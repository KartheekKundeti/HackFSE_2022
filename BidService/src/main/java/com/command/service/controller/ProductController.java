package com.command.service.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.command.service.dao.TransactionalInterface;
import com.command.service.vo.BidingDO;
import com.command.service.vo.ProductVO;

@CrossOrigin
@RestController
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	TransactionalInterface interface1;
	
	@PostMapping("/add-product")
	public String registerProduct(@RequestBody ProductVO product) {
		//ProductVO pd = new ProductVO("sample123", "sample sd123456", "sample dd123000", "sample cat123", 100, new Date());
		//product.setBidEndDate(new Date());
		logger.info("Going to add product:"+product.toString());
		ProductVO vo = interface1.saveProduct(product);
		logger.info("The response is "+vo.toString());
		
		
		/*
		 * DynamoDBImpl dbImpl = new DynamoDBImpl(); ProductInfo pdt = new
		 * ProductInfo(); pdt.setName(product.getName());
		 * pdt.setCategory(product.getCategory()); dbImpl.createProduct(pdt);
		 */
		 
		 
		
		return vo.toString();
	}
	
	@PostMapping("/place-bid")
	public String placeBid(@RequestBody BidingDO bidingDO) {
		interface1.saveBid(bidingDO);
		return bidingDO.toString();
	}
	
	@GetMapping("/show-bid/{productName}")
	public String placeBid(@PathVariable("productName") String productid) {
		List<BidingDO> list = interface1.showBids(productid);
		
		return list.get(0).toString();
	}
	
	@GetMapping("/delete/{productName}")
	public String deleteProduct(@PathVariable("productName") String productName) {
		ProductVO vo = new ProductVO();
		vo.setName(productName);
		boolean deleteStatus = interface1.deleteProduct(vo);
		if(deleteStatus) {
		return "Product with name "+productName+" deleted";
		} else {
			return "Product with name "+productName+" has no Active Bids. Cant be deleted";
		}
		
	}
	
	@GetMapping("/showProducts")
	public List<ProductVO> showProducts() {
		List<ProductVO> list = interface1.showAllProducts();
		return list;
	}
	
	@PostMapping("/showProductbycat")
	public List<ProductVO> showProductbycat(@RequestBody String productCategory) {
		List<ProductVO> list = interface1.showProductbycat(productCategory);
		return list;
	}
	@PostMapping("/showProductbyid")
	public Optional<ProductVO> showProductbyId(@RequestBody String productId) {
		Optional<ProductVO> list = interface1.showProductbyId(productId);
		return list;
	}
	
	@GetMapping("/showAllbids")
	public String showAllbids() {
		List<BidingDO> biddingList = interface1.showAllbids();
		return biddingList.toString();
	}
	
}
