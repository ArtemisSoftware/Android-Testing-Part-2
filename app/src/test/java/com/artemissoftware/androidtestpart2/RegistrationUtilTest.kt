package com.artemissoftware.androidtestpart2

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username return false`(){

        val result = RegistrationUtil.validateRegistrationInput("", "123", "123")

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){

        val result = RegistrationUtil.validateRegistrationInput("Baian", "123", "123")

        assertThat(result).isTrue()
    }


    @Test
    fun `username already exists returns false`(){

        val result = RegistrationUtil.validateRegistrationInput("Milo", "123", "123")

        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){

        val result = RegistrationUtil.validateRegistrationInput("Milo", "", "123")

        assertThat(result).isFalse()
    }


    @Test
    fun `password was repeated incorrectly returns false`(){

        val result = RegistrationUtil.validateRegistrationInput("Milo", "321", "123")

        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than 2 digits returns false`(){

        val result = RegistrationUtil.validateRegistrationInput("Milo", "qwert2", "qwert2")

        assertThat(result).isFalse()
    }
}