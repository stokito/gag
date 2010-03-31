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

import static com.google.gag.enumeration.Property.NEGATIVE;
import static com.google.gag.enumeration.Property.NULL;
import static com.google.gag.enumeration.Property.POSITIVE;
import static com.google.gag.enumeration.Property.THE_BLUE_PILL;
import static com.google.gag.enumeration.Property.THE_RED_PILL;
import static com.google.gag.enumeration.Property.THE_STOLEN_DEATH_STAR_PLANS;
import static com.google.gag.enumeration.Property.ZERO;

import com.google.gag.annotation.enforceable.ThisHadBetterNotBe;

public class ThisHadBetterNotBeTestClass {

  public void doNotAcceptNegInt(@ThisHadBetterNotBe(NEGATIVE) int i) {}
  public void doNotAcceptNegLong(@ThisHadBetterNotBe(NEGATIVE) long i) {}
  public void doNotAcceptNegShort(@ThisHadBetterNotBe(NEGATIVE) short i) {}
  public void doNotAcceptNegDouble(@ThisHadBetterNotBe(NEGATIVE) double i) {}
  public void doNotAcceptNegFloat(@ThisHadBetterNotBe(NEGATIVE) float i) {}
  public void doNotAcceptNegObjectInteger(@ThisHadBetterNotBe(NEGATIVE) Integer i) {}
  public void doNotAcceptNegObjectLong(@ThisHadBetterNotBe(NEGATIVE) Long i) {}
  public void doNotAcceptNegObjectShort(@ThisHadBetterNotBe(NEGATIVE) Short i) {}
  public void doNotAcceptNegObjectDouble(@ThisHadBetterNotBe(NEGATIVE) Double i) {}
  public void doNotAcceptNegObjectFloat(@ThisHadBetterNotBe(NEGATIVE) Float i) {}

  public void doNotAcceptPosInt(@ThisHadBetterNotBe(POSITIVE) int i) {}
  public void doNotAcceptPosLong(@ThisHadBetterNotBe(POSITIVE) long i) {}
  public void doNotAcceptPosShort(@ThisHadBetterNotBe(POSITIVE) short i) {}
  public void doNotAcceptPosDouble(@ThisHadBetterNotBe(POSITIVE) double i) {}
  public void doNotAcceptPosFloat(@ThisHadBetterNotBe(POSITIVE) float i) {}
  public void doNotAcceptPosObjectInteger(@ThisHadBetterNotBe(POSITIVE) Integer i) {}
  public void doNotAcceptPosObjectLong(@ThisHadBetterNotBe(POSITIVE) Long i) {}
  public void doNotAcceptPosObjectShort(@ThisHadBetterNotBe(POSITIVE) Short i) {}
  public void doNotAcceptPosObjectDouble(@ThisHadBetterNotBe(POSITIVE) Double i) {}
  public void doNotAcceptPosObjectFloat(@ThisHadBetterNotBe(POSITIVE) Float i) {}

  public void doNotAcceptZeroInt(@ThisHadBetterNotBe(ZERO) int i) {}
  public void doNotAcceptZeroLong(@ThisHadBetterNotBe(ZERO) long i) {}
  public void doNotAcceptZeroShort(@ThisHadBetterNotBe(ZERO) short i) {}
  public void doNotAcceptZeroDouble(@ThisHadBetterNotBe(ZERO) double i) {}
  public void doNotAcceptZeroFloat(@ThisHadBetterNotBe(ZERO) float i) {}
  public void doNotAcceptZeroObjectInteger(@ThisHadBetterNotBe(ZERO) Integer i) {}
  public void doNotAcceptZeroObjectLong(@ThisHadBetterNotBe(ZERO) Long i) {}
  public void doNotAcceptZeroObjectShort(@ThisHadBetterNotBe(ZERO) Short i){}
  public void doNotAcceptZeroObjectDouble(@ThisHadBetterNotBe(ZERO) Double i) {}
  public void doNotAcceptZeroObjectFloat(@ThisHadBetterNotBe(ZERO) Float i) {}

  public void doNotAcceptNullObject(@ThisHadBetterNotBe(NULL) Object o) {}

  public void doNotAcceptTheBluePill(@ThisHadBetterNotBe(THE_BLUE_PILL) Pill pill) {}
  public void doNotAcceptTheRedPill(@ThisHadBetterNotBe(THE_RED_PILL) Pill pill) {}
  public void doNotAcceptTheBluePillObj(@ThisHadBetterNotBe(THE_BLUE_PILL) Object o) {}
  public void doNotAcceptTheRedPillObj(@ThisHadBetterNotBe(THE_RED_PILL) Object o) {}

  public void doNotAcceptStolenDeathStarPlans(
      @ThisHadBetterNotBe(THE_STOLEN_DEATH_STAR_PLANS) Object object) {
  }
}
