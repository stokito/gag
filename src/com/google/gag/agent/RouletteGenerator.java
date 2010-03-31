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

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.google.gag.annotation.enforceable.Roulette;
import com.google.gag.instrument.AnnotationStateError;
import com.google.gag.instrument.ClassGenerator;
import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.MethodInfo;

public class RouletteGenerator extends ClassGenerator {

  private static final Type ROULETTE_TYPE = Type.getType(Roulette.class);

  @Override
  protected boolean canInstrument(ClassInfo classInfo) {
    return classInfo.hasMethodAnnoAnywhere(ROULETTE_TYPE);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    MethodVisitor mv = writer().visitMethod(access, name, desc, sig, exceptions);
    mv.visitCode();

    MethodInfo method = classInfo().getMethod(name, desc);
    AnnoInfo anno = method.getAnnoFor(ROULETTE_TYPE);
    if (anno != null) {

      Double probability = (Double) anno.getValue("probability");
      if (probability < 0.0 || probability > 1.0) {
        throw new AnnotationStateError(
            "Probability (" + probability + ") needs to be between 0 and 1");
      }

      Type exception = (Type) anno.getValue("exception");

      // TODO: Figure out how to get the default value from the annotation itself.
      if (exception == null) {
        exception = Type.getType(RuntimeException.class);
      }

      String message = (String) anno.getValue("message");

      Label okay = new Label();

      mv.visitTypeInsn(NEW, "java/util/Random");
      mv.visitInsn(DUP);
      mv.visitMethodInsn(INVOKESPECIAL, "java/util/Random", "<init>", "()V");

      mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextDouble", "()D");
      mv.visitLdcInsn(probability);
      mv.visitInsn(DCMPG);
      mv.visitJumpInsn(IFGT, okay);

      mv.visitTypeInsn(NEW, exception.getInternalName());
      mv.visitInsn(DUP);
      mv.visitLdcInsn(message);
      mv.visitMethodInsn(INVOKESPECIAL, exception.getInternalName(), "<init>",
          "(Ljava/lang/String;)V");
      mv.visitInsn(ATHROW);

      mv.visitLabel(okay);

      setInstrumented(true);
    }

    mv.visitEnd();
    return mv;
  }
}
