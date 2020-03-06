package com.project.emart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.emart.controller.ItemController;
import com.project.emart.dao.ItemDao;
import com.project.emart.entity.CategoryEntity;
import com.project.emart.entity.ItemEntity;
import com.project.emart.entity.SellerSignupEntity;
import com.project.emart.entity.SubCategoryEntity;
import com.project.emart.pojo.CategoryPojo;
import com.project.emart.pojo.ItemPojo;
import com.project.emart.pojo.SellerSignupPojo;
import com.project.emart.pojo.SubCategoryPojo;

@Service
public class ItemServiceImpl implements ItemService {
	static Logger LOG = Logger.getLogger(ItemServiceImpl.class.getClass());

	@Autowired
	ItemDao itemDao;

	@Override
	public List<ItemPojo> getAllItems() {
		LOG.info("Entered getAllItems()");

		List<ItemPojo> allItemPojo = new ArrayList();
		Iterable<ItemEntity> allItemEntity = itemDao.findAll();
		Iterator itr = allItemEntity.iterator();

		while (itr.hasNext()) {

			ItemEntity itemEntity = (ItemEntity) itr.next();
			SubCategoryEntity subCategoryEntity = itemEntity.getSubcategory();
			CategoryEntity categoryEntity = subCategoryEntity.getCategory();
			SellerSignupEntity sellerSignupEntity = itemEntity.getSeller();
			 
			//copying CategoryEntity to CategoryPojo
			CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
					categoryEntity.getBrief());
			
			//copying SubCategoryEntity to SubCategoryPojo
			SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
					subCategoryEntity.getName(), subCategoryEntity.getBrief(), subCategoryEntity.getGstPercent(),
					categoryPojo);
			
			//copying SellerSignupEntity to SellerSignupPojo
			SellerSignupPojo sellerPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
					sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(), sellerSignupEntity.getCompany(),
					sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(), sellerSignupEntity.getAddress(),
					sellerSignupEntity.getEmail(), sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());
			
			//copying ItemEntity to ItemPojo
			ItemPojo itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), itemEntity.getImage(),
					itemEntity.getPrice(), itemEntity.getStock(), itemEntity.getDescription(), itemEntity.getRemarks(),
					sellerPojo, subCategoryPojo);
			allItemPojo.add(itemPojo);
		}

		LOG.info("Exited getAllItems()");
		return allItemPojo;
	}

	@Override
	public ItemPojo getItem(int itemId) {
		
		LOG.info("Entered getItem()");
		
		ItemPojo itemPojo = null;
		
		Optional result = itemDao.findById(itemId);
		if (result.isPresent()) {
			ItemEntity itemEntity = (ItemEntity) result.get();
			
			SubCategoryEntity subCategoryEntity = itemEntity.getSubcategory();
			
			CategoryEntity categoryEntity = subCategoryEntity.getCategory();
			
			SellerSignupEntity sellerSignupEntity = itemEntity.getSeller();
			
			//copying CategoryEntity to CategoryPojo
			CategoryPojo categoryPojo = new CategoryPojo(categoryEntity.getId(), categoryEntity.getName(),
					categoryEntity.getBrief());
			
			//copying subCategoryEntity to SubCategoryPojo
			SubCategoryPojo subCategoryPojo = new SubCategoryPojo(subCategoryEntity.getId(),
					subCategoryEntity.getName(), subCategoryEntity.getBrief(), subCategoryEntity.getGstPercent(),
					categoryPojo);
			
			//copying SellerSignupEntity to SellerSignupPojo
			SellerSignupPojo sellerPojo = new SellerSignupPojo(sellerSignupEntity.getId(),
					sellerSignupEntity.getUsername(), sellerSignupEntity.getPassword(), sellerSignupEntity.getCompany(),
					sellerSignupEntity.getBrief(), sellerSignupEntity.getGst(), sellerSignupEntity.getAddress(),
					sellerSignupEntity.getEmail(), sellerSignupEntity.getWebsite(), sellerSignupEntity.getContact());
			
			//copying ItemEntity to ItemPojo
			itemPojo = new ItemPojo(itemEntity.getId(), itemEntity.getName(), itemEntity.getImage(),
					itemEntity.getPrice(), itemEntity.getStock(), itemEntity.getDescription(), itemEntity.getRemarks(),
					sellerPojo, subCategoryPojo);
		}

		LOG.info("Exited getItem()");
		return itemPojo;
	}

	@Override
	public void deleteItem(Integer itemId) {
		
		LOG.info("Entered deleteItem()");

		LOG.info("Exited deleteItem()");
		itemDao.deleteById(itemId);

	}

	@Override
	public ItemPojo addItem(ItemPojo itemPojo) {
		
		LOG.info("Entered addItem()");

		LOG.info("Exited addItem()");
		return null;
	}

	@Override
	public ItemPojo updateItem(ItemPojo itemPojo) {
		
		LOG.info("Entered updateItem()");

		LOG.info("Exited updateItem()");
		return null;
	}

}
