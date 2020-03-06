import { Component, OnInit } from '@angular/core';
import { EmartService } from '../../emart.service';

@Component({
  selector: 'app-cart-list',
  templateUrl: './cart-list.component.html',
  styleUrls: ['./cart-list.component.css']
})
export class CartListComponent implements OnInit {
cartItems: any;
  showCart: boolean;

  constructor(protected emartService: EmartService) { }

  ngOnInit(): void {
    this.cartItems = this.emartService.getAllCart();
    if(this.cartItems.length==0){
      this.showCart=false;
    }
    else{
      this.showCart=true;
    }
  }

  deleteCartItem(itemId: number){
    this.emartService.deleteCartItem(itemId);
     this.cartItems =  this.emartService.getAllCart();
  }
  }




