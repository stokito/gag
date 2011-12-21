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

public class LocalVarInfo {
  private final String name;
  private final Type type;
  private final int index;
  private final Map<Type, AnnoInfo> annosByType = Maps.newLinkedHashMap();

  private LocalVarInfo(String name, String desc, int index) {
    this.name = name;
    this.type = Type.getType(desc);
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }

  public int getIndex() {
    return index;
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
    return name + "::" + type.getClassName();
  }

  public static class Maker {
    private final LocalVarInfo localVar;

    public Maker(String name, String desc, int index) {
      localVar = new LocalVarInfo(name, desc, index);
    }

    public void addAnno(AnnoInfo anno) {
      localVar.annosByType.put(anno.getType(), anno);
    }

    public LocalVarInfo getLocalVarInfo() {
      return localVar;
    }
  }
}
