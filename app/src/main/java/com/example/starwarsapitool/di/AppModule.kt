package com.example.starwarsapitool.di

import com.example.starwarsapitool.data.remote.StarWarsApi
import com.example.starwarsapitool.data.repository.RemoteStarWarsRepositoryImpl
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsCharacterUseCase
import com.example.starwarsapitool.domain.use_case.GetRemoteStarWarsStarshipUseCase
import com.example.starwarsapitool.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providesGetRemoteStarWarsCharacterUseCase(repository: RemoteStarWarsRepository): GetRemoteStarWarsCharacterUseCase{
        return GetRemoteStarWarsCharacterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetRemoteStarWarsStarshipUseCase(repository: RemoteStarWarsRepository): GetRemoteStarWarsStarshipUseCase {
        return GetRemoteStarWarsStarshipUseCase(repository)
    }
}