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
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.FieldInfo;
 
public class FieldCollector implements FieldVisitor {

  private final FieldInfo.Maker fieldMaker;

  public FieldCollector(FieldInfo.Maker fieldMaker) {
    this.fieldMaker = fieldMaker;
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    AnnoInfo.Maker annoMaker = new AnnoInfo.Maker(desc);
    fieldMaker.addAnno(annoMaker.getAnnoInfo());
    return new AnnoCollector(annoMaker);
  }

  @Override
  public void visitAttribute(Attribute attr) {
  }

  @Override
  public void visitEnd() {
  }
}
