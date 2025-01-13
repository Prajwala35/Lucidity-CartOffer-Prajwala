package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyOfferResponse {
    private int cartValueAfterOffer;
    
    public ApplyOfferResponse(int cartValueAfterOffer) {
        this.cartValueAfterOffer = cartValueAfterOffer;
    }

    // Getter
    public int getUpdatedCartValue() {
        return cartValueAfterOffer;
    }
}
