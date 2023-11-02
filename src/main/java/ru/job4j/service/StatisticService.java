package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Statistic;
import ru.job4j.model.Url;
import ru.job4j.repository.StatisticRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository repository;

    public Statistic save(Statistic statistic) {
        return repository.save(statistic);
    }

    @Transactional
    public void update(int id) {
        repository.callProcedureIncrement(id);
    }

    public Optional<Statistic> findById(Statistic statistic) {
        return repository.findById(statistic.getId());
    }

    public Optional<Statistic> findByUrl(Url url) {
        return repository.findByUrl(url);
    }
}
