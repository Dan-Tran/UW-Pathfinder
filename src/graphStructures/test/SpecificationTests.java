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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * SpecificationTests is a test suite used to encapsulate all
 * tests specific to the specification of this homework.
 *
 * For HW5, ScriptFileTests should be the only test class listed in
 * SpecificationTests. If you are tempted to add other classes, recall that
 * any tests you add to SpecificationTests must be valid tests for any other
 * student's implementation for this assignment, even though other students
 * will have designed a different public API.
 * 
 **/
@RunWith(Suite.class)
@SuiteClasses({ CheckAsserts.class, ScriptFileTests.class})
public final class SpecificationTests
{
    //this class is a placeholder for the suite, so it has no members.
}

