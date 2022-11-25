package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class DYE_Images {

    @Id
    @GeneratedValue
    @Column(name="DYE_Images_Code")
    private Long id;

    @Column(name="DYE_Images_originalName", length = 200)
    private String originalName;

    @Column(name="DYE_Images_saveName", length = 200)
    private String saveName;

    @Column(name="DYE_Images_Path", length = 200)
    private String path;

    @Column(name="Account_Type")
    private int type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DYE_Images_Menu_Code")
    private DYE_Menu DYEMenu;


}
