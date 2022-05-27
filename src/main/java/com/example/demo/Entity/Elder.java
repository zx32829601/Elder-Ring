package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Table(name = "Elder")
public class Elder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "ID_number")
    private String ID_number;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "precondition")
    private String precondition;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "guardian_id")
    private Guardian guardian;

    public Elder(String in, int age, String name, String bi, String precondition, String add, Guardian guardian) {
        this.ID_number = in;
        this.age = age;
        this.name = name;
        this.precondition = precondition;
        this.guardian = guardian;
        this.birth = bi;
        this.address = add;
    }
}
