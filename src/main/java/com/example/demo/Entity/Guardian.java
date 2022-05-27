package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "telephone_number")
    private String telephone_number;


    public Guardian(String name, String telephone_number) {
        this.name = name;
        this.telephone_number = telephone_number;

    }
}
