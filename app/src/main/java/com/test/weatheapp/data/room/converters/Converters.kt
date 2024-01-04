package com.test.weatheapp.data.room.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.weatheapp.data.retrofit.model.WeatherCityClass
import java.lang.reflect.Type

@ProvidedTypeConverter
class Converters {

        @TypeConverter
        fun fromTransactionList(transaction: List<WeatherCityClass?>?): String? {
            if (transaction == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<WeatherCityClass?>?>() {}.type
            return gson.toJson(transaction, type)
        }


        @TypeConverter
        fun toTransactionList(transactionString: String?): List<WeatherCityClass>? {
            if (transactionString == null) {
                return null
            }
            val gson = Gson()
            val type =
                object : TypeToken<List<WeatherCityClass?>?>() {}.type
            return gson.fromJson<List<WeatherCityClass>>(transactionString, type)
        }
}