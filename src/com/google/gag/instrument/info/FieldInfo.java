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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class FieldInfo {
  private final int access;
  private final String name;
  private final Type type;
  private final String sig;

  @SuppressWarnings("unused")
  private final Object value;

  private final Map<Type, AnnoInfo> annosByType = Maps.newLinkedHashMap();

  private FieldInfo(int access, String name, String desc, String sig, Object value) {
    this.access = access;
    this.name = name;
    this.type = Type.getType(desc);
    this.sig = sig;
    this.value = value;
  }

  public int getAccess() {
    return access;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }

  public String getSig() {
    return sig;
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

  @Override
  public String toString() {
    return "Field " + name + "::" + type.getClassName();
  }

  public static class Maker {
    private final FieldInfo field;

    public Maker(int access, String name, String desc, String sig, Object value) {
      field = new FieldInfo(access, name, desc, sig, value);
    }

    public void addAnno(AnnoInfo anno) {
      field.annosByType.put(anno.getType(), anno);
    }

    public FieldInfo getFieldInfo() {
      return field;
    }
  }
}