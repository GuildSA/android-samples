package org.guildsa.dagger2demo;

import javax.inject.Inject;

public class CloudDatabase {

    private HttpClient mHttpClient;

    @Inject
    public CloudDatabase(HttpClient httpClient) {
        mHttpClient = httpClient;
    }

    public String getUserData(String userId) {

        // Pretend that we had to do some setup here to talk to our cloud database host.

        return mHttpClient.sendGetRequest("https://cloudserver.com/" + userId);
    }
}