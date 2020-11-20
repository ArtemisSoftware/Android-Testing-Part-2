package com.artemissoftware.androidtestpart2.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.androidtestpart2.R
import com.artemissoftware.androidtestpart2.data.local.ShoppingDao
import com.artemissoftware.androidtestpart2.util.constants.ApiConstants
import com.artemissoftware.androidtestpart2.data.local.ShoppingItemDatabase
import com.artemissoftware.androidtestpart2.data.remote.PixabayApi
import com.artemissoftware.androidtestpart2.repositories.DefaultShoppingRepository
import com.artemissoftware.androidtestpart2.repositories.ShoppingRepository
import com.artemissoftware.androidtestpart2.util.constants.DataBaseConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DataBaseConstants.DATABASE_NAME).build()




    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingItemDatabase) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .build()
            .create(PixabayApi::class.java)
    }


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(shoppingDao: ShoppingDao, pixabayApi: PixabayApi) = DefaultShoppingRepository(shoppingDao, pixabayApi) as ShoppingRepository


    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context) = Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

}