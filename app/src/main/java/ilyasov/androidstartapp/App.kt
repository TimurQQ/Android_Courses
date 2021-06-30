package ilyasov.androidstartapp

import android.app.Application
import ilyasov.androidstartapp.dagger.AppComponent
import ilyasov.androidstartapp.dagger.DaggerAppComponent

class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}