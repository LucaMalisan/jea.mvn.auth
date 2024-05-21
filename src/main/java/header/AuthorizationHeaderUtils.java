package header;

import jakarta.servlet.http.HttpServletRequest;
import request.HeaderFields;

import java.net.URI;

public class AuthorizationHeaderUtils {

    public static final String CALLBACK = "callback";
    public static final String SET_COOKIE = "Set-Cookie";

    private static String generateCallbackParameter(HttpServletRequest request) {
        String absoluteURL = String.format(
                "%s://%s:%d%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                request.getRequestURI());
        return String.format("?%s=%s", AuthorizationHeaderUtils.CALLBACK, absoluteURL);
    }

    public static URI generateLoginRedirectURL(HttpServletRequest request) {
        return URI.create("http://192.168.56.1:8084/login" + generateCallbackParameter(request));
    }
}
