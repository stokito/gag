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

import com.google.gag.annotation.enforceable.ThisHadBetterBe;
import com.google.gag.annotation.enforceable.ThisHadBetterNotBe;
import com.google.gag.enumeration.Property;
import com.google.gag.instrument.AnnotationStateError;
import com.google.gag.instrument.ClassGenerator;
import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.LocalVarInfo;
import com.google.gag.instrument.info.MethodInfo;
import com.google.gag.instrument.util.StringBuilderVisitor;

public class ThisHadBetterGenerator extends ClassGenerator {

  private static final Type BETTER_BE_TYPE = Type.getType(ThisHadBetterBe.class);
  private static final Type BETTER_NOT_BE_TYPE = Type.getType(ThisHadBetterNotBe.class);

  private static final int TRUE = IFNE;
  private static final int FALSE = IFEQ;

  @Override
  protected boolean canInstrument(ClassInfo classInfo) {
    return classInfo.hasLocalVarAnnoAnywhere(BETTER_BE_TYPE)
        || classInfo.hasLocalVarAnnoAnywhere(BETTER_NOT_BE_TYPE);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    MethodVisitor mv = writer().visitMethod(access, name, desc, sig, exceptions);
    mv.visitCode();

    MethodInfo method = classInfo().getMethod(name, desc);
    for (LocalVarInfo param : method.getLocalVars()) {
      AnnoInfo anno = param.getAnnoFor(BETTER_BE_TYPE);
      if (anno != null) {
        visitThisHadBetter(mv, param, anno, true);
        setInstrumented(true);
      }
      anno = param.getAnnoFor(BETTER_NOT_BE_TYPE);
      if (anno != null) {
        visitThisHadBetter(mv, param, anno, false);
        setInstrumented(true);
      }
    }

    mv.visitEnd();
    return mv;
  }

  private static void visitThisHadBetter(MethodVisitor mv, LocalVarInfo param, AnnoInfo anno,
      boolean be) {

    Label okay = new Label();

    Property property = Property.valueOf((String) anno.getValue("value"));
    switch (property) {
    case NEGATIVE:
    case POSITIVE:
    case ZERO:
      visitComparable(mv, be, property, param, okay);
      break;
    case NULL:
      visitNullCheck(mv, be, param, okay);
      break;
    case THE_BLUE_PILL:
    case THE_RED_PILL:
      visitPill(mv, be, property, param, okay);
      break;
    case THE_STOLEN_DEATH_STAR_PLANS:
      visitDeathStarPlans(mv, be, param, okay);
      break;
    default:
      throw new AnnotationStateError("Unsupported Property: " + property);
    }

    visitException(mv, param, " is" + (be ? " not " : " ") + format(property));
    mv.visitLabel(okay);
  }

