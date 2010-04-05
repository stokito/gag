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

package com.google.gag.annotation.team;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.gag.enumeration.ChannelingEntity;
import com.google.gag.enumeration.OpinionOfHumanity;

/**
 * Indicates that the annotated code was influenced by the specified
 * individual. The person's entity type and/or disposition towards humanity
 * can also be indicated. For example:
 * 
 * <pre>
 *   &#064;Channeling(
 *       person = "C. Babbage",
 *       entity = ChannelingEntity.EX_HUMAN)
 *   public class DifferenceEngine {
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Visionary("Mike Samuel")
public @interface Channeling {
  String person();
  ChannelingEntity entity() default ChannelingEntity.UNSPECIFIED;
  OpinionOfHumanity disposition() default OpinionOfHumanity.UNDISCLOSED;
}
