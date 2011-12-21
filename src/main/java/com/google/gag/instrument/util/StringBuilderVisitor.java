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

package com.google.gag.instrument.util;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class StringBuilderVisitor {
  private static final Type SB = Type.getType(StringBuilder.class);
  private static final String SB_NAME = SB.getInternalName();
  private final MethodVisitor mv;

  public StringBuilderVisitor(MethodVisitor mv) {
    this.mv = mv;
    mv.visitTypeInsn(NEW, SB_NAME);
    mv.visitInsn(DUP);
    mv.visitMethodInsn(INVOKESPECIAL, SB_NAME, "<init>", "()V");
  }

  public void visitAppend(String s) {
    mv.visitLdcInsn(s);
    visitMethod("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
  }

  public void visitAppend(Type paramType, int paramIndex) {
    mv.visitVarInsn(getLoad(paramType), paramIndex);
    visitMethod("append",
        "(" + getAppendType(paramType).getDescriptor() + ")Ljava/lang/StringBuilder;");
  }

  public void visitToString() {
    visitMethod("toString", "()Ljava/lang/String;");
  }

  private void visitMethod(String methodName, String desc) {
    mv.visitMethodInsn(INVOKEVIRTUAL, SB_NAME, methodName, desc);
  }

  private static int getLoad(Type paramType) {
    switch (paramType.getSort()) {
      case Type.INT:
      case Type.SHORT:
      case Type.BYTE:
      case Type.CHAR:
      case Type.BOOLEAN:
        return ILOAD;
      case Type.LONG:
        return LLOAD;
      case Type.DOUBLE:
        return DLOAD;
      case Type.FLOAT:
        return FLOAD;
      default:
        return ALOAD;
    }
  }

  private static Type getAppendType(Type paramType) {
    switch (paramType.getSort()) {
      case Type.INT:
      case Type.BYTE:
      case Type.CHAR:
      case Type.BOOLEAN:
      case Type.LONG:
      case Type.DOUBLE:
      case Type.FLOAT:
        return paramType;
      case Type.SHORT:
        return Type.INT_TYPE;
      default:
        return Type.getType(Object.class);
    }
  }
}
