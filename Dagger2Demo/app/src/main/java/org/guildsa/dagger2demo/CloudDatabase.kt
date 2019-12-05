package org.guildsa.dagger2demo

import javax.inject.Inject

class CloudDatabase @Inject constructor(private val mHttpClient: HttpClient?) {

    fun getUserData(userId: String?): String? { // Pretend that we had to do some setup here to talk to our cloud database host.

        return mHttpClient?.sendGetRequest("https://cloudserver.com/$userId")
    }
}