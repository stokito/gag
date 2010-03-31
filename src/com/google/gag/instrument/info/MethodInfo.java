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

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MethodInfo {
  private final int access;
  private final String name;
  private final String desc;
  private final String sig;
  private final List<String> exceptions;
  private final Map<Type, AnnoInfo> annosByType = Maps.newLinkedHashMap();
  private final List<LocalVarInfo> localVars = Lists.newArrayList();

  private MethodInfo(int access, String name, String desc, String sig, String[] exceptions) {
    this.access = access;
    this.name = name;
    this.desc = desc;
    this.sig = sig;
    this.exceptions = exceptions == null
        ? ImmutableList.<String>of()
        : ImmutableList.of(exceptions);
  }

  public int getAccess() {
    return access;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public String getSig() {
    return sig;
  }

  public List<String> getExceptions() {
    return exceptions;
  }

  public boolean hasAnno(Type annoType) {
    return annosByType.containsKey(annoType);
  }

  public AnnoInfo getAnnoFor(Type annoType) {
    return annosByType.get(annoType);
  }

  public List<AnnoInfo> getAnnos() {
    return ImmutableList.copyOf(annosByType.values());
  }

  public List<LocalVarInfo> getLocalVars() {
    return ImmutableList.copyOf(localVars);
  }

  public boolean isStatic() {
    return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder("Method " + name + "(");
    if (!localVars.isEmpty()) {
      for (LocalVarInfo var : localVars) {
        b.append(var.getName() + " ");
      }
      b.delete(b.length() - 1, b.length());
    }
    b.append(")");
    return b.toString();
  }

  public static class Maker {
    private MethodInfo method;

    private List<LocalVarInfo.Maker> localVarMakers = Lists.newArrayList();

    public Maker(int access, String name, String desc, String sig, String[] exceptions) {
      method = new MethodInfo(access, name, desc, sig, exceptions);
    }

    public void addAnno(AnnoInfo anno) {
      method.annosByType.put(anno.getType(), anno);
    }

    public void addLocalVarMaker(LocalVarInfo.Maker localVarMaker) {
      localVarMakers.add(localVarMaker);
      method.localVars.add(localVarMaker.getLocalVarInfo());
    }

    public void addParamAnno(AnnoInfo anno) {
      localVarMakers.get(anno.getParam()).addAnno(anno);
    }

    public MethodInfo getMethodInfo() {
      return method;
    }
  }
}
