package com.FT.app.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity // ProdList 클래스가 MySQL 테이블에 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdList {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prod_no;
    
    @Column(nullable = false, length = 50)
    private String FromArea;
    
    @Column(nullable = false, length = 50)
    private String ToArea;
    
    @Column(nullable = false, length = 20)
    private String seat;
    
    @Column(nullable = false, length = 20)
    private String way;
    
    @Column(nullable = false, length = 100)
    private String price;
}
