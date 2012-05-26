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

package com.google.gag.annotation.remark;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 * &#064;LOL @Facepalm
 * &#064;WTF("just use Collections.reverse()")
 * &#064;Booyah
 * private static &lt;T&gt; void invertOrdering(List&lt;T&gt; list) {
 *     for (int i = 0; i < list.size() / 2; i++) {
 *         int j = list.size() - 1 - i;
 *         T item1 = list.get(i);
 *         T item2 = list.get(j);
 *         list.set(i, item2);
 *         list.set(j, item1);
 *     }
 * }
 * </pre>
 *
 * @see Facepalm
 * @see WTF
 * @see Booyah
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LOL {
    String value() default "";
}
