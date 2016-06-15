package org.guildsa.dagger2demo;

import javax.inject.Inject;

public class UserPrefs {

    private String mZipCode = "75025";

    @Inject
    UserPrefs() {
    }

    public String getZipCode() {

        return mZipCode;
    }
}