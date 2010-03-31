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

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;

import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.LocalVarInfo;
import com.google.gag.instrument.info.MethodInfo;

public class MethodCollector extends BaseMethodCollector {

  private final MethodInfo.Maker methodMaker;
  private final List<AnnoInfo> paramAnnos = new ArrayList<AnnoInfo>();

  public MethodCollector(MethodInfo.Maker methodMaker) {
    this.methodMaker = methodMaker;
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    AnnoInfo.Maker annoMaker = new AnnoInfo.Maker(desc);
    methodMaker.addAnno(annoMaker.getAnnoInfo());
    return new AnnoCollector(annoMaker);
  }

  @Override
  public AnnotationVisitor visitParameterAnnotation(int param, String desc, boolean visible) {
    AnnoInfo.Maker annoMaker = new AnnoInfo.Maker(param, desc);
    paramAnnos.add(annoMaker.getAnnoInfo());
    return new AnnoCollector(annoMaker);
  }

  @Override
  public void visitLocalVariable(String name, String desc, String sig, Label start, Label end,
      int index) {

    if ("this".equals(name)) {
      return;
    }
    LocalVarInfo.Maker localVarMaker = new LocalVarInfo.Maker(name, desc, index);
    methodMaker.addLocalVarMaker(localVarMaker);
  }

  @Override
  public void visitEnd() {
    for (AnnoInfo anno : paramAnnos) {
      methodMaker.addParamAnno(anno);
    }
  }
}
