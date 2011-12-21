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

package com.google.gag.demo;

import com.google.gag.annotation.enforceable.AnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything;
import com.google.gag.annotation.enforceable.Noop;
import com.google.gag.annotation.enforceable.Roulette;
import com.google.gag.annotation.enforceable.ThisHadBetterBe;
import com.google.gag.annotation.enforceable.ThisHadBetterNotBe;
import com.google.gag.enumeration.Property;

public class Demo {

  public boolean acceptOnlyNull(@ThisHadBetterBe(Property.NULL) Object o) {
    return o == null;
  }

  public double sqrt(@ThisHadBetterNotBe(Property.NEGATIVE) double x) {
    return Math.sqrt(x);
  }

  @Noop
  public String echo(String message) {
    return message;
  }

  public String shoutAnswer(@AnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything int answer) {
    return "The answer is " + answer + "!!";
  }

  @Roulette(probability=0.999, message="This exception courtesy of Roulette")
  public void doSeeminglyNothing() {
  }
}
