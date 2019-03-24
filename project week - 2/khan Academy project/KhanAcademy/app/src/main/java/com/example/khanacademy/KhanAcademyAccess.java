package com.example.khanacademy;



import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class KhanAcademyAccess {

    String id,passkey,apikey,apisec,resp;

    public KhanAcademyAccess(String id, String passkey,String apikey,String apisec) {
        this.id = id;
        this.passkey = passkey;
        this.apikey = apikey;
        this.apisec = apisec;

    }

    private static final String PROTECTED_RESOURCE_URL = "https://www.khanacademy.org/api/v1/user";

    public String call(String... args) throws Exception{
        final OAuth10aService service = new ServiceBuilder(this.apikey)
                .apiSecret(this.apisec)
                .build(KAApi.instance());

        // Obtain the Request Token
        final OAuth1RequestToken requestToken = service.getRequestToken();

        System.out.println(service.getAuthorizationUrl(requestToken));
        final String oauthVerifier ="";
        OAuthRequest requestt = new OAuthRequest(Verb.POST,"https://www.khanacademy.org/api/auth2/authorize");
        requestt.addBodyParameter("oauth_token",requestToken.getToken());
        requestt.addBodyParameter("identifier",this.id);
        requestt.addBodyParameter("password",this.passkey);
        final Response responsee = service.execute(requestt);
        this.resp = responsee.getBody();
        //System.out.println(responsee.getBody());

        // Trade the Request Token and Verfier for the Access Token
        final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);



        String url2 = "https://www.khanacademy.org/api/v1/user/students";

        ///*
        final OAuthRequest request2 = new OAuthRequest(Verb.GET,url2);
        service.signRequest(accessToken, request2);
        Response response2 = service.execute(request2);
        //System.out.println(response2.getBody());
        //*/
        return response2.getBody();
    }

    static class KAApi extends DefaultApi10a {

        public static KAApi instance() {
            return new KAApi();
        }

        @Override
        public String getAccessTokenEndpoint() {
            return "https://www.khanacademy.org/api/auth2/access_token";
        }

        @Override
        public String getAuthorizationUrl(OAuth1RequestToken token) {
            return " https://www.khanacademy.org/api/auth2/authorize?oauth_token=" + token.getToken();
        }

        @Override
        public String getAuthorizationBaseUrl() {
            return "";
        }

        @Override
        public String getRequestTokenEndpoint() {
            return "https://www.khanacademy.org/api/auth2/request_token";
        }
    }

}

