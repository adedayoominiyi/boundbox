BoundBox
========

BoundBox provides an easy way to test an object by accessing **all** its fields, constructors and methods, public or not. 
BoundBox breaks encapsulation.

For more information, have a look at [BoundBox's Wiki](https://github.com/stephanenicolas/boundbox/wiki).

[![Build Status](https://travis-ci.org/stephanenicolas/boundbox.png?branch=master)](https://travis-ci.org/stephanenicolas/boundbox)

Sample
------

Let's say we have a class A with private fields, constructors and methods like :

```java
public class A {
 private String foo;
 
 protected A(String foo) {
   this.foo = foo;
 }
 
 private void bar(String foo) {
  this.foo += foo; 
 }
 
 protected String getFoo() {
   return "The value of foo is " + foo;
 }
}
```

With BoundBox, you can write a test that accesses all fields and methods of `A`. 

Below, as soon as you write the statement : `@BoundBox(boundClass=A.class)`, the BoundBox annotation 
processor will generate the class `BoundBoxOfA` that you can use to access all inner fields, constructors and methods of `A`.


```java
public class ATest {
 @BoundBox( boundClass = A.class )
 private BoundBoxOfA boundBoxOfA;
 
 @Before
 public void setUp() {
   boundBoxOfA = new BoundBoxOfA( new A("bb") );
 }
 
 @Test
 public void testConstructor() {
   //GIVEN
   //WHEN
   //THEN
   assertEquals( "bb", boundBoxOfA.boundBox_getFoo());
 }
 
 @Test
 public void testBar() {
   //GIVEN
   //WHEN
   boundBoxOfA.bar("bbcc");
   
   //THEN
   assertEquals( "cc", boundBoxOfA.boundBox_getFoo();
 }
 
 @Test
 public void testGetFoo() {
   //GIVEN
   //WHEN
   //THEN
   assertEquals( "The value of foo is bb", boundBoxOfA.getFoo();
 }
}
```

Summary
-------

BoundBox API is quite simple. Indeed in has no API at all, just a set of conventions to access the inner structure of an `Object`.

BoundBox offers the following advantages over alternative technologies : 
* don't pollute your API under tests. Just code clean, don't change anything for testing even not a visibility modifier.
* objects under tests will be accessed using reflection, and this access will be checked at compile time (unlike using pure reflection or WhiteBox from PowerMock).
* all fields, constructors and methods, even those defined in super classes are accessible. For instance, it allows to access `foo.super.super.a`.

BoundBox has been designed for systems that are hard to tests. For instance, you will find a sample that shows how to test an Android activity pretty easily.

Quality of code 
---------------

BoundBox is heavily tested to ensure its quality. It uses both unit and integration tests and it is placed under continuous integration.
It also integrates checkstyle, findbugs, PMD to increase its robustness.

License
-------

 Copyright (C) 2013 Stéphane Nicolas
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	     http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
