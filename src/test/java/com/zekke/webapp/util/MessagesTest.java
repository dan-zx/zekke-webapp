/*
 * Copyright 2013-2017 Daniel Pedraza-Arcega
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zekke.webapp.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagesTest {

    @Test(dataProvider = "messagesWithoutArgs")
    public void shouldGetExpectedMessageWithNoExceptions(String messageKey, String expectedMessage) {
        String actualMessage = Messages.getMessage(messageKey);
        assertThat(actualMessage).isNotNull().isNotEmpty().isEqualTo(expectedMessage);
    }

    @Test
    public void shouldGetMessageUsingArguments() {
        List<String> args = Arrays.asList("a1arg");
        String actualMessage = Messages.getMessage("test.message_with_args", args.get(0));
        assertThat(actualMessage).isNotNull().isNotEmpty().doesNotMatch("\\?\\?\\?.+\\?\\?\\?").contains(args);
    }

    @DataProvider
    public Object[][] messagesWithoutArgs() {
        return new Object[][]{
                {"test.message", "Test message"},
                {"non_existing.message", "???non_existing.message???"}
        };
    }
}
