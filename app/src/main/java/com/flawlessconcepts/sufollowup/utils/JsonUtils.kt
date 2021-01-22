package com.flawlessconcepts.sufollowup.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.room.TypeConverter
import com.flawlessconcepts.sufollowup.R
import com.flawlessconcepts.sufollowup.database.FollowUpItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}


fun createLessonsFromJson(context: Context): MutableList<FollowUpItem> {
    val jsonFileString = getJsonDataFromAsset(context, "lessons.json")
    if (jsonFileString != null) {
        Log.i("data", jsonFileString)
    }

    val gson = Gson()
    val listQuestionsType = object : TypeToken<List<FollowUpItem>>() {}.type

    var lessons: List<FollowUpItem> = gson.fromJson(jsonFileString, listQuestionsType)
    return lessons.toMutableList()
}


class Converters {
    @TypeConverter
    fun listToJson(value: MutableList<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}


fun formatlessons(lessons: List<FollowUpItem>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.app_name))
        lessons.forEachIndexed() { index, lesson ->
            append("<br>")
            append("Lesson " + index.toString())
            append("\t${lesson.title}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}