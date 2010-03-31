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

package com.google.gag.instrument.info;

import java.util.List;
import java.util.Map;

import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ClassInfo {
  private final int version;
  private final int access;
  private final String name;
  private final String sig;
  private final String superName;
  private final List<String> interfaces;

  private final Map<Type, AnnoInfo> annosByType = Maps.newLinkedHashMap();
  private final List<FieldInfo> fields = Lists.newArrayList();
  private final Map<Method, MethodInfo> methods = Maps.newLinkedHashMap();

  private ClassInfo(int version, int access, String name, String sig, String superName,
      String[] interfaces) {

    this.version = version;
    this.access = access;
    this.name = name;
    this.sig = sig;
    this.superName = superName;
    this.interfaces = interfaces == null
        ? ImmutableList.<String>of()
        : ImmutableList.of(interfaces);
  }

  public int getVersion() {
    return version;
  }

  public int getAccess() {
    return access;
  }

  public String getName() {
    return name;
  }

  public String getSig() {
    return sig;
  }

  public String getSuperName() {
    return superName;
  }

  public List<String> getInterfaces() {
    return interfaces;
  }

  public List<AnnoInfo> getAnnos() {
    return ImmutableList.copyOf(annosByType.values());
  }

  /**
   * Returns <code>true</code> if an annotation of the specified type
   * is found anywhere in the class represented by this {@code ClassInfo}.
   */
  public boolean hasAnnoAnywhere(Type annoType) {
    if (getAnnoFor(annoType) != null) {
      return true;
    }
    for (FieldInfo field : fields) {
      if (field.hasAnno(annoType)) {
        return true;
      }
    }
    for (MethodInfo method : methods.values()) {
      if (method.hasAnno(annoType)) {
        return true;
      }
      for (LocalVarInfo localVar : method.getLocalVars()) {
        if (localVar.hasAnno(annoType)) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean hasMethodAnnoAnywhere(Type annoType) {
    for (MethodInfo method : methods.values()) {
      if (method.hasAnno(annoType)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasLocalVarAnnoAnywhere(Type annoType) {
    for (MethodInfo method : methods.values()) {
      for (LocalVarInfo localVar : method.getLocalVars()) {
        if (localVar.hasAnno(annoType)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if an annotation of the given type is found
   * at the class level in the class represented by this {@code ClassInfo}.
   */
  public boolean hasAnno(Type annoType) {
    return annosByType.containsKey(annoType);
  }

  public AnnoInfo getAnnoFor(Type annoType) {
    return annosByType.get(annoType);
  }

  public List<FieldInfo> getFields() {
    return ImmutableList.copyOf(fields);
  }

  public MethodInfo getMethod(String name, String desc) {
    return methods.get(new Method(name, desc));
  }

  public List<MethodInfo> getMethods() {
    return ImmutableList.copyOf(methods.values());
  }

  @Override
  public String toString() {
    return "Class %s";
  }

  public String toFormattedString() {
    List<String> lines = Lists.newArrayList();
    lines.add(this.toString());
    for (FieldInfo field : fields) {
      lines.add("  " + field);
      formatAnnos(lines, "    ", field.getAnnos());
    }
    for (MethodInfo method : methods.values()) {
      lines.add("  " + method);
      formatAnnos(lines, "    ", method.getAnnos());
      for (LocalVarInfo localVar : method.getLocalVars()) {
        lines.add("    Local Var " + localVar);
        formatAnnos(lines, "      ", localVar.getAnnos());
      }
    }
    return Joiner.on("\n").join(lines);
  }

  private static void formatAnnos(List<String> lines, String indent, Iterable<AnnoInfo> annos) {
    for (AnnoInfo anno : annos) {
      lines.add(indent + "Anno " + anno);
    }
  }

  public static class Maker {
    private final ClassInfo classInfo;

    public Maker(int version, int access, String name, String sig, String superName,
      String[] interfaces) {

      classInfo = new ClassInfo(version, access, name, sig, superName, interfaces);
    }

    public void addAnno(AnnoInfo anno) {
      classInfo.annosByType.put(anno.getType(), anno);
    }

    public void addField(FieldInfo field) {
      classInfo.fields.add(field);
    }

    public void addMethod(MethodInfo method) {
      classInfo.methods.put(new Method(method.getName(), method.getDesc()), method);
    }

    public ClassInfo getClassInfo() {
      return classInfo;
    }
  }
}
