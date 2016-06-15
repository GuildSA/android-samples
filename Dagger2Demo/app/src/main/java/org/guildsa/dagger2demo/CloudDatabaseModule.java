package org.guildsa.dagger2demo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// Use the @Module annotation to identify a class whose methods are responsible
// for providing dependencies.
@Module
public class CloudDatabaseModule {

    // Each method that acts as a dependency provider must have the @Provides
    // annotation. The @Singleton annotation is optional. It indicates that
    // there will be only one instance of the object.

    @Provides @Singleton
    CloudDatabase provideCloudDatabase() {

        return new CloudDatabase(new HttpClient());
    }
}
