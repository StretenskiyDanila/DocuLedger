package org.example.businesspack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@Table(name = "master")
@NoArgsConstructor
@AllArgsConstructor
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String post;

    @ManyToOne()
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private JournalWork journalWork;

}
