package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Александр", "Intern Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Candidate(0, "Денис", "Junior Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Candidate(0, "Алёна", "Junior+ Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Candidate(0, "Мария", "Middle Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Candidate(0, "Пётр", "Middle+ Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
        save(new Candidate(0, "Максим", "Senior Java Developer", LocalDateTime.of(2023, Month.MARCH, 12, 17, 56, 0)));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) -> new Candidate(oldCandidate.getId(), candidate.getName())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }

}
