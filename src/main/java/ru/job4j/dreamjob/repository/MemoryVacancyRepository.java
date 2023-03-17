package ru.job4j.dreamjob.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryVacancyRepository implements VacancyRepository {

    private final AtomicInteger total = new AtomicInteger(0);

    private final ConcurrentMap<Integer, Vacancy> vacancies = new ConcurrentHashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Это новичок с опытом до 6 месяцев",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Junior Java Developer", "Это новичок с опытом от 6-12 месяцев",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Junior+ Java Developer", "Это специалист со стажем от 1 года",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Middle Java Developer", "Это специалист со стажем от 3 лет",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Middle+ Java Developer", "Это специалист со стажем от 3-5 лет",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Senior Java Developer", "Это профессионал с опытом не менее 5 лет",
                LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(total.incrementAndGet());
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> {
            return new Vacancy(oldVacancy.getId(), vacancy.getTitle(), vacancy.getDescription(), vacancy.getCreationDate(), vacancy.getVisible());
        }) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }

}