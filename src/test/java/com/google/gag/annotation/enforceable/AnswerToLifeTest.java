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

public class AnswerToLifeTest extends TestCase {

  private AnswerToLifeTestClass testClass = new AnswerToLifeTestClass();
  
  public void testTheAnswer() {
    assertEquals(42, testClass.intAnswer(123));
    assertEquals(42L, testClass.longAnswer(123L));
    assertEquals(42d, testClass.doubleAnswer(123d));
    assertEquals(42f, testClass.floatAnswer(123f));
    assertEquals((byte) 42, testClass.byteAnswer((byte) 123));
    assertEquals((char) 42, testClass.charAnswer('a'));
    assertEquals((short) 42, testClass.shortAnswer((short) 123));

    assertEquals(new Integer(42), testClass.getIntegerAnswer(new Integer(123)));
    assertEquals(new Long(42), testClass.getLongAnswer(new Long(123L)));
    assertEquals(new Double(42), testClass.getDoubleAnswer(new Double(123d)));
    assertEquals(new Float(42), testClass.getFloatAnswer(new Float(123f)));
    assertEquals(new Byte((byte) 42), testClass.getByteAnswer(new Byte((byte) 123)));
    assertEquals(new Short((short) 42), testClass.getShortAnswer(new Short((short) 123)));
    assertEquals(new Character((char) 42), testClass.getCharacterAnswer(new Character('a')));
  }
}
