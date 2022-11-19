package com.DoyouEat.DYEat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class DYE_Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_Code")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Payment_Account_Code")
    private DYE_Account DYEAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Payment_Order_Code")
    private DYE_Orders DYEOrders;

    @Column(name="Payment_payWay", length = 50)
    private String payWay;

    @Column(name="Payment_payCheck")
    private boolean payCheck;

    @Column(name="Payment_newDate")
    private LocalDateTime newDate;
}
