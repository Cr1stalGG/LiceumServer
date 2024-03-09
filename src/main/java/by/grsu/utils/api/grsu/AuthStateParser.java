package by.grsu.utils.api.grsu;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class AuthStateParser {
    public static String getAuthState(String username, String password) throws AuthException, IOException {
            Connection.Response response = Jsoup.connect("https://edu.grsu.by/my/")
                    .method(Connection.Method.GET)
                    .execute();

            Document getAuthState = Jsoup.connect("https://edu.grsu.by/my/").cookies(response.cookies()).get();

            Element authState = getAuthState.select("input").last();

            assert authState != null;
            response = Jsoup.connect("https://idp.grsu.by/simplesaml/module.php/core/loginuserpass.php")
                    .data("username", username)
                    .data("password", password)
                    .data("AuthState", authState.attr("value"))
                    .cookies(response.cookies())
                    .method(Connection.Method.POST)
                    .execute();

            Document doc = Jsoup.connect("https://edu.grsu.by/my/").cookies(response.cookies()).get();

            if(doc.title().equals("POST data"))
                return authState.attr("value");
            else
                throw new AuthException();
    }
}
