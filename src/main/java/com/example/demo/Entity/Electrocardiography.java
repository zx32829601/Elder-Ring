package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Electrocardiography {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "graph")
    private Blob graph;

    @OneToOne
    @JoinColumn(name = "elder")
    private Elder elder;

    public Electrocardiography(Blob graph, Elder elder) {
        this.graph = graph;
        this.elder = elder;
    }
}
