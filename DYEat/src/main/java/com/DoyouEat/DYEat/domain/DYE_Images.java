package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DYE_Images {

    @Id
    @GeneratedValue
    @Column(name="DYE_Images_Code")
    private Long id;

    @Column(name="DYE_Images_originalName", length = 200)
    private String originalName;

    @Column(name="DYE_Images_saveName", length = 200)
    private String saveName;


    @CreatedDate
    @Column(name="DYE_Images_newDate")
    private LocalDateTime newDate;

    @LastModifiedDate
    @Column(name="DYE_Images_editDate")
    private LocalDateTime editDate;

    @Column(name="DYE_Images_Type")
    private int type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DYE_Images_Menu_Code")
    private DYE_Menu DYEMenu;

    @Builder
    public DYE_Images(int type) {
        this.type = type;
    }

    public DYE_Images(String originalName, String saveName, String path) {
        this.originalName = originalName;
        this.saveName = saveName;
    }

}
