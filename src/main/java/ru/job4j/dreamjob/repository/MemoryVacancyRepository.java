package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.Vacancy;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryVacancyRepository implements VacancyRepository {

    private static final MemoryVacancyRepository INSTANCE = new MemoryVacancyRepository();

    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Это новичок с опытом до 6 месяцев, который знает базовые конструкции Джава",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Junior Java Developer", "Это новичок с опытом от 6-12 месяцев, который знает базовые конструкции Джава",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Junior+ Java Developer", "Это специалист со стажем от 1 года, который способен самостоятельно писать простые программы на Джава",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Middle Java Developer", "Это специалист со стажем от 3 лет, который способен самостоятельно и с нуля сделать программу или приложение",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Middle+ Java Developer", "Это специалист со стажем от 3-5 лет, который способен самостоятельно и с нуля сделать программу или приложение",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Vacancy(0, "Senior Java Developer", "Это профессионал с опытом не менее 5 лет, который совмещает обязанности технического руководителя и  в команде программистов",  LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
    }

    public static MemoryVacancyRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> new Vacancy(oldVacancy.getId(), vacancy.getTitle())) != null;
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