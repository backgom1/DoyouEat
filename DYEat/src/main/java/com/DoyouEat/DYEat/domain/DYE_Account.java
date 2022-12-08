package com.DoyouEat.DYEat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DYE_Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_Code")
    private Long id;

    @Column(name="Account_Username",length = 50)
    private String username;

    @Column(name="Account_Password",length = 200)
    private String password;

    @Column(name="Account_Nickname", length = 30)
    private String nickname;

    @Column(name="Account_Picture", length = 200)
    private String picture;

    @Column(name="Account_Provider", length = 30)
    private String provider;

    @Column(name="Account_Provider_id", length = 30)
    private String providerid;

    @Column(name="Account_Role")
    private String role;

    @CreatedDate
    @Column(name="Account_newDate")
    private LocalDateTime newDate;

    @LastModifiedDate
    @Column(name="Account_editDate")
    private LocalDateTime editDate;

    @Builder
    public DYE_Account(String username, String password, String nickname, String picture, String provider, String providerid, String role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.picture = picture;
        this.provider = provider;
        this.providerid = providerid;
        this.role = role;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Menu> account_DYE_menus = new ArrayList<>();

    @JoinColumn(name = "Account_Code")
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DYE_Orders> account_orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "DYEAccount")
    private List<DYE_Review> account_DYE_reviews = new ArrayList<>();

    @JsonIgnore
    @JoinColumn(name = "Account_Code")
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DYE_Payment> account_DYE_payment = new ArrayList<>();
}
