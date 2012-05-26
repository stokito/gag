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

import java.lang.annotation.Inherited;

/**
 * Causes the annotated code to be implemented via the Infinite Improbability
 * Drive at improbability factor equal to the specified base to the specified
 * power.
 * <p/>
 * Reserve this annotation for emergencies.
 * Say you've pushed off that impossible task, you have no time left and your team lead is screaming code freeze.
 * Rather than throwing in the towel, put this annotation on your unimplemented interface and pray the Infinite Improbability Drive activates in time to save your hide.
 * <pre>
 * &#064;InfiniteImprobabilityDriveFactor(base = 2, power = 43112609)
 * public interface Salesman {
 *     List&lt;Road&gt; travelShortestPath(List&lt;City&gt; cities);
 * }
 * </pre>
 */
@Inherited
public @interface InfiniteImprobabilityDriveFactor {
    int base();

    long power();
}
