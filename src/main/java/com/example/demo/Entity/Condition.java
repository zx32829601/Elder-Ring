package com.example.demo.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "ConditionTable")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp//Use Create time
    @Column(name = "time_stamp")
    @Nullable
    private Timestamp time_stamp;


    @Column(name = "heart_rhythm")
    private int heartrhythm;

    @Column(name = "bloody_oxy")
    private float bloodyoxy;
    @Column(name = "long_gps")
    private double longgps;
    @Column(name = "lati_gps")
    private double latigps;

    @Column(name = "elder_state")
    private String elder_state;

    @Column(columnDefinition = "boolean default false")
    private boolean notify_accept;


    @OneToOne
    @JoinColumn(name = "elder_id")
    private Elder elder;


    public Condition(int heartrhythm, float bloody_oxy, double longgps, double latigps, String state, Elder elder) {
        this.heartrhythm = heartrhythm;
        this.bloodyoxy = bloody_oxy;
        this.longgps = longgps;
        this.latigps = latigps;
        this.elder_state = state;
        this.elder = elder;

    }
}
