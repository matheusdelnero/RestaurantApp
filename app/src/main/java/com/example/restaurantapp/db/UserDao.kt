package com.example.restaurantapp.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantapp.model.Meal
import com.example.restaurantapp.model.User

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)


    @Query("SELECT COUNT(*) FROM user WHERE telefone = :telefone")
    suspend fun checkIfEmailExists(telefone: String): Int





}