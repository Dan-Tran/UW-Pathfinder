/**
 * Copyright 2017 Dan Tran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package graphStructures.test;

import org.junit.Test;

/**
 * Checks that Java Asserts are enabled.
 */
public class CheckAsserts {

  @Test
  public void testAssertEnabled() {
    checkAssertsEnabled();
  }

  /**
   * Checks that Java Asserts are enabled. If they are not, an error message is printed,
   * and the system exits.
   */
  public static void checkAssertsEnabled() {
    try {
      assert false;

      // assertions are not enabled
      System.err.println("Java Asserts are not currently enabled. Follow homework writeup instructions to enable asserts on all JUnit Test files.");
      System.exit(1);

    } catch (AssertionError e) {
      // do nothing
      // assertions are enabled
    }
  }
}
