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

public class ThisHadBetterNotBeTest extends TestCase {

  private ThisHadBetterNotBeTestClass testClass = new ThisHadBetterNotBeTestClass();

  /*
   * NEGATIVE tests
   */

  public void testThisHadBetterNotBeNegativeSuccesses() {
    doThisHadBetterNotBeNegativeSuccesses(0);
    doThisHadBetterNotBeNegativeSuccesses(1);
  }

  public void testThisHadBetterNotBeNegativeFailures() {
    try {
      testClass.doNotAcceptNegInt(-1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegLong(-1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegShort((short) -1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegDouble(-1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegFloat(-1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegObjectInteger(-1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegObjectLong((long) -1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegObjectShort((short) -1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegObjectDouble((double) -1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptNegObjectFloat((float) -1);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }


  /*
   * POSITIVE tests
   */

  public void testThisHadBetterNotBePositiveSuccesses() {
    doThisHadBetterNotBePositiveSuccesses(-1);
    doThisHadBetterNotBePositiveSuccesses(0);
  }

  public void testThisHadBetterNotBePositiveFailures() {
    try {
      testClass.doNotAcceptPosInt(1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosLong(1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosShort((short) 1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosDouble(1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosFloat(1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosObjectInteger(1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosObjectLong((long) 1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosObjectShort((short) 1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosObjectDouble((double) 1);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptPosObjectFloat((float) 1);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }

  /*
   * ZERO tests
   */

  public void testThisHadBetterNotBeZeroSuccesses() {
    doThisHadBetterNotBeZeroSuccesses(-1);
    doThisHadBetterNotBeZeroSuccesses(1);
  }

  public void testThisHadBetterNotBeZeroFailures() {
    try {
      testClass.doNotAcceptZeroInt(0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroLong(0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroShort((short) 0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroDouble(0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroFloat(0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroObjectInteger(0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroObjectLong((long) 0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroObjectShort((short) 0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroObjectDouble((double) 0);
      fail();
    } catch(IllegalArgumentException e) {
    }

    try {
      testClass.doNotAcceptZeroObjectFloat((float) 0);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }


  /*
   * NULL test
   */

  public void testThisHadBetterNotBeNull() {
    testClass.doNotAcceptNullObject(new Object());
    testClass.doNotAcceptNullObject(new int[] {});
    try {
      testClass.doNotAcceptNullObject(null);
      fail();
    } catch(IllegalArgumentException e) {
    }
  }


  /*
   * THE_BLUE_PILL, THE_RED_PILL tests
   */

  public void testThisHadBetterNotBePill() {
    testClass.doNotAcceptTheBluePill(Pill.RED);
    testClass.doNotAcceptTheRedPill(Pill.BLUE);
    testClass.doNotAcceptTheBluePillObj(Pill.RED);
    testClass.doNotAcceptTheBluePillObj(new Object());
    try {
      testClass.doNotAcceptTheRedPill(Pill.RED);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      testClass.doNotAcceptTheRedPillObj(Pill.RED);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }


  /*
   * THE_STOLEN_DEATH_STAR_PLANS tests
   */

  public void testThisHadBetterNotBeStolenDeathStarPlans() {
    testClass.doNotAcceptStolenDeathStarPlans(new Object());
    testClass.doNotAcceptStolenDeathStarPlans(new DeathStarPlans(false));

    try {
      testClass.doNotAcceptStolenDeathStarPlans(new DeathStarPlans(true));
      fail();
    } catch (IllegalArgumentException e) {
    }
  }


  /*
   * Helper methods
   */

  private void doThisHadBetterNotBeNegativeSuccesses(int nonNegInt) {
    testClass.doNotAcceptNegInt(nonNegInt);
    testClass.doNotAcceptNegLong(nonNegInt);
    testClass.doNotAcceptNegShort((short) nonNegInt);
    testClass.doNotAcceptNegDouble(nonNegInt);
    testClass.doNotAcceptNegFloat(nonNegInt);
    testClass.doNotAcceptNegObjectInteger(nonNegInt);
    testClass.doNotAcceptNegObjectLong((long) nonNegInt);
    testClass.doNotAcceptNegObjectShort(new Short((short) nonNegInt));
    testClass.doNotAcceptNegObjectDouble((double) nonNegInt);
    testClass.doNotAcceptNegObjectFloat((float) nonNegInt);
  }

  private void doThisHadBetterNotBePositiveSuccesses(int nonPosInt) {
    testClass.doNotAcceptPosInt(nonPosInt);
    testClass.doNotAcceptPosLong(nonPosInt);
    testClass.doNotAcceptPosShort((short) nonPosInt);
    testClass.doNotAcceptPosDouble(nonPosInt);
    testClass.doNotAcceptPosFloat(nonPosInt);
    testClass.doNotAcceptPosObjectInteger(nonPosInt);
    testClass.doNotAcceptPosObjectLong((long) nonPosInt);
    testClass.doNotAcceptPosObjectShort(new Short((short) nonPosInt));
    testClass.doNotAcceptPosObjectDouble((double) nonPosInt);
    testClass.doNotAcceptPosObjectFloat((float) nonPosInt);
  }

  private void doThisHadBetterNotBeZeroSuccesses(int nonZeroInt) {
    testClass.doNotAcceptZeroInt(nonZeroInt);
    testClass.doNotAcceptZeroLong(nonZeroInt);
    testClass.doNotAcceptZeroShort((short) nonZeroInt);
    testClass.doNotAcceptZeroDouble(nonZeroInt);
    testClass.doNotAcceptZeroFloat(nonZeroInt);
    testClass.doNotAcceptZeroObjectInteger(nonZeroInt);
    testClass.doNotAcceptZeroObjectLong((long) nonZeroInt);
    testClass.doNotAcceptZeroObjectShort(new Short((short) nonZeroInt));
    testClass.doNotAcceptZeroObjectDouble((double) nonZeroInt);
    testClass.doNotAcceptZeroObjectFloat((float) nonZeroInt);
  }
}
