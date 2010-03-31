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

package com.google.gag.agent;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.gag.annotation.enforceable.Noop;
import com.google.gag.instrument.ClassGenerator;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.MethodInfo;

public class NoopGenerator extends ClassGenerator {

  private static final Type NOOP_TYPE = Type.getType(Noop.class);

  @Override
  protected boolean canInstrument(ClassInfo classInfo) {
    return classInfo.hasAnno(NOOP_TYPE) || classInfo.hasMethodAnnoAnywhere(NOOP_TYPE);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    boolean isNoopForAll = classInfo().getAnnoFor(NOOP_TYPE) != null;
    MethodVisitor mv = writer().visitMethod(access, name, desc, sig, exceptions);
    mv.visitCode();

    MethodInfo method = classInfo().getMethod(name, desc);
    boolean isConstructor = "<init>".equals(method.getName());
    boolean isNoopForMethod = method.getAnnoFor(NOOP_TYPE) != null;

    if (!isConstructor && (isNoopForAll || isNoopForMethod)) {
      Type returnType = Type.getReturnType(desc);
      switch (returnType.getSort()) {
      case Type.VOID:
        mv.visitInsn(RETURN);
        break;
      case Type.OBJECT:
      case Type.ARRAY:
        mv.visitInsn(Opcodes.ACONST_NULL);
        mv.visitInsn(ARETURN);
        break;
      case Type.DOUBLE:
        mv.visitInsn(DCONST_0);
        mv.visitInsn(DRETURN);
        break;
      case Type.FLOAT:
        mv.visitInsn(FCONST_0);
        mv.visitInsn(FRETURN);
        break;
      case Type.LONG:
        mv.visitInsn(LCONST_0);
        mv.visitInsn(LRETURN);
        break;
      case Type.INT:
      case Type.SHORT:
      case Type.CHAR:
      case Type.BYTE:
      case Type.BOOLEAN:
        mv.visitInsn(ICONST_0);
        mv.visitInsn(IRETURN);
        break;
      }
      setInstrumented(true);
    }

    mv.visitEnd();
    return mv;
  }
}
