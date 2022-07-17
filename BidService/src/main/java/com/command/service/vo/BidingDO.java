package com.command.service.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bson.json.JsonObject;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="bid")
public class BidingDO {

	@Id
	@GeneratedValue
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("productName")
	private String name;
	@JsonProperty("bidderName")
	private String bidderName;
	@JsonProperty("bidAmount")
	private double bidAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	@Override
	public String toString() {
		JSONObject returnObj = new JSONObject();
		returnObj.put("id", id);
		returnObj.put("bidderName", bidderName);
		returnObj.put("bidAmount", bidAmount);
		returnObj.put("productName", name);
		return returnObj.toString();
	}
	public BidingDO(String productName, String bidderName, double bidAmount) {
		super();
		this.name = productName;
		this.bidderName = bidderName;
		this.bidAmount = bidAmount;
	}
	public BidingDO() {
		super();
	}
	
	
}
