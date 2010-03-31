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

package com.google.gag.instrument.collector;

import org.objectweb.asm.AnnotationVisitor;

import com.google.gag.instrument.info.AnnoInfo;

public class AnnoCollector implements AnnotationVisitor {

  private AnnoInfo.Maker annoMaker;

  public AnnoCollector(AnnoInfo.Maker annoMaker) {
    this.annoMaker = annoMaker;
  }

  @Override
  public void visit(String name, Object value) {
    annoMaker.addValue(name, value);
  }

  @Override
  public void visitEnum(String name, String desc, String value) {
    // TODO: Support enums properly.
    annoMaker.addValue(name, value);
  }

  @Override
  public AnnotationVisitor visitAnnotation(String name, String desc) {
    // TODO: Support inner annotations.
    return this;
  }

  @Override
  public AnnotationVisitor visitArray(String name) {
    // TODO: Support arrays annotations.
    throw new UnsupportedOperationException();
  }

  @Override
  public void visitEnd() {
  }
}
