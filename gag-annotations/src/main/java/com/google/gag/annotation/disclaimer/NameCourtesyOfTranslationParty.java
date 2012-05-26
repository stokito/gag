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

package com.google.gag.annotation.disclaimer;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the questionable name of the annotated class, method,
 * variable is due to having the original name run through Translation Party.
 * <p/>
 * Sometimes it's tough to name a class, method or variable. We've all been there.
 * If you've ever resorted to using Translation Party like we have, then you can use this annotation to give credit where it's due.
 * And if you've ever had the misfortune of botching up a name all on your own, then, sure, use this annotation anyway.
 * <pre>
 * &#064;NameCourtesyOfTranslationParty
 * void purizupurizuPleaseReferToTheFollowingElementsAggregateCan();
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NameCourtesyOfTranslationParty {
}
