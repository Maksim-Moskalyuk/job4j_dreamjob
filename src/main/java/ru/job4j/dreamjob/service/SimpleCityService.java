package ru.job4j.dreamjob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.repository.CityRepository;
import ru.job4j.dreamjob.repository.Sql2oCityRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SimpleCityService implements CityService {

    private final Sql2oCityRepository sql2oCityRepository;

    @Override
    public Collection<City> findAll() {
        return sql2oCityRepository.findAll();
    }
}