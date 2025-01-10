package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
    private int restaurant_id;
    private String offer_type;
    private int offer_value;

    private List<String> customer_segment;

	public String getCustomer_segment() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRestaurant_id() {
		// TODO Auto-generated method stub
		int id=0;
		
		return id;
	}

	public Object getOffer_type() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
