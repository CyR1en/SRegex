package us.cyrien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An easier way to handle, test, get results from a {@link String String} using Regex.
 *
 */
public class SRegex {

    private final String DEFAULT_SAMPLE =
            "Sample text for testing:\n" +
                    "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ\n" +
                    "0123456789 _+-.,!@#$%^&*();\\/|<>\"'\n" +
                    "12345 -98.7 3.141 .6180 9,000 +42\n" +
                    "555.123.4567\t+1-(800)-555-2468\n" +
                    "\n" +
                    "[ABC] [abc] [Abc] [AbC]" +
                    "\n\n" + "<ABC> <abc> <Abc> <AbC>" + "\n\n" +
                    "foo@demo.net\tbar.ba@test.co.uk\n";

    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_GREEN = "\033[42m";

    private Pattern regex;

    private String sample;
    private String result;
    private int numResults;
    private List<String> resultsList;

    /**
     * Constructor that initializes regex and automatically find matches
     * using {@link SRegex#find(Pattern) find(Pattern)}.
     *
     * @param sample sample to use for SRegex methods.
     * @param p Pattern to use as {@link #regex} regex.
     */
    public SRegex(String sample, Pattern p) {
        this.sample = sample == null ? DEFAULT_SAMPLE : sample ;
        result = this.sample;
        numResults = 0;
        resultsList = new ArrayList<>();
        regex = p;
        if(regex != null)
            find(p);
    }

    /**
     * Constructor that only initializes the sample.
     *
     * To initialize regex. Use {@link SRegex#init(Pattern) init(Pattern)},
     * {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)}.
     *
     * @param sample sample to use for SRegex methods.
     */
    public SRegex(String sample) {
        this(sample, null);
    }

    /**
     * Constructor that uses DEFAULT_SAMPLE as sample.
     *
     * To initialize regex. Use {@link SRegex#init(String) init(Pattern)},
     * {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)}.
     */
    public SRegex() {
        this(null, null);
    }

    /**
     * This is only used when {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)} have not been used.
     *
     * @param p Pattern to initialize private field regex instance with.
     * @return current instance of SRegex.
     */
    public SRegex init(Pattern p) {
        regex = p;
        return this;
    }

    /**
     * This is only used when {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)} have not been used.
     *
     * @param p Pattern as String to initialize private field regex instance with.
     * @return current instance of SRegex.
     */
    public SRegex init(String p) {
        init(Pattern.compile(p));
        return this;
    }

    /**
     * Find substrings using regex. This will put all matched substrings to private instance of
     * {@link java.util.List  resultList}.
     *
     * @param p Pattern to use for finding substrings. This will also initialize private field regex.
     * @return current instance of SRegex.
     */
    public SRegex find(Pattern p) {
        regex = p;
        resultsList = new ArrayList<>();
        String tempSample = sample;
        Matcher m = p.matcher(tempSample);
        this.regex = p;

        while (m.find()) {
            String result = m.group();
            resultsList.add(result);
            tempSample = tempSample.substring(tempSample.indexOf(result) + result.length());
            m = p.matcher(tempSample);
        }

        if (resultsList.size() < 1)
            System.out.println("No result found");
        else {
            numResults = resultsList.size();
        }
        return this;
    }

    /**
     * Test regex pattern, find substrings using that regex, change the color of the results, and print the sample.
     * This will put all matched substrings to private instance of {@link java.util.List resultList}.
     *
     * @param p Pattern to use for finding substrings. This will also initialize private field regex.
     */
    public void test(Pattern p) {
        String tempSample = sample;
        find(p);
        for (String string : resultsList) {
            tempSample = tempSample.replace(string, ANSI_GREEN + string + ANSI_RESET);
        }
        result = tempSample;
        System.out.println(this);
    }

    /**
     * Splits field result with a discriminant and use the array that
     * {@link String#split(String) String#split(String)}returns as resultList.
     *
     * @param p Pattern to use as discriminant.
     * @return current instance of SRegex
     */
    public SRegex split(Pattern p) {
        String[] args = result.split(p.pattern());
        resultsList = Arrays.asList(args);
        return this;
    }

    /**
     * Splits field result with a discriminant and use the array that
     * {@link String#split(String) String#split(String)}returns as resultList.
     *
     * @param p String to use as discriminant.
     * @return current instance of SRegex
     */
    public SRegex split(String p) {
        return split(Pattern.compile(p));
    }

    /**
     * Replace all substrings that matches the regex pattern.
     *
     * This can only be used if field regex have been initialized by
     * {@link SRegex#SRegex(String, Pattern) SRegex(String, Pattern)} constructor,
     * {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)}.
     *
     * @param replacement string to replace regex matches with.
     * @return current instance of SRegex.
     */
    public SRegex replace(String replacement) {
        result = result.replaceAll(regex.pattern(), replacement);
        return this;
    }

    /**
     * Replace all results that have been found by {@link SRegex#find(Pattern) find(Pattern)}
     * or {@link SRegex#test(Pattern) test(Pattern)} and replace resultList.
     *
     * This can only be used if field regex have been initialized by
     * {@link SRegex#SRegex(String, Pattern) SRegex(String, Pattern)} constructor,
     * {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)}.
     *
     * @param replacement string to replace regex matches with.
     * @return current instance of SRegex.
     */
    public SRegex replaceAllResults(String replacement) {
        List<String> replaced = new ArrayList<>();
        resultsList.forEach(s -> replaced.add(s.replaceAll(regex.pattern(), replacement)));
        this.resultsList = replaced;
        return this;
    }

    /**
     * Get resultList that have been set by {@link SRegex#find(Pattern) find(Pattern)},
     * {@link SRegex#test(Pattern) test(Pattern)}, {@link SRegex#split(Pattern) split(Pattern)},
     * or {@link SRegex#replaceAllResults(String) replaceAllResults(String)} as {@link List List}.
     *
     * @return resultList.
     */
    public List<String> getResultsList() {
        return resultsList;
    }

    /**
     * Get resultList that have been set by {@link SRegex#find(Pattern) find(Pattern)},
     * {@link SRegex#test(Pattern) test(Pattern)}, {@link SRegex#split(Pattern) split(Pattern)},
     * or {@link SRegex#replaceAllResults(String) replaceAllResults(String)} as {@link String String}.
     *
     * @return result.
     */
    public String getResult() {
        return result;
    }

    /**
     * Return regex Pattern that have been initialized by {@link SRegex#init(Pattern) init(Patter)},
     * {@link SRegex#find(Pattern) find(Pattern)}, or {@link SRegex#test(Pattern) test(Pattern)}.
     *
     * @return regex pattern.
     */
    public Pattern getRegex() {
        return regex;
    }

    /**
     * Get the default sample of SRegex.
     *
     * @return DEFAULT_SAMPLE.
     */
    public String getDefaultSample() {
        return DEFAULT_SAMPLE;
    }

    @Override
    public String toString() {
        String out = "Number of results: " + numResults + "\n\n" + "Results: " + getResultsList();
        out += "\n\n" + result;
        return out;
    }
}

