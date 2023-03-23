package ru.job4j.dreamjob.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.configuration.DatasourceConfiguration;
import ru.job4j.dreamjob.model.User;

import java.util.List;
import java.util.Properties;

import static java.util.Optional.empty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepositoryTest
                .class
                .getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);

    }

    @AfterEach
    public void clearVacancies() {
        var users = sql2oUserRepository.findAll();
        for (var user : users) {
            sql2oUserRepository.deleteByEmail(user.getEmail());
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var user = sql2oUserRepository.save(new User(1, "name", "name", "password"));
        var saveduser = sql2oUserRepository.findByEmail(user.get().getEmail());
        assertThat(saveduser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenSaveSeveralThenGetAll() {
        var user1 = sql2oUserRepository.save(new User(1, "name1@mail.ru", "name1", "password"));
        var user2 = sql2oUserRepository.save(new User(2, "name2@mail.ru", "name2", "password"));
        var user3 = sql2oUserRepository.save(new User(3, "name3@mail.ru", "name3", "password"));
        var result = sql2oUserRepository.findAll();
        assertThat(result).isEqualTo(List.of(user1.get(), user2.get(), user3.get()));
    }

    @Test
    public void whenSaveSameMailThenGetError() {
        var user1 = sql2oUserRepository.save(new User(1, "name", "name1", "password"));
        assertThat(user1).isNotEmpty();
        Throwable exception = assertThrows(Exception.class, () -> {
            sql2oUserRepository.save(new User(2, "name", "name1", "password"));
        });
        assertThat(exception.getMessage()).containsIgnoringCase("Unique index or primary key violation");
    }

}