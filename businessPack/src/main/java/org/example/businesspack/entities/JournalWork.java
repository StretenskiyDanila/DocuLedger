package org.example.businesspack.entities;

import com.sun.tools.javac.Main;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "journal_work")
@NoArgsConstructor
@AllArgsConstructor
public class JournalWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;

    @OneToMany(mappedBy = "journalWork", fetch = FetchType.LAZY)
    private List<Master> master;

    @OneToMany(mappedBy = "journalWork", fetch = FetchType.LAZY)
    private List<Maintenance> maintenance;

    @Column(name = "work_date")
    private Timestamp workDate;

}
