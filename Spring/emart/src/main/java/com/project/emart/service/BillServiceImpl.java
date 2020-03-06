package com.project.emart.service;


import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.emart.dao.BillDao;
import com.project.emart.dao.BillDetailsDao;
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
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billDao;

	@Autowired
	BillDetailsDao billDetailsDao;

	@Override
	@Transactional
	public BillPojo saveBill(BillPojo billPojo) {
		BuyerSignupPojo buyerSignupPojo = billPojo.getBuyer();
		BuyerSignupEntity buyerSignupEntity = new BuyerSignupEntity(buyerSignupPojo.getId(), null,
				buyerSignupPojo.getUsername(), buyerSignupPojo.getPassword(), buyerSignupPojo.getEmail(),
				buyerSignupPojo.getMobile(), buyerSignupPojo.getDate());

		
		BillEntity billEntity = new BillEntity();
		billEntity.setId(0);
		billEntity.setBuyer(buyerSignupEntity);
		billEntity.setType(billPojo.getType());
		billEntity.setDate(billPojo.getDate());
		billEntity.setRemarks(billPojo.getRemarks());
		billEntity.setAmount(billPojo.getAmount());

		billEntity = billDao.saveAndFlush(billEntity);
		billPojo.setId(billEntity.getId());

		BillEntity setBillEntity = billDao.findById(billEntity.getId()).get();

		Set<BillDetailsEntity> allBillDetailsEntity = new HashSet<BillDetailsEntity>();
		Set<BillDetailsPojo> allBillDetailsPojo = billPojo.getAllBillDetails();

		for (BillDetailsPojo billDetailsPojo : allBillDetailsPojo) {

			ItemPojo itemPojo = billDetailsPojo.getItem();
			SubCategoryPojo subCategoryPojo = itemPojo.getSubCategory();
			CategoryPojo categoryPojo = subCategoryPojo.getCategory();
			SellerSignupPojo sellerSignupPojo = itemPojo.getSeller();
			
			
			//copying sellerSignupPojo to SellerSignupEntity
			SellerSignupEntity sellerSignupEntity = new SellerSignupEntity(sellerSignupPojo.getId(),
					sellerSignupPojo.getUsername(), sellerSignupPojo.getPassword(), sellerSignupPojo.getCompany(),
					sellerSignupPojo.getBrief(), sellerSignupPojo.getGst(), sellerSignupPojo.getAddress(),
					sellerSignupPojo.getEmail(), sellerSignupPojo.getWebsite(), sellerSignupPojo.getContact());
			
			//copying categoryPojo to CategoryEntity
			CategoryEntity categoryEntity = new CategoryEntity(categoryPojo.getId(), categoryPojo.getName(),
					categoryPojo.getBrief());
			
			//copying subCategoryPojo to SubCategoryEntity
			SubCategoryEntity subCategoryEntity = new SubCategoryEntity(subCategoryPojo.getId(),
					subCategoryPojo.getName(), categoryEntity, subCategoryPojo.getBrief(),
					subCategoryPojo.getGstPercent());

			//copying itemPojo to ItemEntity
			ItemEntity itemEntity = new ItemEntity(itemPojo.getId(), itemPojo.getName(), itemPojo.getImage(),
					itemPojo.getPrice(), itemPojo.getStock(), itemPojo.getDescription(), itemPojo.getRemarks(),
					sellerSignupEntity, subCategoryEntity);

			//copying billDetailsPojo to BillDetailsEntity
			BillDetailsEntity billDetailsEntity = new BillDetailsEntity(billDetailsPojo.getId(), setBillEntity,
					itemEntity);
			billDetailsDao.save(billDetailsEntity);

		}

		return billPojo;
	}

}
