package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class DYE_Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_Code")
    private Long id;

    @Column(name="Account_EmailID",length = 50)
    private String emailId;

    @Column(name="Account_Password",length = 200)
    private String password;

    @Column(name="Account_Nickname", length = 30)
    private String nickname;

    @Column(name="Account_Type")
    private int type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name="Account_newDate")
    private LocalDateTime newDate = LocalDateTime.now();

    @Column(name="Account_editDate")
    private LocalDateTime editDate;

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Menu> account_DYE_menus = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Images> account_images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Orders> account_orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Review> account_DYE_reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Payment> account_DYE_payment = new ArrayList<>();
}
