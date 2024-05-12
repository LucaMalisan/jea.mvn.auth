package csrf;

import header.AuthorizationHeaderFields;
import jakarta.servlet.http.Cookie;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class CsrfUtils {

    public static String extractCsrfFromCookieOrEmpty(Cookie authorizationCookie) {
        if (authorizationCookie != null) {
            String[] chunks = authorizationCookie.getValue().split("\\+"); //split csrf from jwt
            String decodedCSRF = URLDecoder.decode(chunks[0], StandardCharsets.UTF_8);
            return decodedCSRF;
        }
        return "";
    }
}
