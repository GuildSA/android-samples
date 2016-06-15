package org.guildsa.dagger2demo;

import javax.inject.Singleton;
import dagger.Component;

// The connection between the provider of dependencies, @Module, and the classes requesting them
// through @Inject is made using @Component, which is an interface:
@Singleton
@Component(modules = {CloudDatabaseModule.class})
public interface CloudDatabaseComponent {

    // We'll call this from the onCreate method of MainActivity to inject
    // CloudDatabase into MainActivity.
    void inject(MainActivity activity);
}
