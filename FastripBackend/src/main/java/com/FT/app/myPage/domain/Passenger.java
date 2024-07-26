package com.FT.app.myPage.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "res_no")
    @JsonBackReference
    private ResList resList;

    private String type;
    private String korName;
    private String engLastName;
    private String engFirstName;
    private String gender;
    private String birthday;
}
