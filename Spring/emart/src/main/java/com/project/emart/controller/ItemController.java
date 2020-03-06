package com.project.emart.controller;

import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.emart.pojo.ItemPojo;
import com.project.emart.service.ItemService;

@RestController 
@CrossOrigin
@RequestMapping("emart")

public class ItemController {
	
	static Logger LOG = Logger.getLogger(ItemController.class.getClass());

	@Autowired
	ItemService itemService;

	@GetMapping("/item/{itemId}")
	ItemPojo getItem(@PathVariable("itemId") int itemId) {
		
		LOG.info("Entered end point\'emart/item/{itemId}\'");

		LOG.info("Exited end point\'emart/item/{itemId}\'");
		return itemService.getItem(itemId);
	}

	@GetMapping("/item/all")
	List<ItemPojo> getAllItems() {
		
		LOG.info("Entered end point\'emart/item/all\'");
		
		LOG.info("Exited end point\'emart/item/all\'");

		return itemService.getAllItems();
	}

	@DeleteMapping("/item/{itemId}")
	void deleteItem(@PathVariable("itemId") int itemId) {
		
		LOG.info("Entered end point\'emart/item/{itemId}\'");

		LOG.info("Exited end point\'emart/item/{itemId}\'");
		itemService.deleteItem(itemId);
	}

	@PostMapping("/item")
	ItemPojo addItem(@RequestBody ItemPojo itemPojo) {
		
		LOG.info("Entered end point\'emart/item\'");

		LOG.info("Exited end point\'emart/item\'");
		return itemService.addItem(itemPojo);
	}

	@PutMapping("/item")	
	ItemPojo updateItem(@RequestBody ItemPojo itemPojo) {
		
		LOG.info("Entered end point\'emart/item\'");

		LOG.info("Exited end point\'emart/item\'");
		return itemService.updateItem(itemPojo);
	}
}
