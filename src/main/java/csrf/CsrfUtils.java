package csrf;

import jakarta.servlet.http.Cookie;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class CsrfUtils {

    /**
     * CSRF-Tokens are only sent through Cookies because they are passed from the login handler back to the client.
     * Afterwards they'll be submitted through forms.
     * This method splits the (standardized) authorization cookie string in order to get the csrf token
     */

    public static String extractCsrfFromCookieOrEmpty(Cookie authorizationCookie) {
        if (authorizationCookie != null) {
            String[] chunks = authorizationCookie.getValue().split("\\+"); //split csrf from jwt
            String decodedCSRF = URLDecoder.decode(chunks[0], StandardCharsets.UTF_8);
            return decodedCSRF;
        }
        return "";
    }
}
