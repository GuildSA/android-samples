package org.guildsa.dagger2demo;

import javax.inject.Inject;

public class HttpClient {

    @Inject
    public HttpClient() {
    }

    public String sendGetRequest(String url) {

        // Pretend that we used the parameter 'url' and did some awesome networking
        // here and then returned real data!

        return "{'name': 'Kevin', 'zipcode': '75025'}";
    }
}
