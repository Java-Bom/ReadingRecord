package item6;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Splitter {
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final String NUMERIC_REGEX = "-?\\d+(\\.\\d+)?";

    private static final int CUSTOM_DELIMITER_INDEX = 1;
    private static final int REAL_FORMULA_INDEX = 2;

    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.*)₩n(.*)");

    private Splitter() {
    }

    public static List<String> split(final String formula) {
        Matcher customDelimiterMatcher = CUSTOM_DELIMITER_PATTERN.matcher(formula);

        if (customDelimiterMatcher.find()) {
            return split(customDelimiterMatcher);
        }

        return Arrays.asList(formula.split(DEFAULT_DELIMITER_REGEX));
    }

    private static List<String> split(Matcher customDelimiterMatcher) {
        String realFormula = customDelimiterMatcher.group(REAL_FORMULA_INDEX);
        String customDelimiter = customDelimiterMatcher.group(CUSTOM_DELIMITER_INDEX);

        validateDelimiter(customDelimiter);

        return Arrays.asList(realFormula.split(customDelimiter));
    }

    private static void validateDelimiter(final String customDelimiter) {
        if (customDelimiter.matches(NUMERIC_REGEX) || customDelimiter.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s 는(은) 올바르지 않은 커스텀구분자입니다.", customDelimiter));
        }
    }
}
