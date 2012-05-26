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

package com.github.stokito.gag.annotation.enforceable;

import java.lang.annotation.*;

/**
 * Causes the annotated method to throw the indicated throwable with the
 * specified probability.
 * <p/>
 * Do you have a service method? Feeling neglected by your clients?
 * Add a simple @Roulette and sit back while the method throws exceptions at the specified rate.
 * <pre>
 * &#064;Roulette(
 *     probability = 0.005,
 *     exception = PayYourContractorException,
 *     message = "Courtesy reminder")
 * public Service getRockSolidService() {
 * }
 * </pre>
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Inherited
public @interface Roulette {
    double probability();

    Class<? extends Throwable> exception() default RuntimeException.class;

    String message() default "";
}
