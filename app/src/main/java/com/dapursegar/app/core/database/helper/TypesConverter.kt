package com.dapursegar.app.core.database.helper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by Ramana on 05-Oct-19.
 */

class TypesConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Int> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {

        }.type

        return gson.fromJson<List<Int>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Int>): String {
        return gson.toJson(someObjects)
    }
}