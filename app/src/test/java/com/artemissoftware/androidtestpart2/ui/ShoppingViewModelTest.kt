package com.artemissoftware.androidtestpart2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.artemissoftware.androidtestpart2.MainCoroutineRule
import com.artemissoftware.androidtestpart2.getOrAwaitValueTest
import com.artemissoftware.androidtestpart2.repositories.FakeShoppingRepository
import com.artemissoftware.androidtestpart2.util.Status
import com.artemissoftware.androidtestpart2.util.constants.AppConstants
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class ShoppingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel


    @Before
    fun setup(){
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }


    @Test
    fun `insert shopping item with empty field, returns error`(){

        viewModel.insertShoppingItem("Mask", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert shopping item with too long name, returns error`(){

        val itemName = buildString {
            for(i in 1..AppConstants.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }


        viewModel.insertShoppingItem(itemName, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert shopping item with too long price, returns error`(){

        val itemPrice = buildString {
            for(i in 1..AppConstants.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }


        viewModel.insertShoppingItem("Mask", "5", itemPrice)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount, returns error`(){

        viewModel.insertShoppingItem("Mask", "99999999999999999999999999999999999", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, returns success`(){

        viewModel.insertShoppingItem("Mask", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}