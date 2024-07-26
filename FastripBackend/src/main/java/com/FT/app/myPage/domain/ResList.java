package com.FT.app.myPage.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.FT.app.domain.Seat;
import com.FT.app.domain.Way;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class ResList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int res_no;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Seat seat;

    @Column(nullable = false, length = 50)
    private String seatClass1;

    @Column(length = 50)
    private String seatClass2;

    @Enumerated(EnumType.STRING)
    private Way way;

    @Column(nullable = false, length = 50)
    private String flight1;

    @Column(length = 50)
    private String flight2;

    @Column(nullable = false, length = 50)
    private String fromArea;

    @Column(nullable = false, length = 50)
    private String toArea;

    @Column(nullable = false, length = 50)
    private String oneWayArea;

    @Column(nullable = false, length = 50)
    private String startDate;

    @Column(nullable = false, length = 50)
    private String returnDate;

    @Column(nullable = false, length = 10)
    private int adultCount;

    @Column(nullable = false, length = 10)
    private int childCount;

    @Column(nullable = false, length = 10)
    private int infantCount;

    @Column(nullable = false, length = 10)
    private String startTime1;

    @Column(nullable = false, length = 10)
    private String arriveTime1;

    @Column(length = 10)
    private String startTime2;

    @Column(length = 10)
    private String arriveTime2;

    @OneToMany(mappedBy = "resList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Passenger> passengers = new ArrayList<>();

    @CreationTimestamp
    private Timestamp res_date;

    public void Passenger(Passenger passenger) {
    	if (this.passengers == null) {
            this.passengers = new ArrayList<>(); // null 체크 및 초기화
        }
        this.passengers.add(passenger);
        passenger.setResList(this);
    }
}

