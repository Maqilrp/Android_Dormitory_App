package com.festra.dormitory.domain.model

data class Users(
    val name: String,
    val email: String,
    val password: String,
    val photo: String,
    val nim: String,
    val telephone: String,
    val roomNumber: String
)
