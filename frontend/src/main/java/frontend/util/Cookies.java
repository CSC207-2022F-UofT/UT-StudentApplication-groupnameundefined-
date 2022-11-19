package frontend.util;

import org.springframework.http.ResponseCookie;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class Cookies {
    private MultiValueMap<String, String> cookies;

    Cookies() {
        this.cookies = new LinkedMultiValueMap<String, String>();
    }

    public MultiValueMap<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(MultiValueMap<String, ResponseCookie> cookies) {
        for (String key : cookies.keySet()) {
            this.cookies.set(key, cookies.get(key).get(0).getValue());
        }
    }

    public void setCookies(String key, String value) {
        this.cookies.set(key, value);
    }
}
