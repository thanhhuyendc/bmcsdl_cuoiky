package com.db.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "PASSPORT", name = "passport")
@Accessors(chain = true)
public class PassportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PASSPORTID")
    private String passportID;

    @Column(name = "STARTDATE")
    private LocalDate startDate;

    @Column(name = "ENDDATE")
    private LocalDate endDate;

    @Column(name = "ISXD")
    private int isXD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESIDENTID", referencedColumnName = "id")
    private ResidentEntity resident;
}