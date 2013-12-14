# Google Annotations Gallery
The Google Annotations Gallery is an exciting new Java open source library that provides a rich set of annotations for developers to express themselves. Do you find the standard Java annotations dry and lackluster? Have you ever resorted to leaving messages to fellow developers with the `@Deprecated` annotation? Wouldn't you rather leave a `@LOL` or `@Facepalm` instead? If so, then this is the gallery for you.

Not only can you leave expressive remarks in your code, you can use these annotations to draw attention to your poetic endeavors. How many times have you written a palindromic or synecdochal line of code and wished you could annotate it for future readers to admire? Look no further than `@Palindrome` and `@Synecdoche`.

But wait, there's more. The Google Annotations Gallery comes complete with dynamic bytecode instrumentation. By using the gag-agent.jar Java agent, you can have your annotations behavior-enforced at runtime. For example, if you want to ensure that a method parameter is non-zero, try `@ThisHadBetterNotBe(Property.ZERO)`. Want to completely inhibit a method's implementation? Try `@Noop`.

If we've whet your appetite for truly expressive annotations, then read on and immerse yourself in the Google Annotations Gallery.

## Using

You can find the annotations and supporting enumerations in gag.jar. If you
want to enforce the enforceable annotations, you can find the dynamic bytecode
instrumentation Java agent in `gag-agent.jar`.

To use the annotations, simply annotate your code with them and include
`gag.jar` in your classpath.

To use the Java agent: use at least JDK 5; use the `-javaagent:gag-agent.jar`
option; and put the agent-dependent jars in your classpath, like:
```
java -javaagent:gag-agent.jar \
    -cp asm-3.1.jar:asm-commons-3.1.jar:google-collect-1.0.jar:gag.jar \
    MyAnnotatedClass
```
## About this fork
This project is fork of GAG. Currently it has identical code with original GAG, but it will growth with new features in next release.
You can read [my article about in russian](http://stokito.wordpress.com/2012/07/02/gag)

GAG is already loaded to Maven Central
```xml
<dependency>
    <groupId>com.google.gag</groupId>
    <artifactId>gag</artifactId>
    <version>1.0.1</version>
</dependency>

<dependency>
    <groupId>com.google.gag</groupId>
    <artifactId>gag-agent</artifactId>
    <version>1.0.1</version>
</dependency>
```
But it is not mavenized yet instead of this fork.
Now, GAG builded by Maven, thats a helps to resolve transitive dependencies.
This version also is uploaded to Maven Central, here is dependency:
```xml
<dependency>
    <groupId>com.github.stokito</groupId>
    <artifactId>gag-annotations</artifactId>
    <version>1.0.1</version>
</dependency>
```
Take a look on demo application https://github.com/stokito/gag-demo

Everybody are wellcome to fork me ;)
