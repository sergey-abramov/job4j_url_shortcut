package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findByUrl(String url);

    Optional<Url> findByShortUrl(String shortUrl);
}
