package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.UrlDto;
import ru.job4j.dto.UserDto;
import ru.job4j.model.Site;
import ru.job4j.model.Url;
import ru.job4j.service.ShortCutService;

@RestController
@RequestMapping("/short_url")
@AllArgsConstructor
public class ShortCutController {

    private final ShortCutService service;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody Site site) {
        return ResponseEntity.ok(service.registration(site));
    }

    @PostMapping("/convert")
    public ResponseEntity<String> regUrl(@RequestBody Url url) {
        if (service.convert(url)) {
            return ResponseEntity.ok(url.getShortUrl());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<?> getPage(@PathVariable String shortUrl) {
        var optionalUrl = service.findByShortUrl(shortUrl);
        return optionalUrl.map(url -> ResponseEntity.status(HttpStatus.FOUND)
                .body(new UrlDto(HttpStatus.FOUND.value(), url.getUrl())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new UrlDto(HttpStatus.NOT_FOUND.value(),
                        String.format("%s not found page", shortUrl))));
    }

    @GetMapping("/statistic")
    public ResponseEntity<?> getStatistic(@RequestBody Site site) {
        return ResponseEntity.ok(service.getStatistic(site).getStatisticForUrl());
    }
}
