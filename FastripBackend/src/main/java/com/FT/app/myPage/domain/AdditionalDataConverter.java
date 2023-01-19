package com.FT.app.myPage.domain;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdditionalDataConverter implements AttributeConverter<AdditionalData, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AdditionalData additionalData) {
        // AdditionalData -> Json문자열로 변환
        try {
            return objectMapper.writeValueAsString(additionalData);
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", additionalData, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AdditionalData convertToEntityAttribute(String jsonStr) {
        // Json 문자열 -> AdditionalData로 변환
        try {
            return objectMapper.readValue(jsonStr, AdditionalData.class);
        } catch (IOException e) {
            log.error("fail to deserialize as Json into Object : {}", jsonStr, e);
            throw new RuntimeException(e);
        }
    }
}