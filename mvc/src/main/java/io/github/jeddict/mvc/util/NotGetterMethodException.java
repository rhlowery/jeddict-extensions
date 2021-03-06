/**
 * Copyright 2013-2019 the original author or authors from the Jeddict project (https://jeddict.github.io/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.jeddict.mvc.util;

public class NotGetterMethodException extends Exception {

    /**
     * Creates a new instance of <code>NotGetterMethodException</code> without
     * detail message.
     */
    public NotGetterMethodException() {
        super("Bean method is not a valid getter method. It's name should begin with \"get\" or \"is\".");
    }

    /**
     * Constructs an instance of <code>NotGetterMethodException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotGetterMethodException(String msg) {
        super(msg);
    }
}
