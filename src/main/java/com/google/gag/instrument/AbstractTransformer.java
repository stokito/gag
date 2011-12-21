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

package com.google.gag.instrument;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.google.common.collect.Lists;
import com.google.gag.instrument.collector.ClassCollector;
import com.google.gag.instrument.info.ClassInfo;

public class AbstractTransformer implements ClassFileTransformer  {

  private final List<ClassGenerator> generators = Lists.newArrayList();

  protected void addGenerator(ClassGenerator generator) {
    generators.add(generator);
  }

  @Override
  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
      ProtectionDomain domain, byte[] bytes) throws IllegalClassFormatException {

    return transformBytes(bytes);
  }

  protected void transformFile(File classFile) throws IOException {
    byte[] bytes = AbstractTransformer.getBytes(classFile);
    bytes = transformBytes(bytes);
    AbstractTransformer.writeBytes(bytes, classFile);
  }

  /*
   * Visible for testing.
   */
  byte[] transformBytes(byte[] bytes) {
    ClassCollector visitor = new ClassCollector();
    visitVerbose(bytes, visitor);
    ClassInfo classInfo = visitor.getClassInfo();
    boolean instrumented = false;

    for (ClassGenerator gen : generators) {
      if (!gen.canInstrument(classInfo)) {
        continue;
      }
      ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
      gen.init(writer, classInfo);
      visitVerbose(bytes, gen);
      if (gen.hasInstrumented()) {
        bytes = writer.toByteArray();
        instrumented = true;
        System.out.println(gen.getClass().getSimpleName() + " instrumented "
            + classInfo.getName().replace('/', '.'));
      }
    }

    // If any generators instrumented the class bytes, return the
    // instrumented bytes. Otherwise, return null.
    return instrumented ? bytes : null;
  }

  private static void visitVerbose(byte[] bytes, ClassVisitor visitor) {
    try {
      new ClassReader(bytes).accept(visitor, ClassReader.SKIP_FRAMES);
    } catch (RuntimeException e) {
      e.printStackTrace();
      throw e;
    } catch (Error e) {
      e.printStackTrace();
      throw e;
    }
  }

  private static byte[] getBytes(File file) throws IOException {
    FileInputStream in = new FileInputStream(file);
    try {
      return toByteArray(in);
    } finally {
      in.close();
    }
  }

  private static void writeBytes(byte[] bytes, File file) throws IOException {
    FileOutputStream out = new FileOutputStream(file);
    try {
      out.write(bytes);
      out.flush();
    } finally {
      out.close();
    }
  }

  private static byte[] toByteArray(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] buf = new byte[1024];
    for(int numRead = in.read(buf); numRead > 0; numRead = in.read(buf)) {
      out.write(buf, 0, numRead);
    }
    return out.toByteArray();
  }
}
