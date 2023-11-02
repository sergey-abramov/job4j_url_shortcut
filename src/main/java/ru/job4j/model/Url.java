package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String url;
    @Column(name = "short_url")
    private String shortUrl;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;
}
