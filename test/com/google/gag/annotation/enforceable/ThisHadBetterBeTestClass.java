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

import com.google.gag.annotation.enforceable.ThisHadBetterBe;

public class ThisHadBetterBeTestClass {

  public void acceptNegInt(@ThisHadBetterBe(NEGATIVE) int i) {}
  public void acceptNegLong(@ThisHadBetterBe(NEGATIVE) long i) {}
  public void acceptNegShort(@ThisHadBetterBe(NEGATIVE) short i) {}
  public void acceptNegDouble(@ThisHadBetterBe(NEGATIVE) double i) {}
  public void acceptNegFloat(@ThisHadBetterBe(NEGATIVE) float i) {}
  public void acceptNegObjectInteger(@ThisHadBetterBe(NEGATIVE) Integer i) {}
  public void acceptNegObjectLong(@ThisHadBetterBe(NEGATIVE) Long i) {}
  public void acceptNegObjectShort(@ThisHadBetterBe(NEGATIVE) Short i) {}
  public void acceptNegObjectDouble(@ThisHadBetterBe(NEGATIVE) Double i) {}
  public void acceptNegObjectFloat(@ThisHadBetterBe(NEGATIVE) Float i) {}

  public void acceptPosInt(@ThisHadBetterBe(POSITIVE) int i) {}
  public void acceptPosLong(@ThisHadBetterBe(POSITIVE) long i) {}
  public void acceptPosShort(@ThisHadBetterBe(POSITIVE) short i) {}
  public void acceptPosDouble(@ThisHadBetterBe(POSITIVE) double i) {}
  public void acceptPosFloat(@ThisHadBetterBe(POSITIVE) float i) {}
  public void acceptPosObjectInteger(@ThisHadBetterBe(POSITIVE) Integer i) {}
  public void acceptPosObjectLong(@ThisHadBetterBe(POSITIVE) Long i) {}
  public void acceptPosObjectShort(@ThisHadBetterBe(POSITIVE) Short i) {}
  public void acceptPosObjectDouble(@ThisHadBetterBe(POSITIVE) Double i) {}
  public void acceptPosObjectFloat(@ThisHadBetterBe(POSITIVE) Float i) {}

  public void acceptZeroInt(@ThisHadBetterBe(ZERO) int i) {}
  public void acceptZeroLong(@ThisHadBetterBe(ZERO) long i) {}
  public void acceptZeroShort(@ThisHadBetterBe(ZERO) short i) {}
  public void acceptZeroDouble(@ThisHadBetterBe(ZERO) double i) {}
  public void acceptZeroFloat(@ThisHadBetterBe(ZERO) float i) {}
  public void acceptZeroObjectInteger(@ThisHadBetterBe(ZERO) Integer i) {}
  public void acceptZeroObjectLong(@ThisHadBetterBe(ZERO) Long i) {}
  public void acceptZeroObjectShort(@ThisHadBetterBe(ZERO) Short i){}
  public void acceptZeroObjectDouble(@ThisHadBetterBe(ZERO) Double i) {}
  public void acceptZeroObjectFloat(@ThisHadBetterBe(ZERO) Float i) {}

  public void acceptNullObject(@ThisHadBetterBe(NULL) Object o) {}

  public void acceptBluePill(@ThisHadBetterBe(THE_BLUE_PILL) Pill pill) {}
  public void acceptRedPill(@ThisHadBetterBe(THE_RED_PILL) Pill pill) {}

  public void acceptStolenDeathStarPlans(
      @ThisHadBetterBe(THE_STOLEN_DEATH_STAR_PLANS) DeathStarPlans plans) {
  }
}
