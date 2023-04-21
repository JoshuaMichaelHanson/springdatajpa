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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryByExampleTest extends Chapter4ApplicationTests {

    @Test
    void testEmailWithQueryByExample() {
        UserChapter4 userChapter4 = new UserChapter4();
        userChapter4.setEmail("@someotherdomain.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());

        Example<UserChapter4> example = Example.of(userChapter4, matcher);

        List<UserChapter4> userChapter4s = userChapter4Repository.findAll(example);

        assertEquals(4, userChapter4s.size());

    }

    @Test
    void testUsernameWithQueryByExample() {
        UserChapter4 userChapter4 = new UserChapter4();
        userChapter4.setUsername("J");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();

        Example<UserChapter4> example = Example.of(userChapter4, matcher);

        List<UserChapter4> userChapter4s = userChapter4Repository.findAll(example);

        assertEquals(3, userChapter4s.size());

    }
}
