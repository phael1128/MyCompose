package com.example.component.data.modules

import com.example.component.data.service.PokemonService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

// 스터디용으로 만든 AppModules 에서 주입하는 거랑 겹쳐서 Qualifier로 사용하기
// (App 모듈꺼 사용해도 상관은 없는데 그냥 써보고 싶어서 사용)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class POKEMONGson

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class POKEMONGsonConverter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class POKEMONRetrofit

@InstallIn(SingletonComponent::class)
@Module
class PokemonModules {
    @Singleton
    @Provides
    @Named("POKRMON_BASE_URL")
    fun provideWebAPI(): String = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    @POKEMONGson
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Singleton
    @Provides
    @POKEMONGsonConverter
    fun provideConverterFactory(
        @POKEMONGson gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    @POKEMONRetrofit
    fun provideRetrofit(
        @Named("POKRMON_BASE_URL") apiUrl: String,
        @POKEMONGsonConverter converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGithubService(
        @POKEMONRetrofit retrofit: Retrofit
    ): PokemonService = retrofit.create(PokemonService::class.java)
}