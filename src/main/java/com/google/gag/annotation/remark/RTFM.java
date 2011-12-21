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

import com.google.gag.annotation.team.Visionary;

/**
 * Indicates that the author of the annotated code is strongly encouraged to
 * read the manual at the specified URL. For example:
 * 
 * <pre>
 *   &#064;RTFM("http://java.sun.com/javase/6/docs/api/java/lang/String.html")
 *   private static String stripPeriods(String s) {
 *     return s.replaceAll(".", "");
 *   }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Visionary("andy.tokarev")
public @interface RTFM {
  String value() default "";
}
