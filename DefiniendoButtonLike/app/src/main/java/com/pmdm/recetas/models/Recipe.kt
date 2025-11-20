package com.pmdm.recetas.models

import androidx.compose.ui.graphics.ImageBitmap

data class Recipe(
    val name: String,
    val description: String,
    val chef: String,
    val photo: ImageBitmap?,
    val likes: Int,
    val isLiked: Boolean

)