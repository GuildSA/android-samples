package org.guildsa.dagger2demo

import javax.inject.Inject

class HttpClient @Inject constructor() {

    fun sendGetRequest(url: String?): String? {

        // Pretend that we used the parameter 'url' and did some awesome networking
        // here and then returned real data!
        return "{'name': 'Kevin', 'zipcode': '75025'}"
    }
}