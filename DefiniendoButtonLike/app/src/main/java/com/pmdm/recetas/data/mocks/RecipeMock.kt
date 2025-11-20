package com.pmdm.recetas.data.mocks

import androidx.compose.ui.graphics.ImageBitmap

data class RecipeMock(
    val name: String,
    val description: String,
    val chef: String,
    val photo: ImageBitmap?,
    val likes: Int,
    val isLiked: Boolean
)