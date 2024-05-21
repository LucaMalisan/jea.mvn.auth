package bearer;

import request.HeaderFields;
import request.HttpRequestUtil;

import java.util.Map;

public class BearerRequestUtil {

    /**
     * This method takes the application credentials, creates a request and calls Auth0
     */

    public static String getAccessToken(String clientId, String clientSecret, String audience, String oauthUrl) {
        String authorization = String.join(":", clientId, clientSecret);
        String dataFormat = "%s=%s&";

        //it is ok that these values aren't defined in a constant, because this calls OAuth.
        //We have no influence on whether OAuth changes these properties anyway.

        String data = String.format(dataFormat, "grant_type", "client_credentials") +
                String.format(dataFormat, "redirect_uri", "urn:ietf:wg:oauth:2.0:oob") +
                String.format(dataFormat, "audience", audience);

        return HttpRequestUtil.createHttpRequestAndGetResponse(oauthUrl, "POST", data, Map.of(HeaderFields.AUTHORIZATION, authorization));
    }

}
