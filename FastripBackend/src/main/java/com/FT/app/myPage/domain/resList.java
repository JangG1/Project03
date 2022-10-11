package com.FT.app.myPage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long res_no;
    private String email;
    private LocalDateTime res_date;
    private String kind;
    @Lob
    private String input;
    private String output;
}
