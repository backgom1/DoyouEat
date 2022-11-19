package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@Setter
public class DYE_Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_Code")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "dye_delivery", fetch = LAZY)
    private DYE_Orders dye_orders;

    @Column(name="Delivery_Address", length =50)
    private String address;

    @Column(name="Delivery_Streets", length =50)
    private String street;

    @Column(name="Delivery_Name", length = 50)
    private String name;

    @Column(name="Delivery_Email", length =50)
    private String email;

    @Column(name = "Delivery_PhoneNumber")
    private int phoneNumber;

    @Column(name="Delivery_Type")
    private int type;

    @Column(name = "Delivery_Status")
    private int status;

    @Column(name="Delivery_newDate")
    private LocalDateTime newDate;

    @Column(name="Delivery_editDate")
    private LocalDateTime editDate;
}
