package com.mtsteta.homework.model.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileInformation (
    @PrimaryKey(autoGenerate = true)
    val email: String,
    val name: String,
    val password: String,
    val phone: String,
    val preferences: List<String>,
    val imageId: Int
)