package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Site;
import ru.job4j.model.Url;
import ru.job4j.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SiteService {

    private final SiteRepository repository;

    public Site save(Site site) {
        return repository.save(site);
    }

    public boolean update(Site site) {
        if (findById(site.getId()).isPresent()) {
            repository.save(site);
            return true;
        }
        return false;
    }

    public Optional<Site> findByName(String name) {
        return repository.findByName(name);
    }

    public Optional<Site> findById(int id) {
        return repository.findById(id);
    }

    public Optional<Site> findByUrlId(Url url) {
        return repository.findByUrls(url);
    }
}
