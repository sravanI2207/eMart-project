package com.project.emart.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.emart.dao.BuyerSignupDao;
import com.project.emart.entity.BillDetailsEntity;
import com.project.emart.entity.BillEntity;
import com.project.emart.entity.BuyerSignupEntity;
import com.project.emart.entity.CategoryEntity;
import com.project.emart.entity.ItemEntity;
import com.project.emart.entity.SellerSignupEntity;
import com.project.emart.entity.SubCategoryEntity;
import com.project.emart.pojo.BillDetailsPojo;
import com.project.emart.pojo.BillPojo;
import com.project.emart.pojo.BuyerSignupPojo;
import com.project.emart.pojo.CategoryPojo;
import com.project.emart.pojo.ItemPojo;
import com.project.emart.pojo.SellerSignupPojo;
import com.project.emart.pojo.SubCategoryPojo;

@Service
public class BuyerSignupServiceImpl implements BuyerSignupService {

	@Autowired
	BuyerSignupDao buyerDao;

	@Override
	public BuyerSignupPojo validateBuyersignup(BuyerSignupPojo buyerSignupPojo) {

		System.out.println("username:" + buyerSignupPojo.getUsername());
		System.out.println("password:" + buyerSignupPojo.getPassword());
		BuyerSignupEntity buyerSignupEntity = buyerDao.findByUsernameAndPassword(buyerSignupPojo.getUsername(),
				buyerSignupPojo.getPassword());
		System.out.println(buyerSignupEntity);

		if (buyerSignupEntity != null) {

			Set<BillEntity> allBillsEntity = buyerSignupEntity.getAllBills();
			Set<BillPojo> allBillPojo = new HashSet<BillPojo>();

			for (BillEntity billEntity : allBillsEntity) {

				Set<BillDetailsEntity> allBillDetailsEntity = billEntity.getAllBillDetails();
				Set<BillDetailsPojo> allBillDetailsPojo = new HashSet<BillDetailsPojo>();

				for (BillDetailsEntity billDetailsEntity : allBillDetailsEntity) {

					ItemEntity itemEntity = billDetailsEntity.getItemId();
					SubCategoryEntity subCategoryEntity = itemEntity.getSubcategory();
					CategoryEntity categoryEntity = subCategoryEntity.getCategory();
					SellerSignupEntity sellerSignupEntity = itemEntity.getSeller();
					
					CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
							categoryEntity.getBrief());
					
					//copying subCategoryEntity to SubCategoryPojo
					SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
							subCategoryEntity.getName(), subCategoryEntity.getBrief(),
							subCategoryEntity.getGstPercent(), categoryPojo);
					
					//copying sellerSignupEntity to SellerSignupPojo
					SellerSignupPojo sellerPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
							sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(),
							sellerSignupEntity.getCompany(), sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(),
							sellerSignupEntity.getAddress(), sellerSignupEntity.getEmail(),
							sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());
					
					//copying itemEntity to ItemPojo
					ItemPojo itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), itemEntity.getImage(),
							itemEntity.getPrice(), itemEntity.getStock(), itemEntity.getDescription(),
							itemEntity.getRemarks(), sellerPojo, subCategoryPojo);
					
					//copying billDetailsEntity to BillDetailsPojo
					BillDetailsPojo billDetailsPojo = new BillDetailsPojo(billDetailsEntity.getId(), null, itemPojo);
					allBillDetailsPojo.add(billDetailsPojo);

				}
				 //copying billEntity to BillPojo
				BillPojo billPojo = new BillPojo(billEntity.getId(), null, billEntity.getType(), billEntity.getDate(),
						billEntity.getRemarks(), billEntity.getAmount(), allBillDetailsPojo);
				allBillPojo.add(billPojo);

			}
			
			//copying buyerSignupEntity to BuyerSignupPojo
			buyerSignupPojo = new BuyerSignupPojo(buyerSignupEntity.getId(), buyerSignupEntity.getUsername(),
					buyerSignupEntity.getPassword(), buyerSignupEntity.getEmail(), buyerSignupEntity.getMobile(),
					buyerSignupEntity.getDate(), allBillPojo);

		}

		return buyerSignupPojo;
	}

	@Override
	public BuyerSignupPojo getBuyer(Integer id) {
		
		BuyerSignupPojo buyerSignuppojo = null;
		BuyerSignupEntity buyerSignupEntity = buyerDao.findById(id).get();
		System.out.println("get:" + buyerSignupEntity);
		if (buyerSignupEntity != null) {

			Set<BillEntity> allBillsEntity = buyerSignupEntity.getAllBills();
			Set<BillPojo> allBillPojo = new HashSet<BillPojo>();

			for (BillEntity billEntity : allBillsEntity) {

				Set<BillDetailsEntity> allBillDetailsEntity = billEntity.getAllBillDetails();
				Set<BillDetailsPojo> allBillDetailsPojo = new HashSet<BillDetailsPojo>();

				for (BillDetailsEntity billDetailsEntity : allBillDetailsEntity) {

					ItemEntity itemEntity = billDetailsEntity.getItemId();
					SubCategoryEntity subCategoryEntity = itemEntity.getSubcategory();
					CategoryEntity categoryEntity = subCategoryEntity.getCategory();
					SellerSignupEntity sellerSignupEntity = itemEntity.getSeller();
					
					//copying categoryEntity to CategoryPojo
					CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
							categoryEntity.getBrief());
					
					//copying subCategoryEntity to SubCategoryPojo
					SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
							subCategoryEntity.getName(), subCategoryEntity.getBrief(),
							subCategoryEntity.getGstPercent(), categoryPojo);
					
					//copying sellerSignupEntity to SellerSignupPojo
					SellerSignupPojo sellerPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
							sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(),
							sellerSignupEntity.getCompany(), sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(),
							sellerSignupEntity.getAddress(), sellerSignupEntity.getEmail(),
							sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());
					
					//copying itemEntity to ItemPojo
					ItemPojo itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), itemEntity.getImage(),
							itemEntity.getPrice(), itemEntity.getStock(), itemEntity.getDescription(),
							itemEntity.getRemarks(), sellerPojo, subCategoryPojo);
					
					//copying billDetailsEntity to BillDetailsPojo
					BillDetailsPojo billDetailsPojo = new BillDetailsPojo(billDetailsEntity.getId(), null, itemPojo);
					allBillDetailsPojo.add(billDetailsPojo);
				}
				
				//copying billEntity to BillPojo
				BillPojo billPojo = new BillPojo(billEntity.getId(), null, billEntity.getType(), billEntity.getDate(),
						billEntity.getRemarks(), billEntity.getAmount(), allBillDetailsPojo);
				allBillPojo.add(billPojo);

			}
			
			//copying buyerSignupEntity to BuyerSignupPojo
			buyerSignuppojo = new BuyerSignupPojo(buyerSignupEntity.getId(), buyerSignupEntity.getUsername(),
					buyerSignupEntity.getPassword(), buyerSignupEntity.getEmail(), buyerSignupEntity.getMobile(),
					buyerSignupEntity.getDate(), allBillPojo);

		}

		return buyerSignuppojo;
	}

}
