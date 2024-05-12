package cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class CookieUtils {

    public static Cookie getCookieByNameOrNull(HttpServletRequest request, String name) {
        if(request.getCookies().length == 0) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(e -> e.getName().equals(name))
                .findAny().orElse(null);
    }

    public static Cookie generateCallbackCookie(HttpServletRequest request) {
        String absoluteURL = String.format(
                "%s://%s:%d",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        return new Cookie(CookieNames.CALLBACK, absoluteURL);
    }
}
