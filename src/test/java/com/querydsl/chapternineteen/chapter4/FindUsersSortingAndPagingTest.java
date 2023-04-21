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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersSortingAndPagingTest extends Chapter4ApplicationTests {

    @Test
    void testOrder() {

        UserChapter4 userChapter41 = userChapter4Repository.findFirstByOrderByUsernameAsc();
        UserChapter4 userChapter42 = userChapter4Repository.findTopByOrderByRegistrationDateDesc();
//        Page<User> userPage = userRepository.findTop3ByActive(true, PageRequest.of(1, 3));
        Page<UserChapter4> userPage = userChapter4Repository.findAll(PageRequest.of(1, 3));
        List<UserChapter4> userChapter4s = userChapter4Repository.findFirst2ByLevel(2, Sort.by("registrationDate"));

        assertAll(
                () -> assertEquals("beth", userChapter41.getUsername()),
                () -> assertEquals("julius", userChapter42.getUsername()),
                () -> assertEquals(2, userChapter4s.size()),
                () -> assertEquals(3, userPage.getSize()),
                () -> assertEquals("beth", userChapter4s.get(0).getUsername()),
                () -> assertEquals("marion", userChapter4s.get(1).getUsername())
        );

    }

    @Test
    void testFindByLevel() {
        Sort.TypedSort<UserChapter4> user = Sort.sort(UserChapter4.class);

        List<UserChapter4> userChapter4s = userChapter4Repository.findByLevel(3, user.by(UserChapter4::getRegistrationDate).descending());
        assertAll(
                () -> assertEquals(2, userChapter4s.size()),
                () -> assertEquals("james", userChapter4s.get(0).getUsername())
        );

    }

    @Test
    void testFindByActive() {
        List<UserChapter4> userChapter4s = userChapter4Repository.findByActive(true, PageRequest.of(1, 4, Sort.by("registrationDate")));
        assertAll(
                () -> assertEquals(4, userChapter4s.size()),
                () -> assertEquals("burk", userChapter4s.get(0).getUsername())
        );

    }
}
