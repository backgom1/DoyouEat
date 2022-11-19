package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class DYE_Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Review_Code")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Review_Account_Code")
    private DYE_Account DYEAccount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Review_Menu_Code")
    private DYE_Menu DYEMenu;

    @Column(name="Review_Title",length = 50)
    private String title;

    @Column(name="Review_Text",length = 100)
    private String text;


    @Column(name="Review_newDate")
    private LocalDateTime newDate;

    @Column(name="Review_editDate")
    private LocalDateTime editDate;
}
