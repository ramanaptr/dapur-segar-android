package com.dapursegar.app

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will looper on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private val categoryHomeDummy = arrayListOf<Any>()

    @Test
    fun test_instance() {
        val newJson = ""
        // Array convert response
        val response = Gson().fromJson(newJson, Array<Any>::class.java)
    }
}
