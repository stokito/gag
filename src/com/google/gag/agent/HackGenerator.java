package com.google.gag.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.google.gag.annotation.remark.Hack;
import com.google.gag.instrument.ClassGenerator;
import com.google.gag.instrument.info.AnnoInfo;
import com.google.gag.instrument.info.ClassInfo;
import com.google.gag.instrument.info.MethodInfo;

public class HackGenerator extends ClassGenerator {

  private static final Type HACK_TYPE = Type.getType(Hack.class);

  @Override
  protected boolean canInstrument(ClassInfo classInfo) {
    return classInfo.hasAnnoAnywhere(HACK_TYPE);
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String desc, String sig,
      String[] exceptions) {

    MethodVisitor mv = writer().visitMethod(access, name, desc, sig, exceptions);
    mv.visitCode();

    MethodInfo method = classInfo().getMethod(name, desc);
    AnnoInfo anno = method.getAnnoFor(HACK_TYPE);
    if (anno != null) {
      System.out.println("Here for method " + method);
    }

    mv.visitEnd();
    return mv;
  }
}
