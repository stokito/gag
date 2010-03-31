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

package com.google.gag.instrument;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassWriter;

import com.google.gag.instrument.info.ClassInfo;

public abstract class ClassGenerator extends ClassAdapter {
  private ClassWriter writer;
  private ClassInfo classInfo;
  private boolean instrumented;

  public ClassGenerator() {
    super(null);
  }

  protected abstract boolean canInstrument(ClassInfo classInfo);

  final void init(ClassWriter writer, ClassInfo classInfo) {
    this.cv = writer;
    this.writer = writer;
    this.classInfo = classInfo;
    this.instrumented = false;
  }

  protected ClassWriter writer() {
    return writer;
  }

  protected ClassInfo classInfo() {
    return classInfo;
  }

  protected void setInstrumented(boolean instrumented) {
    this.instrumented = instrumented;
  }

  /**
   * Returns true if this {@code ClassGenerator} has instrumented the class
   * bytes as a result of visiting the class.
   */
  protected boolean hasInstrumented() {
    return instrumented;
  }
}
