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

public class RunDemo {

  public static void main(String[] args) {
    Demo demo = new Demo();

    System.out.println("demo.srqt(4.0) = " + demo.sqrt(4.0));
    System.out.println("demo.srqt(0.0) = " + demo.sqrt(0.0));
    System.out.print("demo.srqt(-1.0) = ");
    try {
      System.out.println(demo.sqrt(-1.0));
    } catch (Exception e) {
      System.out.println();
      e.printStackTrace();
    }

    System.out.println("demo.acceptOnlyNull(null) = " + demo.acceptOnlyNull(null));
    System.out.print("demo.acceptOnlyNull(new Object()) = ");
    try {
      System.out.println(demo.acceptOnlyNull(new Object()));
    } catch (Exception e) {
      System.out.println();
      e.printStackTrace();
    }

    System.out.println("demo.echo(\"woot\") = " + demo.echo("woot"));

    System.out.println("demo.shoutAnswer(6 * 9) = " + demo.shoutAnswer(6 * 9));
  }
}