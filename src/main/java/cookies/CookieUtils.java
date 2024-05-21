package cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class CookieUtils {

    public static final String AUTHORIZATION_COOKIE = "authorization";

    /**
     * Filters all cookies of the current request for the given name
     */

    public static Cookie getCookieByNameOrNull(HttpServletRequest request, String name) {
        if(request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(e -> e.getName().equals(name))
                .findAny().orElse(null);
    }

    /**
     * Standardized format how to store key-value pairs in authorization cookie string
     */

    public static String authorizationCookieFormat(String value, String key) {
        return String.format("%s:%s", key, value);
    }
}
