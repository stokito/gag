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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gag.annotation.team.Visionary;
import com.google.gag.enumeration.Stop;

/**
 * Replaces the implementation of the annotated method to instead print "Stop"
 * along with the specified reason to standard out. For example, calling the
 * method:
 * 
 * <pre>
 *   &#064;CantTouchThis(Stop.HAMMERTIME)
 *   public void tryToTouchThis() {
 *     System.out.println("This has been touched.");
 *   }
 * </pre>
 * 
 * will cause "Stop, HAMMERTIME" to be printed to standard out.
 * 
 * <p>Methods returning primitive numeric values return the equivalent of 0;
 * methods returning boolean return false; methods returning objects return
 * null.
 * 
 * <p>TODO: Enforce this annotation.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Visionary("Jšrn Zaefferer")
public @interface CantTouchThis {
  Stop value();
}
