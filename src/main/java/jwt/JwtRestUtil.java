package jwt;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This is an alternative approach to pass the JWT token if cookies can't be used.
 * It is recommended to use the cookie strategy wherever possible.
 */

public class JwtRestUtil {

    public static final String JWT_PASSING_REST_URI = "pass-jwt";

    public static String generateAuthRestURI(String targetUrl) {
        return targetUrl + "/rest/" + JWT_PASSING_REST_URI;
    }

    public static String generateClientId(HttpServletRequest request) {
        return request.getHeader("User-Agent") +
                request.getHeader("Accept-Language") +
                request.getHeader("X-Forwarded-For");
    }
}
