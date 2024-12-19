package com.db.security.model;

import com.db.security.model.enum_type.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "PASSPORT", name = "userrequest")
@Accessors(chain = true)
public class UserRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullname")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String CCCD;

    private String phone;

    private String email;

    @Column(name = "ISAUTHENTICATED", insertable = false, updatable = false)
    private int isAuthenticated;

    @Column(name = "ISAPPROVE", insertable = false, updatable = false)
    private int isApprove;

    @Column(name = "ISREJECTED", insertable = false, updatable = false)
    private int isRejected;

    @Column(insertable=false)
    private String note;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passportid",referencedColumnName = "passportID")
    private PassportEntity passport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationid", referencedColumnName = "id")
    private LocationEntity location;
}