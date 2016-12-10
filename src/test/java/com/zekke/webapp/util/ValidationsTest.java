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
import static org.assertj.core.api.Assertions.fail;

public class ValidationsTest {

    private static final String ERROR_ARG_NULL_KEY = "error.arg.null";
    private static final String ERROR_ARG_EMPTY_KEY = "error.arg.empty";
    private static final String ERROR_ARG_BLANK_KEY = "error.arg.blank";
    private static final String ARG_NAME = "arg";
    private static final String STR_WITH_CONTENT = "A valid string";
    private static final Object NON_NULL_OBJ = new Object();

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonNull() {
        assertThatThrownBy(() -> Validations.requireNonNull(null, ERROR_ARG_NULL_KEY, ARG_NAME)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldNotThrowAnyExceptionWhenArgIsNotNullInRequireNonNull() {
        try {
            Validations.requireNonNull(NON_NULL_OBJ, ERROR_ARG_NULL_KEY, ARG_NAME);
        } catch (Exception ex) {
            fail("Unexpected exception", ex);
        }
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonEmpty() {
        assertThatThrownBy(() -> Validations.requireNonEmpty(null, ERROR_ARG_EMPTY_KEY, ARG_NAME)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenArgIsEmptyInRequireNonEmpty() {
        assertThatThrownBy(() -> Validations.requireNonEmpty(Strings.EMPTY, ERROR_ARG_EMPTY_KEY, ARG_NAME)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test(dataProvider = "nonEmptyStrings")
    public void shouldNotThrowAnyExceptionWhenArgIsNotEmptyInRequireNonEmpty(String nonEmpty) {
        try {
            Validations.requireNonEmpty(nonEmpty, ERROR_ARG_NULL_KEY, ARG_NAME);
        } catch (Exception ex) {
            fail("Unexpected exception", ex);
        }
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenArgIsNullInRequireNonBlank() {
        assertThatThrownBy(() -> Validations.requireNonBlank(null, ERROR_ARG_BLANK_KEY, ARG_NAME)).isInstanceOf(NullPointerException.class);
    }

    @Test(dataProvider = "blankStrings")
    public void shouldThrowIllegalArgumentExceptionWhenArgIsBlankInRequireNonBlank(String blank) {
        assertThatThrownBy(() -> Validations.requireNonBlank(blank, ERROR_ARG_BLANK_KEY, ARG_NAME)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotThrowAnyExceptionWhenArgIsNotEmptyInRequireNonBlank() {
        try {
            Validations.requireNonBlank(STR_WITH_CONTENT, ERROR_ARG_NULL_KEY, ARG_NAME);
        } catch (Exception ex) {
            fail("Unexpected exception", ex);
        }
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

    @DataProvider
    public Object[][] nonEmptyStrings() {
        return new Object[][]{
                {STR_WITH_CONTENT},
                {Strings.BLANK_SPACE},
                {Strings.NEW_LINE},
                {Strings.TAB},
        };
    }
}
