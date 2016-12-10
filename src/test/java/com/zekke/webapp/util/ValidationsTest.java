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

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidationsTest {

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonNull() {
        assertThatThrownBy(() -> Validations.requireNonNull(null, "error.arg.null", "arg")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonEmpty() {
        assertThatThrownBy(() -> Validations.requireNonEmpty(null, "error.arg.empty", "arg")).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenArgIsEmptyInRequireNonEmpty() {
        assertThatThrownBy(() -> Validations.requireNonEmpty(Strings.EMPTY, "error.arg.empty", "arg")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonBlank() {
        assertThatThrownBy(() -> Validations.requireNonBlank(null, "error.arg.blank", "arg")).isInstanceOf(NullPointerException.class);
    }

    @Test(dataProvider = "blankStrings")
    public void shouldThrowIllegalArgumentExceptionWhenArgIsBlankInRequireNonBlank(String blank) {
        assertThatThrownBy(() -> Validations.requireNonBlank(blank, "error.arg.blank", "arg")).isInstanceOf(IllegalArgumentException.class);
    }

    @DataProvider
    public Object[][] blankStrings() {
        return new Object[][]{
                {Strings.EMPTY},
                {Strings.BLANK_SPACE},
                {Strings.NEW_LINE},
                {Strings.TAB},
        };
    }
}
