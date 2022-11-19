package com.DoyouEat.DYEat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@Setter
public class DYE_Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Code")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Account_Code")
    private DYE_Account DYEAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Menu_Code")
    private DYE_Menu DYEMenu;

    @JsonIgnore
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private DYE_Delivery dye_delivery;

    @Column(name = "Orders_Status")
    private int status;

    @Column(name = "Orders_Name", length = 50)
    private String name;

    @Column(name = "Orders_Email", length = 50)
    private String email;

    @Column(name = "Orders_PhoneNumber")
    private int phoneNumber;

    @Column(name = "Orders_newDate")
    private LocalDateTime newDate;

    @Column(name = "Orders_editDate")
    private LocalDateTime editDate;

    @JsonIgnore
    @OneToMany(mappedBy = "DYEOrders")
    private List<DYE_Payment> order_DYE_payment = new ArrayList<>();
}
