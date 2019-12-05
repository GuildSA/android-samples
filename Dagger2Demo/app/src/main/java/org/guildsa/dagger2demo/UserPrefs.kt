package org.guildsa.dagger2demo

import javax.inject.Inject

class UserPrefs @Inject internal constructor() {

    private val mZipCode: String? = "75025"

    fun getZipCode(): String? {
        return mZipCode
    }
}