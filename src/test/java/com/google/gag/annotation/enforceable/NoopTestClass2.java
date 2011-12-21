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

import java.util.List;

import com.google.gag.annotation.enforceable.Noop;

@Noop
public class NoopTestClass2 {

  public void addSomethingToList(List<String> list) {
    list.add("foo");
  }

  public short returnShortOne() {
    return 1;
  }

  public int returnIntOne() {
    return 1;
  }

  public double returnDoubleOne() {
    return 1.0;
  }

  public float returnFloatOne() {
    return 1f;
  }

  public boolean returnTrue() {
    return true;
  }

  public char returnCharA() {
    return 'a';
  }

  public long returnLong() {
    return 1L;
  }

  public String returnNonNullString() {
    return "foo";
  }

  public int[] returnNonNullArray() {
    return new int[] { 1, 2, 3 };
  }
}
