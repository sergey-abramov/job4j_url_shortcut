package ru.job4j.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "%s.%s", message = "The line must contain the site name")
    @NotBlank(message = "Name must be not empty")
    private String name;
    @OneToMany(mappedBy = "site")
    private List<Url> urls = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private User user;
}
