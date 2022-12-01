package com.DoyouEat.DYEat.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DYE_Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Event_code")
    private Long id;

    @Column(name="Event_Title",length = 50)
    private String title;

    @Column(name="Event_Text",length =100)
    private String text;

    @Column(name="Event_Original_Picture",length =255)
    private String picture_ori;

    @Column(name="Event_Save_Picture",length =255)
    private String picture_save;

    @Column(name="Event_onOff")
    private int onoff;


    @CreatedDate
    @Column(name="Menu_newDate")
    private LocalDateTime newDate;

    @LastModifiedDate
    @Column(name="Menu_editDate")
    private LocalDateTime editDate;

}
