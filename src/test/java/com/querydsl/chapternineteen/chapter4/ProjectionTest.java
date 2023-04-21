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

import com.querydsl.chapternineteen.chapter4.model.Projection;
import com.querydsl.chapternineteen.chapter4.model.UserChapter4;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectionTest extends Chapter4ApplicationTests {

    @Test
    void testProjectionUsername() {

        List<Projection.UsernameOnly> users = userChapter4Repository.findByEmail("john@somedomain.com");

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("john", users.get(0).getUsername())
        );
    }

    @Test
    void testProjectionUserSummary() {
        List<Projection.UserSummary> users = userChapter4Repository.findByRegistrationDateAfter(LocalDate.of(2021, Month.FEBRUARY, 1));

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("julius", users.get(0).getUsername()),
                () -> assertEquals("julius julius@someotherdomain.com", users.get(0).getInfo())
        );
    }

    @Test
    void testDynamicProjection() {
        List<Projection.UsernameOnly> usernames = userChapter4Repository.findByEmail("mike@somedomain.com", Projection.UsernameOnly.class);
        List<UserChapter4> userChapter4s = userChapter4Repository.findByEmail("mike@somedomain.com", UserChapter4.class);

        assertAll(
                () -> assertEquals(1, usernames.size()),
                () -> assertEquals("mike", usernames.get(0).getUsername()),
                () -> assertEquals(1, userChapter4s.size()),
                () -> assertEquals("mike", userChapter4s.get(0).getUsername())
        );
    }
}
