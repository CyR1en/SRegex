package us.cyrien;

import java.util.regex.Pattern;

public class SRegexTest {

    public static void main(String[] args) {
        SRegex sRegex = new SRegex("1a 22b 3c 4d 5e");
        System.out.println("SRegex#test(Pattern) : ");
        sRegex.test(Pattern.compile("\\d{1,}[A-Za-z]{1}"));
    }
}
