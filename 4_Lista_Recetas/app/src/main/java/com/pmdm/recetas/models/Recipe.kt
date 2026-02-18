package com.pmdm.recetas.models

import androidx.compose.ui.graphics.ImageBitmap

data class Recipe(
    val id:Int,
    val name: String,
    val description: String,
    val chef: String,
    val photo: String?,
    val likes: Int,
    val isLiked: Boolean
)

