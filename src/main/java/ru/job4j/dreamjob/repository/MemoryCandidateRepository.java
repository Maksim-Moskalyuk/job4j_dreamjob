package ru.job4j.dreamjob.repository;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

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
public class MemoryCandidateRepository implements CandidateRepository {

    private final AtomicInteger total = new AtomicInteger(0);

    private final ConcurrentMap<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Александр", "Intern Java Developer"));
        save(new Candidate(0, "Денис", "Junior Java Developer"));
        save(new Candidate(0, "Алёна", "Junior+ Java Developer"));
        save(new Candidate(0, "Мария", "Middle Java Developer"));
        save(new Candidate(0, "Пётр", "Middle+ Java Developer"));
        save(new Candidate(0, "Максим", "Senior Java Developer"));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(total.incrementAndGet());
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
                (id, oldCandidate) -> new Candidate(oldCandidate.getId(), candidate.getName(), candidate.getDescription(), candidate.getCityId())) != null;
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
