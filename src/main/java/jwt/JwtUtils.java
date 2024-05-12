package jwt;

import header.HeaderFields;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtUtils {

    public static String extractJwtFromHeaderOrEmpty(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HeaderFields.AUTHORIZATION))
                .map(e -> e.replace(HeaderFields.BASIC_PREFIX, ""))
                .orElse("");
    }

    public static String extractJwtFromCookieOrEmpty(Cookie authorizationCookie) {
        if (authorizationCookie != null) {
            String jwt = authorizationCookie.getValue().split("\\.")[1];
            return decodeJWTToString(jwt);
        }
        return "";
    }

    public static String decodeJWTToString(String str) {
        String payload = new String(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8)));
        return payload.replaceAll("[{}]", "");
    }

    public static Map<String, String> splitDecodedJWTIntoMap(String str) {
        return Arrays.stream(str.split(","))
                .map(e -> e.replace("\"", ""))
                .map(e -> e.split(":"))
                .collect(Collectors.toMap(e -> e[0].trim(), e -> e[1].trim()));
    }
}
