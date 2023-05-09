package com.example.restaurantapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val userid: Int?,
    val telefone: String?,
    val endereco: String?,
    val bairro: String?,
    val senha: String?,
    val confirmacaoSenha: String?

)
