package com.example.taskcypress.feature_albums.di

import android.app.Application
import androidx.room.Room
import com.example.taskcypress.core.util.Constants.BASE_URL
import com.example.taskcypress.feature_albums.data.local.AlbumDao
import com.example.taskcypress.feature_albums.data.local.AlbumDatabase
import com.example.taskcypress.feature_albums.data.paging.AlbumPagingSource
import com.example.taskcypress.feature_albums.data.remote.BackendApi
import com.example.taskcypress.feature_albums.data.repository.AlbumRepositoryImpl
import com.example.taskcypress.feature_albums.domain.repository.AlbumRepository
import com.example.taskcypress.feature_albums.domain.use_cases.GetAlbums
import com.example.taskcypress.feature_albums.domain.use_cases.GetPhotosByAlbum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetAlbumUseCase(repository: AlbumRepository): GetAlbums {
        return GetAlbums(repository)
    }

    @Provides
    @Singleton
    fun provideGetPhotosByAlbumUseCase(repository: AlbumRepository): GetPhotosByAlbum {
        return GetPhotosByAlbum(repository)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(
        api: BackendApi,
        db: AlbumDatabase): AlbumRepository {
        return AlbumRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideAlbumDatabase(app: Application): AlbumDatabase =
        Room.databaseBuilder(app, AlbumDatabase::class.java, "albumDatabase").build()

    @Provides
    @Singleton
    fun provideAlbumDao(db: AlbumDatabase) = db.albumDao

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideBackendApi(retrofit: Retrofit): BackendApi =
        retrofit.create(BackendApi::class.java)
}