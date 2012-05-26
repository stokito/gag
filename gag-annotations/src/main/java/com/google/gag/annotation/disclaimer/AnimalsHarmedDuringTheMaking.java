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

package com.google.gag.annotation.disclaimer;

/**
 * Discloses the number of animals were harmed during the making of the
 * annotated code.
 * <p>
 * The @AnimalsHarmedDuringTheMaking annotation allows you to disclose the mistreatment that befell any animals during the implementation of your code.
 * <pre>
 * &#064;AnimalsHarmedDuringTheMaking(
 *  number = 1,
 *  animal = "hamster",
 *  disclosure = "didn't feed Fermie for 2 days to finish this on time")
 * public class ConstantTimePrimalityTest {
 * }
 * </pre>
 */
public @interface AnimalsHarmedDuringTheMaking {
  int number();
  String animal() default "";
  String disclosure() default "";
}
