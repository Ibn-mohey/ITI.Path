import java.util.function.BiPredicate;

public class StringUtils {

    public static String betterString(String s1, String s2, BiPredicate<String, String> Bi) {
        if (Bi.test(s1, s2)) {
            return s1;
        }
            return s2;
    }
}
