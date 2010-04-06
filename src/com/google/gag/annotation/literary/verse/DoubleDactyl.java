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

package com.google.gag.annotation.literary.verse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.google.gag.annotation.team.Visionary;

/**
 * Indicates that the annotated code is a double dactyl. For example:
 *
 * <pre>
 *   &#064;DoubleDactyl
 *   public void createNewTractor() {
 *
 *     tractoryFactory
 *         .tractorConfigurer()
 *         .setTractorPaintjob(
 *             A_BRIGHT_SHINY_RED);
 *
 *     saveTheNewTractor(
 *         AUTOTRANSACTIONALLY);
 *     driveTheNewTractorIn(
 *         new WorkerThread());
 *
 *   }
 * </pre>
 */
@Retention(RetentionPolicy.SOURCE)
@Visionary("Paul Cowan")
public @interface DoubleDactyl {
}
