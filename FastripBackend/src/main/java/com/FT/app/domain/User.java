package com.FT.app.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"EMAIL"})})
public class User {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String profile;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    
    public User(String email, String password, MultipartFile profile) throws IOException{
    	this.email = email;
    	this.password = password;
    	this.profile = null;
    }
    
    public void setProfile(String profile) throws IOException {
    	this.profile = null;
    	if((profile!=null && !profile.equals(""))) {
			this.profile  = profile;
		}
    }
}
