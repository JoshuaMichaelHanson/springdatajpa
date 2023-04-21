/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.querydsl.chapternineteen.chapter4;

import com.querydsl.chapternineteen.chapter4.model.UserChapter4;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersUsingQueriesTest extends Chapter4ApplicationTests {

    @Test
    void testFindAll() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findAll();
        assertEquals(10, userChapter4s.size());
    }

    @Test
    void testFindUser() {
        UserChapter4 beth = userChapter4Repository.findByUsername("beth");
        assertEquals("beth", beth.getUsername());
    }

    @Test
    void testFindAllByOrderByUsernameAsc() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findAllByOrderByUsernameAsc();
        assertAll(() -> assertEquals(10, userChapter4s.size()),
                () -> assertEquals("beth", userChapter4s.get(0).getUsername()),
                () -> assertEquals("stephanie", userChapter4s.get(userChapter4s.size() - 1).getUsername()));
    }

    @Test
    void testFindByRegistrationDateBetween() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findByRegistrationDateBetween(
                LocalDate.of(2020, Month.JULY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31));
        assertEquals(4, userChapter4s.size());
    }

    @Test
    void testFindByUsernameEmail() {
        List<UserChapter4> usersList1 = userChapter4Repository.findByUsernameAndEmail("mike", "mike@somedomain.com");
        List<UserChapter4> usersList2 = userChapter4Repository.findByUsernameOrEmail("mike", "beth@somedomain.com");
        List<UserChapter4> usersList3 = userChapter4Repository.findByUsernameAndEmail("mike", "beth@somedomain.com");
        List<UserChapter4> usersList4 = userChapter4Repository.findByUsernameOrEmail("beth", "beth@somedomain.com");

        assertAll(
                () -> assertEquals(1, usersList1.size()),
                () -> assertEquals(2, usersList2.size()),
                () -> assertEquals(0, usersList3.size()),
                () -> assertEquals(1, usersList4.size())
        );
    }

    @Test
    void testFindByUsernameIgnoreCase() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findByUsernameIgnoreCase("MIKE");

        assertAll(
                () -> assertEquals(1, userChapter4s.size()),
                () -> assertEquals("mike", userChapter4s.get(0).getUsername())
        );
    }

    @Test
    void testFindByLevelOrderByUsernameDesc() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findByLevelOrderByUsernameDesc(1);

        assertAll(
                () -> assertEquals(2, userChapter4s.size()),
                () -> assertEquals("john", userChapter4s.get(0).getUsername()),
                () -> assertEquals("burk", userChapter4s.get(1).getUsername())
        );
    }

    @Test
    void testFindByLevelGreaterThanEqual() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findByLevelGreaterThanEqual(3);

        assertEquals(5, userChapter4s.size());
    }

    @Test
    void testFindByUsername() {
        List<UserChapter4> usersContaining = userChapter4Repository.findByUsernameContaining("ar");
        List<UserChapter4> usersLike = userChapter4Repository.findByUsernameLike("%ar%");
        List<UserChapter4> usersStarting = userChapter4Repository.findByUsernameStartingWith("b");
        List<UserChapter4> usersEnding = userChapter4Repository.findByUsernameEndingWith("ie");

        assertAll(
                () -> assertEquals(2, usersContaining.size()),
                () -> assertEquals(2, usersLike.size()),
                () -> assertEquals(2, usersStarting.size()),
                () -> assertEquals(2, usersEnding.size())
        );
    }

    @Test
    void testFindByActive() {
        List<UserChapter4> usersActive = userChapter4Repository.findByActive(true);
        List<UserChapter4> usersNotActive = userChapter4Repository.findByActive(false);

        assertAll(
                () -> assertEquals(8, usersActive.size()),
                () -> assertEquals(2, usersNotActive.size())
        );
    }

    @Test
    void testFindByRegistrationDateInNotIn() {
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 18);
        LocalDate date2 = LocalDate.of(2021, Month.JANUARY, 5);

        List<LocalDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);

        List<UserChapter4> usersList1 = userChapter4Repository.findByRegistrationDateIn(dates);
        List<UserChapter4> usersList2 = userChapter4Repository.findByRegistrationDateNotIn(dates);

        assertAll(
                () -> assertEquals(3, usersList1.size()),
                () -> assertEquals(7, usersList2.size())
        );
    }
}
