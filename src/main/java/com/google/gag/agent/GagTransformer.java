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

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;

import com.google.gag.instrument.AbstractTransformer;

public class GagTransformer extends AbstractTransformer {

  private static enum GenKey {
    NOOP, ROULETTE, ANSWER_TO_LIFE, THIS_HAD_BETTER, HACK
  }

  private void addGeneratorForKey(String key) {
    addGeneratorForKey(GenKey.valueOf(key.toUpperCase()));
  }

  private void addAllGenerators() {
    for (GenKey key : GenKey.values()) {
      addGeneratorForKey(key);
    }
  }

  private void addGeneratorForKey(GenKey key) {
    switch (key) {
      case NOOP:
        addGenerator(new NoopGenerator());
        break;
      case ROULETTE:
        addGenerator(new RouletteGenerator());
        break;
      case ANSWER_TO_LIFE:
        addGenerator(new AnswerToLifeGenerator());
        break;
      case THIS_HAD_BETTER:
        addGenerator(new ThisHadBetterGenerator());
        break;
      case HACK:
        addGenerator(new HackGenerator());
        break;
    }
  }

  /**
   * An instrumentation agent needs to have a premain method.
   */
  public static void premain(String args, Instrumentation inst) {
    GagTransformer transformer = new GagTransformer();
    if (args == null || args.length() == 0) {
      transformer.addAllGenerators();
    } else {
      for (String key : args.split(",")) {
        transformer.addGeneratorForKey(key);
      }
    }
    inst.addTransformer(transformer);
  }

  public static void main(String[] args) throws IOException {
    File classFile = new File(args[0]);
    GagTransformer transformer = new GagTransformer();
    if (args.length < 2) {
      transformer.addAllGenerators();
    } else {
      for (int i = 1; i < args.length; i++) {
        transformer.addGeneratorForKey(args[i]);
      }
    }
    transformer.transformFile(classFile);
  }
}