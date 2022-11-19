package com.DoyouEat.DYEat.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class DYE_Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Menu_Code")
    private Long id;

    @Column(name="Menu_Title",length = 50)
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Menu_Account_Code")
    private DYE_Account DYEAccount;

    @Column(name="Menu_Text",length =100)
    private String text;

    @Column(name="Menu_Type")
    private int type;

    @Column(name="Menu_Category")
    private int category;

    @Column(name="Menu_Price")
    private int price;

    @Column(name="Menu_newDate")
    private LocalDateTime newDate;

    @Column(name="Menu_editDate")
    private LocalDateTime editDate;

    @JsonIgnore
    @OneToMany(mappedBy = "DYEMenu")
    private List<DYE_Images> menu_images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEMenu")
    private List<DYE_Orders> menu_orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEMenu")
    private List<DYE_Review> menu_DYE_reviews = new ArrayList<>();


}
