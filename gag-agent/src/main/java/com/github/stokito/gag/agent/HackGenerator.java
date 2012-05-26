package com.github.stokito.gag.agent;

import com.github.stokito.gag.annotation.remark.Hack;
import com.github.stokito.gag.instrument.ClassGenerator;
import com.github.stokito.gag.instrument.info.AnnoInfo;
import com.github.stokito.gag.instrument.info.ClassInfo;
import com.github.stokito.gag.instrument.info.MethodInfo;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

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
