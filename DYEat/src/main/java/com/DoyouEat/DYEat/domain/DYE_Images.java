package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class DYE_Images {

    @Id
    @GeneratedValue
    @Column(name="DYE_Images_Code")
    private Long id;

    @Column(name="DYE_Images_MenuName", length = 50)
    private String menuName;
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

    public DYE_Images(String originalName, String saveName) {
        this.originalName = originalName;
        this.saveName = saveName;
    }

}
