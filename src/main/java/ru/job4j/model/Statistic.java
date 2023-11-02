package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Statistic {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "count_call")
    private int count;
    @OneToOne
    private Url url;

    public Statistic() {
    }

    public Statistic(int count, Url url) {
        this.count = count;
        this.url = url;
    }
}