  private static void visitComparable(MethodVisitor mv, boolean be, Property property,
      LocalVarInfo param, Label okay) {

    Type type = param.getType();
    int index = param.getIndex();

    switch (type.getSort()) {
    case Type.INT:
    case Type.SHORT:
      mv.visitVarInsn(ILOAD, index);
      break;
    case Type.LONG:
      mv.visitVarInsn(LLOAD, index);
      mv.visitInsn(LCONST_0);
      mv.visitInsn(LCMP);
      break;
    case Type.DOUBLE:
      mv.visitVarInsn(DLOAD, index);
      mv.visitInsn(DCONST_0);
      mv.visitInsn(DCMPG);
      break;
    case Type.FLOAT:
      mv.visitVarInsn(FLOAD, index);
      mv.visitInsn(FCONST_0);
      mv.visitInsn(FCMPG);
      break;
    case Type.OBJECT:
      if (type.equals(Type.getType(Integer.class))) {
        mv.visitVarInsn(ALOAD, index);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getType(Integer.class).getInternalName(),
            "intValue", "()I");
      } else if (type.equals(Type.getType(Short.class))) {
        mv.visitVarInsn(ALOAD, index);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getType(Short.class).getInternalName(),
            "intValue", "()I");
      } else if (type.equals(Type.getType(Long.class))) {
        mv.visitVarInsn(ALOAD, index);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getType(Long.class).getInternalName(),
            "longValue", "()J");
        mv.visitInsn(LCONST_0);
        mv.visitInsn(LCMP);
      } else if (type.equals(Type.getType(Double.class))) {
        mv.visitVarInsn(ALOAD, index);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getType(Double.class).getInternalName(),
            "doubleValue", "()D");
        mv.visitInsn(DCONST_0);
        mv.visitInsn(DCMPG);
      } else if (type.equals(Type.getType(Float.class))) {
        mv.visitVarInsn(ALOAD, index);
        mv.visitMethodInsn(INVOKEVIRTUAL, Type.getType(Float.class).getInternalName(),
            "floatValue", "()F");
        mv.visitInsn(FCONST_0);
        mv.visitInsn(FCMPG);
      } else {
        throw new AnnotationStateError("Unsupported type: " + type);
      }
      break;
    default:
      throw new AnnotationStateError("Unsupported type: " + type);
    }

    switch (property) {
    case NEGATIVE:
      mv.visitJumpInsn(be ? IFLT : IFGE, okay);
      break;
    case POSITIVE:
      mv.visitJumpInsn(be ? IFGT : IFLE, okay);
      break;
    case ZERO:
      mv.visitJumpInsn(be ? IFEQ : IFNE, okay);
      break;
    }
  }

  private static void visitNullCheck(MethodVisitor mv, boolean be, LocalVarInfo param, Label okay) {
    switch (param.getType().getSort()) {
    case Type.ARRAY:
    case Type.OBJECT:
      mv.visitVarInsn(ALOAD, param.getIndex());
      break;
    default:
      throw new AnnotationStateError("Unsupported type: " + param.getType());
    }

    mv.visitJumpInsn(be ? IFNULL : IFNONNULL, okay);
  }

  /**
   * The given Property needs to be either {@link Property#THE_BLUE_PILL} or
   * {@link Property#THE_RED_PILL}.
   */
  private static void visitPill(MethodVisitor mv, boolean be, Property property,
      LocalVarInfo param, Label okay) {

    if (param.getType().getSort() != Type.OBJECT) {
      throw new AnnotationStateError("Unsupported type: " + param.getType());
    }

    // TODO: Also handle if parameter is null.

    Label notOkay = new Label();

    // See if the param type matches a Pill type
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitMethodInsn(INVOKEVIRTUAL, param.getType().getInternalName(), "getClass",
        "()Ljava/lang/Class;");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
    mv.visitLdcInsn("Pill|ThePill|.*[\\.$]Pill|.*[\\.$]ThePill");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "matches", "(Ljava/lang/String;)Z");

    if (be) {
      // If the param type does not match a Pill type, that's not okay, go to exception.
      mv.visitJumpInsn(FALSE, notOkay);
    } else {
      // If the param type does not match a Pill type, then that's okay, skip exception.
      mv.visitJumpInsn(FALSE, okay);
    }

    // At this point, the param type matches a Pill type.
    // So check if the param type is an enum type.
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitTypeInsn(INSTANCEOF, "java/lang/Enum");

    if (be) {
      // If the param type is not an enum, that's not okay, go to exception.
      mv.visitJumpInsn(FALSE, notOkay);
    } else {
      // If the param type is not an enum, that's okay, skip exception.
      mv.visitJumpInsn(FALSE, okay);
    }

    // Check that the Pill type has the property specified in the annotation.
    // First try to match on "BLUE" (or "RED").
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitTypeInsn(CHECKCAST, "java/lang/Enum");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Enum", "name", "()Ljava/lang/String;");
    mv.visitLdcInsn(property == Property.THE_BLUE_PILL ? "BLUE" : "RED");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");
    mv.visitJumpInsn(TRUE, be ? okay : notOkay);

    // Then try to see if the value ends with "BLUE_PILL" (or "RED_PILL").
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitTypeInsn(CHECKCAST, "java/lang/Enum");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Enum", "name", "()Ljava/lang/String;");
    mv.visitLdcInsn(property == Property.THE_BLUE_PILL ? "BLUE_PILL" : "RED_PILL");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z");
    mv.visitJumpInsn(be ? TRUE : FALSE, okay);

    mv.visitLabel(notOkay);
  }

  private static void visitDeathStarPlans(MethodVisitor mv, boolean be, LocalVarInfo param,
      Label okay) {

    if (param.getType().getSort() != Type.OBJECT) {
      throw new AnnotationStateError("Unsupported type: " + param.getType());
    }

    Label notOkay = new Label();

    // See if the param type matches a DeathStarPlans type
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitMethodInsn(INVOKEVIRTUAL, param.getType().getInternalName(), "getClass",
        "()Ljava/lang/Class;");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
    mv.visitLdcInsn(
        "DeathStarPlans|TheDeathStarPlans|.*[\\.$]DeathStarPlans|.*[\\.$]TheDeathStarPlans");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "matches", "(Ljava/lang/String;)Z");

    if (be) {
      // If the param type does not match a DeathStarPlans type, that's not okay, go to exception.
      mv.visitJumpInsn(FALSE, notOkay);
    } else {
      // If the param type does not match a DeathStarPlans type, then that's okay, skip exception.
      mv.visitJumpInsn(FALSE, okay);
    }

    // At this point, the param type matches a DeathStarPlans type.
    // So check if the param has an isStolen method.
    Label start = new Label();
    Label end = new Label();
    Label handler = new Label();
    mv.visitTryCatchBlock(start, end, handler, null);

    mv.visitLabel(start);
    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitMethodInsn(INVOKEVIRTUAL, param.getType().getInternalName(), "getClass",
        "()Ljava/lang/Class;");
    mv.visitLdcInsn("isStolen");
    mv.visitInsn(ACONST_NULL);
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod",
        "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");

    mv.visitVarInsn(ALOAD, param.getIndex());
    mv.visitInsn(ACONST_NULL);
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke",
        "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
    mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z");

    mv.visitJumpInsn(be ? TRUE : FALSE, okay);

    mv.visitLabel(end);
    mv.visitJumpInsn(GOTO, notOkay);
    mv.visitLabel(handler);
    mv.visitInsn(POP);

    mv.visitLabel(notOkay);
  }

  private static String format(Property property) {
    switch (property) {
    case THE_BLUE_PILL:
      return "the Blue Pill";
    case THE_RED_PILL:
      return "the Red Pill";
    case THE_STOLEN_DEATH_STAR_PLANS:
      return "the stolen Death Star plans";
    default:
      return property.toString().toLowerCase().replace('_', ' ');
    }
  }

  private static void visitException(MethodVisitor mv, LocalVarInfo param, String message) {
    mv.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
    mv.visitInsn(DUP);

    StringBuilderVisitor sb = new StringBuilderVisitor(mv);
    sb.visitAppend(param.getName() + " = ");
    sb.visitAppend(param.getType(), param.getIndex());
    sb.visitAppend(message);
    sb.visitToString();

    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>",
        "(Ljava/lang/String;)V");
    mv.visitInsn(ATHROW);
  }
}
