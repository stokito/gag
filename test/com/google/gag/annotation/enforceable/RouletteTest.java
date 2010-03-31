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

public class RouletteTest extends TestCase {

  private RouletteTestClass testClass = new RouletteTestClass();

  /**
   * This is a flaky test. It's expected to not pass with probability (0.75)^20.
   */
  public void testRoulette() {
    try {
      for (int i = 0; i < 20; i++) {
        testClass.doesNothing();
      }
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Something bogus just happened", e.getMessage());
    }
  }
}
