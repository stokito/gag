/**
 * Copyright 2010 Google Inc.
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

package com.github.stokito.gag.annotation.remark;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Java is a verbose language. Sometimes you're stuck using it. Sometimes you want to express your frustration. Sure:
 * <pre>
 * &#064;ThisWouldBeOneLineIn(
 *     language = "haskell"
 *     toWit = "product [1..n]")
 * public int factorial(int n) {
 *     int fac = 1;
 *     for (int i = 1; i <= n; i++) {
 *         fac *= i;
 *     }
 *     return fac;
 * }
 * </pre>
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ThisWouldBeOneLineIn {
    String language();

    String toWit();
}
