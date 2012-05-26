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

import com.github.stokito.gag.enumeration.Source;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Ever get the urge to leave a quote? Use {@code @ObligatoryQuote}:
 * <pre>
 * &#064;ObligatoryQuote(
 *     quote = "Remember, raptors run at 10 m/s and they do not know fear."
 *     source = Source.XKCD,
 *     citation = "http://xkcd.com/135/")
 * public Route planRoute(FloorPlan floorPlan, Set&lt;Velociraptor&gt; raptors);
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ObligatoryQuote {
    String quote();

    Source source();

    String other() default "";

    String citation() default "";
}
