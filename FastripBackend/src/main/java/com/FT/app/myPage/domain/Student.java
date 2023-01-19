package com.FT.app.myPage.domain;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Student {
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String key;
	
    private String name;
    
    private int age;

//    @Convert(converter = AdditionalDataConverter.class)
//    private AdditionalData additionalData;
}
