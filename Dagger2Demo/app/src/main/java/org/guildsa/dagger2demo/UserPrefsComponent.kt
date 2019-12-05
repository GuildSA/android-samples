package org.guildsa.dagger2demo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserPrefsModule::class])
interface UserPrefsComponent {

    // We'll call this from the onCreate method of MainActivity to inject
    // UserPrefs into MainActivity.
    open fun inject(activity: MainActivity?)
}