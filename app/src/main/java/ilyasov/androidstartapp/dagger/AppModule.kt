package ilyasov.androidstartapp.dagger

import dagger.Module
import dagger.Provides
import ilyasov.androidstartapp.data.PersonalInfo
import ilyasov.androidstartapp.data.PersonalToken
import ilyasov.androidstartapp.data.Student

@Module
class AppModule {

    @Provides
    fun provideStudent(
        personalInfo: PersonalInfo,
        personalToken: PersonalToken
    ): Student {
        return Student(
            personalInfo = personalInfo,
            personalToken = personalToken
        )
    }

    @Provides
    fun providePersonalInfo():PersonalInfo {
        return PersonalInfo()
    }

    @Provides
    fun providePersonalToken():PersonalToken {
        return PersonalToken()
    }
}