package com.example.soskarikcyandmorty.utils

import android.media.Image
import android.media.Rating
import android.net.Network
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {

    private inline fun <reified T> typeToken() = object : TypeToken<T>() {}.type
    private inline fun <reified T> fromJson(value: String?) =
        Gson().fromJson<T>(value, typeToken<T>())

    private inline fun <reified T> toJson(generic: T) = Gson().toJson(generic, typeToken<T>())

    @TypeConverter
    fun fromImage(value: String?) = fromJson<Image?>(value)

    @TypeConverter
    fun imageToJson(image: Image?) = toJson(image)

    @TypeConverter
    fun fromObject(value: String?) = fromJson<String>(value)

    @TypeConverter
    fun objcetToString(name : Any) = toJson(name)
}