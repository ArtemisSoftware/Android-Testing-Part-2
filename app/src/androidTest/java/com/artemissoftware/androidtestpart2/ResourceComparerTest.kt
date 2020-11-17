package com.artemissoftware.androidtestpart2

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before

class ResourceComparerTest{

    private lateinit var resourceComparer : ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }

    @After
    fun tearDown(){
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        val result =  resourceComparer.isEqual(context, R.string.app_name, "AndroidTestPart2")

        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        val result =  resourceComparer.isEqual(context, R.string.app_name, "AndroidTestPart3")

        assertThat(result).isFalse()
    }
}