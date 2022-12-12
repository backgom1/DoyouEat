package com.DoyouEat.DYEat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DYE_Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Code")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Orders_Account_Code")
    private DYE_Account DYEAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_Menu_Code")
    private DYE_Menu DYEMenu;

    @JsonIgnore
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Delivery_Code")
    private DYE_Delivery dye_delivery;

    @Column(name = "Orders_Status")
    private int status;

    @Column(name = "Orders_Quantity")
    private int quantity;

    @Column(name="Order_WantDate", length =50)
    private String wantdate;

    @Column(name="Order_How", length =50)
    private String how;

    @Column(name="Order_Price")
    private int price;

    @CreatedDate
    @Column(name = "Orders_newDate")
    private LocalDateTime newDate;

    @LastModifiedDate
    @Column(name = "Orders_editDate")
    private LocalDateTime editDate;

}
