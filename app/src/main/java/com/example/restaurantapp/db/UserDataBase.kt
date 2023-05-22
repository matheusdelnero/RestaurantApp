package com.example.restaurantapp.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurantapp.model.User

//Banco de Dados para Salvar Dados de Login e Cadastro de Usuario
@Database(entities = [User::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        var INSTANCE: UserDataBase?= null

        @Synchronized
        fun getInstance(context: Context): UserDataBase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    UserDataBase::class.java,
                    "user.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as UserDataBase
        }

    }

}