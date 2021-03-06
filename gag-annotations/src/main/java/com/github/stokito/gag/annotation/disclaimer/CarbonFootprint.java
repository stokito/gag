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

package com.github.stokito.gag.annotation.disclaimer;

import com.github.stokito.gag.enumeration.CO2Units;

import java.lang.annotation.*;

/**
 * Estimates the primary carbon footprint the annotated code is expected to
 * incur during execution.
 * <p/>
 * For environmentally conscious developers, this annotation allows you to specify your primary carbon footprint incurred during normal execution of your code.
 * Valid units are expressed with {@link CO2Units}.
 * <pre>
 * #&064;CarbonFootprint(
 *  value = 6.28318531,
 *  units = GRAMS_PER_MEGAJOULE)
 * public interface Holodeck {
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.TYPE
})
@Inherited
public @interface CarbonFootprint {
    double value();

    CO2Units units();
}
