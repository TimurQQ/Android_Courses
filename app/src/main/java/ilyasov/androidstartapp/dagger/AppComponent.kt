package ilyasov.androidstartapp.dagger

import dagger.Component
import ilyasov.androidstartapp.MainActivity

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}