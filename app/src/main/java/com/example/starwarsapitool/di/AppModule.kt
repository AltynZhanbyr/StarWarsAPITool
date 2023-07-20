package com.example.starwarsapitool.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwarsapitool.data.local.MainDB
import com.example.starwarsapitool.data.remote.StarWarsApi
import com.example.starwarsapitool.data.repository.LocalStarWarsRepositoryImpl
import com.example.starwarsapitool.data.repository.RemoteStarWarsRepositoryImpl
import com.example.starwarsapitool.domain.repository.LocalStarWarsRepository
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.domain.use_case.AllLocalUseCases
import com.example.starwarsapitool.domain.use_case.DeleteLocalStarWarsCharacterUseCase
import com.example.starwarsapitool.domain.use_case.DeleteLocalStarWarsStarshipUseCase
import com.example.starwarsapitool.domain.use_case.GetLocalStarWarsAllCharactersUseCase
import com.example.starwarsapitool.domain.use_case.GetLocalStarWarsAllStarshipsUseCase
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsCharacterUseCase
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsStarshipUseCase
import com.example.starwarsapitool.domain.use_case.InsertLocalStarWarsCharacterUseCase
import com.example.starwarsapitool.domain.use_case.InsertLocalStarWarsStarshipUseCase
import com.example.starwarsapitool.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStarWarsApi():StarWarsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteStarWarsRepository(starWarsApi: StarWarsApi):RemoteStarWarsRepository{
        return RemoteStarWarsRepositoryImpl(starWarsApi)
    }

    @Provides
    @Singleton
    fun provideLocalStarWarsRepository(mainDB: MainDB): LocalStarWarsRepository {
        return LocalStarWarsRepositoryImpl(mainDB)
    }

    @Provides
    @Singleton
    fun providesGetRemoteStarWarsCharacterUseCase(repository: RemoteStarWarsRepository): GetRemoteStarWarsCharacterUseCase{
        return GetRemoteStarWarsCharacterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetRemoteStarWarsStarshipUseCase(repository: RemoteStarWarsRepository): GetRemoteStarWarsStarshipUseCase {
        return GetRemoteStarWarsStarshipUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAllLocalUseCases(repository: LocalStarWarsRepository):AllLocalUseCases{
        return AllLocalUseCases(
            deleteLocalStarWarsCharacterUseCase = DeleteLocalStarWarsCharacterUseCase(),
            deleteLocalStarWarsStarshipUseCase = DeleteLocalStarWarsStarshipUseCase(),
            insertLocalStarWarsCharacterUseCase = InsertLocalStarWarsCharacterUseCase(repository),
            insertLocalStarWarsStarshipUseCase = InsertLocalStarWarsStarshipUseCase(repository),
            getLocalStarWarsAllCharactersUseCase = GetLocalStarWarsAllCharactersUseCase(),
            getLocalStarWarsAllStarshipsUseCase = GetLocalStarWarsAllStarshipsUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideMainDB(application: Application): MainDB {
        return  Room.databaseBuilder(
            application,
            MainDB::class.java,
            Constants.dbName
        ).build()
    }
}