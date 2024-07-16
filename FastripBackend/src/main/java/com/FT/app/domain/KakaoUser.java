package com.FT.app.domain;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class KakaoUser {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = true, length = 50)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 100)
	private String password;
    
    @Column(nullable = false, length = 100)
    private String profile;
    
    @Column(nullable = true, length = 50)
    private String gender;

    @Column(nullable = true, length = 50)
    private String birthday;
    
    @Column(nullable = false, length = 100)
    private String refresh_token;
    
    @Column(nullable = false, length = 100)
    private String access_token;
    
    @Column(nullable = false, length = 100)
    private Long loginId;
    
    //@CreationTimestamp
    @Column(nullable = false, length = 50)
	private String login_date;
    
    
}
