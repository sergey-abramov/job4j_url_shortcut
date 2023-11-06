package ru.job4j.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Statistic;
import ru.job4j.model.Url;

import java.util.Optional;

public interface StatisticRepository extends CrudRepository<Statistic, Integer> {
    @Modifying
    @Query(value = """
            update statistic
            SET count_call = count_call + 1
            where url_id = :#{#urlId} returning url_id;""", nativeQuery = true)
    void callProcedureIncrement(int urlId);

    Optional<Statistic> findByUrl(Url url);
}
