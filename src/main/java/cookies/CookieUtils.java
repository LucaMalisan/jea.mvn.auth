package cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class CookieUtils {

    /**
     * Filters all cookies of the current request for the given name
     */

    public static Cookie getCookieByNameOrNull(HttpServletRequest request, String name) {
        if(request.getCookies().length == 0) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(e -> e.getName().equals(name))
                .findAny().orElse(null);
    }

    /**
     * Read current URL and create a standardized callback cookie to be sent to the login handler.
     * Through this cookie, it is determined where to redirect after a successful login
     */

    public static Cookie generateCallbackCookie(HttpServletRequest request) {
        String absoluteURL = String.format(
                "%s://%s:%d",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        return new Cookie(CookieNames.CALLBACK, absoluteURL);
    }

    /**
     * Standardized format how to store key-value pairs in authorization cookie string
     */

    public static String authorizationCookieFormat(String value, String key) {
        return String.format("%s:%s", key, value);
    }
}
