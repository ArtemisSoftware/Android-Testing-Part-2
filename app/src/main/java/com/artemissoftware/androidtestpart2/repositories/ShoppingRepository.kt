package com.artemissoftware.androidtestpart2.repositories

import androidx.lifecycle.LiveData
import com.artemissoftware.androidtestpart2.data.local.ShoppingItem
import com.artemissoftware.androidtestpart2.data.remote.responses.ImageResponse
import com.artemissoftware.androidtestpart2.util.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}