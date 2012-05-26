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

package com.google.gag.annotation.enforceable;

import com.google.gag.enumeration.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enforces that the annotated parameter has the specified property.
 * <p/>
 * {@link ThisHadBetterBe} and {@link ThisHadBetterNotBe}
 * <p/>
 * These annotations are useful to ensure that your method parameters are what you expect them to be, dagnabbit.
 * If you want a succinct alternatively to bulletproofing your parameters with preconditions, use these.
 * <pre>
 * public void misappropriate(@ThisHadBetterBe(THE_STOLEN_DEATH_STAR_PLANS) DataTape tape) {
 *     r2.record(tape.play());
 *     ship.getEscapePod().insert(r2).jettison();
 * }
 * </pre>
 *
 * @see ThisHadBetterNotBe
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface ThisHadBetterBe {
    Property value();
}
