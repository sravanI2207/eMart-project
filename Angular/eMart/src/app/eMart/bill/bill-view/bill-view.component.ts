import { Component, OnInit } from '@angular/core';
import { EmartService } from '../../emart.service';
import { Item } from '../../item';
import { Router } from '@angular/router';
import { BillDetails } from '../../bill.details';

@Component({
  selector: 'app-bill-view',
  templateUrl: './bill-view.component.html',
  styleUrls: ['./bill-view.component.css']
})
export class BillViewComponent implements OnInit {
  cartItems:any;
amount:number=0;
  todayDate: Date=new Date;
  currentBuyer: any;
  allBillDetails:BillDetails[];

  constructor(protected emartService:EmartService,  protected router: Router) { }

  ngOnInit(): void {
    this.currentBuyer = this.emartService.getCurrentBuyer();
    this.cartItems=this.emartService.getAllCart();
    let size=this.cartItems.length;
   for(let i=0;i<size;i++)
   {
    this.amount = this.amount + this.cartItems[i].price
   }
     }
     addBill(){
      this.currentBuyer = this.emartService.getCurrentBuyer();
      this.emartService
      .addBill( this.todayDate, this.amount)
      .subscribe(
        (res) => {
          this.emartService
              .getBuyer(this.currentBuyer.id)
              .subscribe(
                (response) => {
                  this.currentBuyer = response;
                  this.emartService.setBuyerAndBills(this.currentBuyer)
                }
              )
        }
      )
      this.router.navigate(['item-list']);
    }
  
}

