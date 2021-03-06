/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8016013
 * @summary Compiler incorrectly treats annotated and unannotated type variable bounds as different types
 * @compile -doe ErasureTest.java
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
@interface Advanced {}

class U {}
interface I {}

class ErasureTest {
     <T extends U & @Advanced I> void TestMethod(T arg1) { }
    public static void main(String argv[]) {
        ErasureTest t1 = new ErasureTest(){
            public <T extends @Advanced U & I> void TestMethod(T arg1) { }
        };

        ErasureTest t2 = new ErasureTest(){
            public <T extends U & @Advanced I> void TestMethod(T arg1) { }
        };
    }
}
