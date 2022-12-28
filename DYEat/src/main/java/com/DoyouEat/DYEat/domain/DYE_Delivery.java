package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DYE_Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_Code")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "dye_delivery", fetch = LAZY, cascade = CascadeType.ALL)
    private DYE_Orders dye_orders;

    @JsonIgnore
    @OneToOne(mappedBy = "dye_delivery", fetch = LAZY, cascade = CascadeType.ALL)
    private DYE_Payment dye_payment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Delivery_Account_Code")
    private DYE_Account DYEAccount;

    @Column(name="Delivery_Address", length =50)
    private String address;

    @Column(name="Delivery_Streets", length =50)
    private String street;

    @Column(name="Delivery_Name", length = 50)
    private String name;

    @Column(name="Delivery_Email", length =50)
    private String email;

    @Column(name = "Delivery_PhoneNumber")
    private String phoneNumber;

    @Column(name="Delivery_Type")
    private int type;

    @Column(name = "Delivery_Status")
    private int status;

    @CreatedDate
    @Column(name="Delivery_newDate")
    private LocalDateTime newDate;

    @LastModifiedDate
    @Column(name="Delivery_editDate")
    private LocalDateTime editDate;
}
