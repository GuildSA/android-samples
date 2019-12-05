package org.guildsa.dagger2demo

import dagger.Component
import javax.inject.Singleton

// The connection between the provider of dependencies, @Module, and the classes requesting them
// through @Inject is made using @Component, which is an interface:
@Singleton
@Component(modules = [CloudDatabaseModule::class])
interface CloudDatabaseComponent {

    // We'll call this from the onCreate method of MainActivity to inject
    // CloudDatabase into MainActivity.
    open fun inject(activity: MainActivity?)
}