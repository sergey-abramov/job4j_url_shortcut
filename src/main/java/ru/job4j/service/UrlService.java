package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Statistic;
import ru.job4j.model.Url;
import ru.job4j.repository.UrlRepository;
import ru.job4j.util.GeneratorString;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository repository;

    private final GeneratorString generator;
    private final StatisticService statisticService;

    public Url save(Url url) {
        url.setShortUrl(generator.generateShortUrl());
        var seveUrl = repository.save(url);
        statisticService.save(new Statistic(0, seveUrl));
        return seveUrl;
    }

    public Optional<Url> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
        var optionalUrl = repository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            statisticService.update(optionalUrl.get().getId());
            return optionalUrl;
        }
        return Optional.empty();
    }
}
