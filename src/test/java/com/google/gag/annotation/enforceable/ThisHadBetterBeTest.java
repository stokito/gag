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

import junit.framework.TestCase;

public class ThisHadBetterBeTest extends TestCase {

  private ThisHadBetterBeTestClass testClass = new ThisHadBetterBeTestClass();
  
  /*
   * NEGATIVE tests
   */

  public void testThisHadBetterBeNegativeSuccesses() {
    testClass.acceptNegInt(-1);
    testClass.acceptNegLong(-1L);
    testClass.acceptNegShort((short) -1);
    testClass.acceptNegDouble(-1d);
    testClass.acceptNegFloat(-1f);
    testClass.acceptNegObjectInteger(-1);
    testClass.acceptNegObjectLong(-1L);
    testClass.acceptNegObjectShort(new Short((short) -1));
    testClass.acceptNegObjectDouble(-1d);
    testClass.acceptNegObjectFloat(-1f);
  }

  public void testThisHadBetterBeNegativeFailures() {
    doThisHadBetterBeNegativeFailures(1);
    doThisHadBetterBeNegativeFailures(0);
  }


  /*
   * POSITIVE tests
   */

  public void testThisHadBetterBePositiveSuccesses() {
    testClass.acceptPosInt(1);
    testClass.acceptPosLong(1L);
    testClass.acceptPosShort((short) 1);
    testClass.acceptPosDouble(1d);
    testClass.acceptPosFloat(1f);
    testClass.acceptPosObjectInteger(1);
    testClass.acceptPosObjectLong(1L);
    testClass.acceptPosObjectShort(new Short((short) 1));
    testClass.acceptPosObjectDouble(1d);
    testClass.acceptPosObjectFloat(1f);
  }

  public void testThisHadBetterBePositiveFailures() {
    doThisHadBetterBePositiveFailures(-1);
    doThisHadBetterBePositiveFailures(0);
  }


  /*
   * ZERO tests
   */

  public void testThisHadBetterBeZeroSuccesses() {
    testClass.acceptZeroInt(0);
    testClass.acceptZeroLong(0L);
    testClass.acceptZeroShort((short) 0);
    testClass.acceptZeroDouble(0d);
    testClass.acceptZeroFloat(0f);
    testClass.acceptZeroObjectInteger(0);
    testClass.acceptZeroObjectLong(0L);
    testClass.acceptZeroObjectShort(new Short((short) 0));
    testClass.acceptZeroObjectDouble(0d);
    testClass.acceptZeroObjectFloat(0f);
  }

  public void testThisHadBetterBeZeroFailures() {
    doThisHadBetterZeroFailures(1);
    doThisHadBetterZeroFailures(-1);
  }


  /*
   * NULL test
   */

  public void testThisHadBetterBeNull() {
    testClass.acceptNullObject(null);

    try {
      testClass.acceptNullObject(new Object());
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNullObject(new int[] {});
      fail();
    } catch(IllegalArgumentException e) {
    }
  }


  /*
   * THE_BLUE_PILL, THE_RED_PILL tests
   */

  public void testThisHadBetterBePill() {
    testClass.acceptBluePill(Pill.BLUE);
    testClass.acceptRedPill(Pill.RED);

    try {
      testClass.acceptRedPill(Pill.BLUE);
      fail();
    } catch (IllegalArgumentException e) {
    }

    try {
      testClass.acceptBluePill(Pill.RED);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  /*
   * THE_STOLEN_DEATH_STAR_PLANS tests
   */

  public void testThisHadBetterBeStolenDeathStarPlans() {
    testClass.acceptStolenDeathStarPlans(new DeathStarPlans(true));

    try {
      testClass.acceptStolenDeathStarPlans(new DeathStarPlans(false));
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  /*
   * Helper methods
   */

  private void doThisHadBetterBeNegativeFailures(int nonNegInt) {
    try {
      testClass.acceptNegInt(nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegLong(nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegShort((short) nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegDouble(nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegFloat(nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegObjectInteger(nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegObjectLong((long) nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegObjectShort((short) nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegObjectDouble((double) nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptNegObjectFloat((float) nonNegInt);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }

  private void doThisHadBetterBePositiveFailures(int nonPosInt) {
    try {
      testClass.acceptPosInt(nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosLong(nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosShort((short) nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosDouble(nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosFloat(nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosObjectInteger(nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosObjectLong((long) nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosObjectShort((short) nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosObjectDouble((double) nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptPosObjectFloat((float) nonPosInt);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }

  private void doThisHadBetterZeroFailures(int nonZeroInt) {
    try {
      testClass.acceptZeroInt(nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroLong(nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroShort((short) nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroDouble(nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroFloat(nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroObjectInteger(nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroObjectLong((long) nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroObjectShort((short) nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroObjectDouble((double) nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.acceptZeroObjectFloat((float) nonZeroInt);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }  
}
