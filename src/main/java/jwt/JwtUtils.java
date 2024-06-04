package jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import json.JsonUtil;
import request.HeaderFields;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtUtils {

    /**
     * Checks if authorization header is set and parses its value
     */

    public static String extractJwtFromHeaderOrEmpty(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HeaderFields.AUTHORIZATION))
                .map(e -> e.replace(HeaderFields.BASIC_PREFIX, ""))
                .orElse("");
    }

    /**
     * This method splits the (standardized) authorization cookie string in order to get the jwt token
     */

    public static String extractJwtFromCookieOrEmpty(Cookie authorizationCookie) {
        if (authorizationCookie != null) {
            String jwt = authorizationCookie.getValue().split("\\.")[1];
            return decodeJWTToString(jwt);
        }
        return "";
    }

    /**
     * Decode the jwt token into a plain text string
     */

    public static String decodeJWTToString(String str) {
        String payload = new String(Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8)));
        return payload.replaceAll("[{}]", "");
    }

    /**
     * Decode the jwt token into a map containing the split key-value pairs of the jwt token in plain text
     */

    public static Map<String, String> splitDecodedJWTIntoMap(String str) {
        return JsonUtil.jsonStringToMap(str);
    }
}
