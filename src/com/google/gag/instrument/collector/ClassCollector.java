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
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.FieldInfo;
import com.google.gag.instrument.info.MethodInfo;

public class ClassCollector implements ClassVisitor {

  private ClassInfo.Maker classMaker;

  @Override
  public void visit(int version, int access, String name, String sig, String superName,
      String[] interfaces) {

    classMaker = new ClassInfo.Maker(version, access, name, sig, superName, interfaces);
  }

  @Override
  public void visitSource(String source, String debug) {
  }

  @Override
  public void visitOuterClass(String owner, String name, String desc) {
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    AnnoInfo.Maker annoMaker = new AnnoInfo.Maker(desc);
    classMaker.addAnno(annoMaker.getAnnoInfo());
    return new AnnoCollector(annoMaker);
  }

  @Override
  public void visitAttribute(Attribute attr) {
  }

  @Override
  public void visitInnerClass(String name, String outerName, String innerName, int access) {
  }

  @Override
  public FieldVisitor visitField(int access, String name, String desc, String sig, Object value) {
    FieldInfo.Maker fieldMaker = new FieldInfo.Maker(access, name, desc, sig, value);
    classMaker.addField(fieldMaker.getFieldInfo());
    return new FieldCollector(fieldMaker);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    MethodInfo.Maker methodMaker = new MethodInfo.Maker(access, name, desc, sig, exceptions);
    classMaker.addMethod(methodMaker.getMethodInfo());
    return new MethodCollector(methodMaker);
  }

  @Override
  public void visitEnd() {
  }

  public ClassInfo getClassInfo() {
    return classMaker.getClassInfo();
  }
}
