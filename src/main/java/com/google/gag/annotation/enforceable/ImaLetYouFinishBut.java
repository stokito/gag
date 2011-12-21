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

import com.google.gag.annotation.team.HonorableMention;
import com.google.gag.annotation.team.Visionary;

/**
 * Causes the annotated method to first execute the parameterless method
 * indicated by the annotation's value attribute. For example:
 * 
 * <pre>
 *   &#064;ImaLetYouFinishBut("interrupt")
 *   public void deliver(AcceptanceSpeech speech) {
 *     crowd.listen(speech);
 *   }
 *   
 *   public void interrupt() {
 *     crowd.listen("Kindly allow me to express my own opinion first.");
 *   }
 * </pre>
 * 
 * will cause the <code>deliver</code> method to first call the
 * <code>interrupt</code> method.
 * 
 * <p>TODO: Enforce this annotation.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Visionary("obreerbo")
@HonorableMention("emma.vartdal")
public @interface ImaLetYouFinishBut {
  String value();
}
