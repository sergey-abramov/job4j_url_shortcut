package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.dto.StatisticDto;
import ru.job4j.dto.UserDto;
import ru.job4j.model.Site;
import ru.job4j.model.Url;
import ru.job4j.model.User;
import ru.job4j.util.GeneratorString;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShortCutService {

    private final UserService userService;

    private final SiteService siteService;

    private final UrlService urlService;
    private final StatisticService statisticService;

    private final GeneratorString generator;

    public UserDto registration(Site site) {
        var optionalSite = siteService.findByName(site.getName());
        if (optionalSite.isPresent()) {
            var user = userService.loadUserByUsername(optionalSite.get().getUser().getLogin());
            return new UserDto(true, user.getUsername(), user.getPassword());
        }
        User user = new User(generator.generateLogin(), generator.generatePassword());
        var userDto = new UserDto(false, user.getLogin(), user.getPassword());
        var saveUser = userService.save(user);
        site.setUser(saveUser);
        siteService.save(site);
        return userDto;
    }

    public boolean convert(Url url) {
        if (urlService.findByUrl(url.getUrl()).isEmpty()) {
            String[] s = url.getUrl().split("/");
            var optionalSite = siteService.findByName(s[2]);
            if (optionalSite.isPresent()) {
                var site = optionalSite.get();
                List<Url> listUrls = site.getUrls();
                url.setSite(site);
                listUrls.add(urlService.save(url));
                site.setUrls(listUrls);
                siteService.update(site);
                return true;
            }
        }
        return false;
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
        var optionalUrl = urlService.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            if (siteService.findById(optionalUrl.get().getSite().getId()).isPresent()) {
                return optionalUrl;
            }
        }
        return Optional.empty();
    }

    public StatisticDto getStatistic(Site site) {
        var optionalSite = siteService.findByName(site.getName());
        if (optionalSite.isPresent()) {
            var expectedSite = optionalSite.get();
            var statisticDto = new StatisticDto();
            var map = statisticDto.getStatisticForUrl();
            for (Url url : expectedSite.getUrls()) {
                map.put(url.getUrl(), statisticService.findByUrl(url).get().getCount());
            }
            statisticDto.setStatisticForUrl(map);
            return statisticDto;
        }
        return null;
    }
}
