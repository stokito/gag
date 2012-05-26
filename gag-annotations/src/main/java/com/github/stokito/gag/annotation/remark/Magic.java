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

package com.github.stokito.gag.annotation.remark;

import com.github.stokito.gag.enumeration.MagicType;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Stumble across code that somehow works beyond all reason?
 * Life's short. Mark it with {@code @Magic} and move on:
 * <pre>
 * &#064;Magic
 * public static int negate(int n) {
 *     return new Byte((byte) 0xFF).hashCode()
 *     / (int) (short) '\uFFFF' * ~0
 *     * Character.digit ('0', 0) * n
 *     * (Integer.MAX_VALUE * 2 + 1)
 *     / (Byte.MIN_VALUE >> 7) * (~1 | 1);
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Magic {
    MagicType type() default MagicType.BLACK;
}
