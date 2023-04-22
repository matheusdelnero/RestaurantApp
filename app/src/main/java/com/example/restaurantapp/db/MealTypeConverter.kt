package com.example.restaurantapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute: Any?):String{
        if(attribute== null){
            return ""
        } else {
            return attribute.toString()
        }
    }

    @TypeConverter
    fun fromStringtoAny(attribute: String?): Any{
        if (attribute == null) {
            return ""
        } else {
            return attribute
        }
    }

}