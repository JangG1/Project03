package com.FT.app.myPage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalData {
    private Double height;
    private Double weight;
    private String hobby;
    
}
