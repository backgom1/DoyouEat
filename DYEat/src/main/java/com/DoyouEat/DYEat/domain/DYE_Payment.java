package com.DoyouEat.DYEat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class DYE_Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_Code")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Account_Code")
    private DYE_Account DYEAccount;

    @JsonIgnore
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Delivery_Code")
    private DYE_Delivery dye_delivery;


    @Column(name="Payment_payWay", length = 50)
    private String payWay;

    @Column(name="Payment_payCheck")
    private boolean payCheck;

    @CreatedDate
    @Column(name="Payment_newDate")
    private LocalDateTime newDate;
}
