package com.querydsl.chapternineteen.chapter4;

import com.querydsl.chapternineteen.chapter4.model.UserChapter4;
import com.querydsl.chapternineteen.chapter4.repositories.UserChapter4Repository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class Chapter4ApplicationTests {
    @Autowired
    UserChapter4Repository userChapter4Repository;

    @BeforeAll
    void beforeAll() {
        userChapter4Repository.saveAll(generateUsers());
    }

    private static List<UserChapter4> generateUsers() {
        List<UserChapter4> userChapter4s = new ArrayList<>();

        UserChapter4 john = new UserChapter4("john", LocalDate.of(2020, Month.APRIL, 13));
        john.setEmail("john@somedomain.com");
        john.setLevel(1);
        john.setActive(true);

        UserChapter4 mike = new UserChapter4("mike", LocalDate.of(2020, Month.JANUARY, 18));
        mike.setEmail("mike@somedomain.com");
        mike.setLevel(3);
        mike.setActive(true);

        UserChapter4 james = new UserChapter4("james", LocalDate.of(2020, Month.MARCH, 11));
        james.setEmail("james@someotherdomain.com");
        james.setLevel(3);
        james.setActive(false);

        UserChapter4 katie = new UserChapter4("katie", LocalDate.of(2021, Month.JANUARY, 5));
        katie.setEmail("katie@somedomain.com");
        katie.setLevel(5);
        katie.setActive(true);

        UserChapter4 beth = new UserChapter4("beth", LocalDate.of(2020, Month.AUGUST, 3));
        beth.setEmail("beth@somedomain.com");
        beth.setLevel(2);
        beth.setActive(true);

        UserChapter4 julius = new UserChapter4("julius", LocalDate.of(2021, Month.FEBRUARY, 9));
        julius.setEmail("julius@someotherdomain.com");
        julius.setLevel(4);
        julius.setActive(true);

        UserChapter4 darren = new UserChapter4("darren", LocalDate.of(2020, Month.DECEMBER, 11));
        darren.setEmail("darren@somedomain.com");
        darren.setLevel(2);
        darren.setActive(true);

        UserChapter4 marion = new UserChapter4("marion", LocalDate.of(2020, Month.SEPTEMBER, 23));
        marion.setEmail("marion@someotherdomain.com");
        marion.setLevel(2);
        marion.setActive(false);

        UserChapter4 stephanie = new UserChapter4("stephanie", LocalDate.of(2020, Month.JANUARY, 18));
        stephanie.setEmail("stephanie@someotherdomain.com");
        stephanie.setLevel(4);
        stephanie.setActive(true);

        UserChapter4 burk = new UserChapter4("burk", LocalDate.of(2020, Month.NOVEMBER, 28));
        burk.setEmail("burk@somedomain.com");
        burk.setLevel(1);
        burk.setActive(true);

        userChapter4s.add(john);
        userChapter4s.add(mike);
        userChapter4s.add(james);
        userChapter4s.add(katie);
        userChapter4s.add(beth);
        userChapter4s.add(julius);
        userChapter4s.add(darren);
        userChapter4s.add(marion);
        userChapter4s.add(stephanie);
        userChapter4s.add(burk);

        return userChapter4s;
    }

    @AfterAll
    void afterAll() {
        userChapter4Repository.deleteAll();
    }
}
