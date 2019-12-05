package org.guildsa.dagger2demo

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Use the @Module annotation to identify a class whose methods are responsible
// for providing dependencies.
@Module
class CloudDatabaseModule {

    // Each method that acts as a dependency provider must have the @Provides
    // annotation. The @Singleton annotation is optional. It indicates that
    // there will be only one instance of the object.
    @Provides
    @Singleton
    fun provideCloudDatabase(): CloudDatabase? {
        return CloudDatabase(HttpClient())
    }
}