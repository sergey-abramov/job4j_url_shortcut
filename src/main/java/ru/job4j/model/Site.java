package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    @OneToMany(mappedBy = "site")
    private List<Url> urls = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private User user;
}
