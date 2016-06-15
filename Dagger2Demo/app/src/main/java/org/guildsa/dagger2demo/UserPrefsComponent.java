package org.guildsa.dagger2demo;

import javax.inject.Singleton;
import dagger.Component;

@Singleton @Component(modules = {UserPrefsModule.class})
public interface UserPrefsComponent {

    // We'll call this from the onCreate method of MainActivity to inject
    // UserPrefs into MainActivity.
    void inject(MainActivity activity);
}
