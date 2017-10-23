# SRegex
Simple way to use regex.

### Why use SRegex?
Have you ever tried to replace a substring using String#replaceAll(String, String) and want to replace the regex match to a modified version of the match?

With SRegex you can do so because you can actually get the results of the regex pattern and use it how every you want to use it.

Or if you just want to test a regex pattern. You can also use SRegex to test samples that you're gonna provide or the default sample that comes with SRegex.
When you test a regex pattern, it will print out the sample but the results are highlighted.

#### Sample:
```java
SRegex sRegex = new SRegex("1a 22b 3c 4d 5e");
System.out.println("SRegex#test(Pattern) :");
sRegex.test(Pattern.compile("\\d{1,}[A-Za-z]{1}"));
```

#### Output:
![Output](https://raw.githubusercontent.com/CyR1en/SRegex/master/src/test/resources/Output.png)
