package com.springboot.controller;

import lombok.Data;

@Data
public class ApplyOfferRequest {
    private int cart_value;
    private int restaurant_id;
    private int user_id;
	public int getCart_value() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getUser_id() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getRestaurant_id() {
		// TODO Auto-generated method stub
		return 0;
	}
}
