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

public class AnnoInfo {
  private final Integer param;
  private final Type type;
  private final Map<String, Object> values = Maps.newHashMap();

  private AnnoInfo(Integer param, String desc) {
    this.param = param;
    this.type = Type.getType(desc);
  }

  public Integer getParam() {
    return param;
  }

  public Type getType() {
    return type;
  }

  public List<String> getValueNames() {
    return ImmutableList.copyOf(values.keySet());
  }

  /**
   * This is meant to be an immutable access, but it is not, since the value
   * object itself may not be immutable.
   */
  public Object getValue(String name) {
    return values.get(name);
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder("@" + type.getClassName());
    if (!values.isEmpty()) {
      b.append("(");
      for (Map.Entry<String, Object> e : values.entrySet()) {
        b.append(e.getKey() + "=" + e.getValue() + ", ");
      }
      b.delete(b.length() - 2, b.length());
      b.append(")");
    }
    return b.toString();
  }

  public static class Maker {
    private final AnnoInfo anno;

    public Maker(Integer param, String desc) {
      anno = new AnnoInfo(param, desc);
    }

    public Maker(String desc) {
      this(null, desc);
    }

    public void addValue(String name, Object value) {
      anno.values.put(name, value);
    }

    public AnnoInfo getAnnoInfo() {
      return anno;
    }
  }
}
