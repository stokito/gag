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

import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.google.common.collect.ImmutableList;
import com.google.gag.annotation.enforceable.AnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything;
import com.google.gag.instrument.AnnotationStateError;
import com.google.gag.instrument.ClassGenerator;
import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.LocalVarInfo;
import com.google.gag.instrument.info.MethodInfo;

public class AnswerToLifeGenerator extends ClassGenerator {

  private static final Type ANSWER_TYPE =
      Type.getType(AnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything.class);

  private static final int FORTY_TWO = 42;

  @Override
  protected boolean canInstrument(ClassInfo classInfo) {
    return classInfo.hasLocalVarAnnoAnywhere(ANSWER_TYPE);
  }

  /**
   * Supported types that have a valueOf(String) method.
   */
  private final List<Type> supportedValueOfTypes = ImmutableList.of(
      Type.getType(Integer.class),
      Type.getType(Long.class),
      Type.getType(Double.class),
      Type.getType(Float.class),
      Type.getType(Byte.class),
      Type.getType(Short.class));

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    MethodVisitor mv = writer().visitMethod(access, name, desc, sig, exceptions);
    mv.visitCode();

    MethodInfo method = classInfo().getMethod(name, desc);
    for (LocalVarInfo param : method.getLocalVars()) {
      AnnoInfo anno = param.getAnnoFor(ANSWER_TYPE);
      if (anno == null) {
        continue;
      }
      
      Type paramType = param.getType();
      switch (paramType.getSort()) {
        case Type.INT:
        case Type.BYTE:
        case Type.CHAR:
        case Type.SHORT:
          mv.visitLdcInsn(FORTY_TWO);
          mv.visitVarInsn(ISTORE, param.getIndex());
          break;
        case Type.LONG:
          mv.visitLdcInsn((long) FORTY_TWO);
          mv.visitVarInsn(LSTORE, param.getIndex());
          break;
        case Type.DOUBLE:
          mv.visitLdcInsn((double) FORTY_TWO);
          mv.visitVarInsn(DSTORE, param.getIndex());
          break;
        case Type.FLOAT:
          mv.visitLdcInsn((float) FORTY_TWO);
          mv.visitVarInsn(FSTORE, param.getIndex());
          break;
        case Type.OBJECT:
          visitObject(mv, param);
          break;
        default:
          throwUnsupportedException(param);
      }

      setInstrumented(true);
    }

    mv.visitEnd();
    return mv;
  }

  /**
   * TODO: Support BigDecimal and BigInteger.
   */
  private void visitObject(MethodVisitor mv, LocalVarInfo param) {
    Type paramType = param.getType();
    if (Type.getType(Character.class).equals(paramType)) {
      mv.visitLdcInsn((char) FORTY_TWO);
      mv.visitMethodInsn(INVOKESTATIC, paramType.getInternalName(), "valueOf",
      "(C)L" + paramType.getInternalName() + ";");
      mv.visitVarInsn(ASTORE, param.getIndex());
    } else if (supportedValueOfTypes.contains(paramType)) {
      mv.visitLdcInsn(String.valueOf(FORTY_TWO));
      mv.visitMethodInsn(INVOKESTATIC, paramType.getInternalName(), "valueOf",
      "(Ljava/lang/String;)L" + paramType.getInternalName() + ";");
      mv.visitVarInsn(ASTORE, param.getIndex());
    } else {
      throwUnsupportedException(param);
    }
  }

  private void throwUnsupportedException(LocalVarInfo param) {
    throw new AnnotationStateError("Unsupported parameter type (" + param.getType() + ") for "
        + AnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything.class);

  }
}
