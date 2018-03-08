# SRegex
An easier way to get sub-strings, result handling, and Regex testing.

### Why use SRegex?
With SRegex you easily get the results of the regex pattern and use it how ever you want to use it.

Or if you just want to test a Regex pattern. You can use SRegex to test samples that you're gonna provide or the default sample that comes with SRegex.

When you test a regex pattern, it will print out the sample and the results are highlighted.

#### Sample:
```java
SRegex sRegex = new SRegex("1a 22b 3c 4d 5e");
System.out.println("SRegex#test(Pattern) :");
sRegex.test(Pattern.compile("\\d{1,}[A-Za-z]{1}"));
```

#### Output:
![Output](https://raw.githubusercontent.com/CyR1en/SRegex/master/src/test/resources/Output.png)

#### How to use SRegex on your project?
Just add the jar file to your dependencies or clone the project and add it to your source.

#### Documentation
Javadocs can be located [here](https://github.cyr1en.com/docs/sregex/).

Current version: 0.0.2

Download: https://github.com/CyR1en/SRegex/releases/tag/0.0.2
