package com.example.androidtheory.model

data class Topic(val name: String, val image: String)

val topics = listOf(
    Topic(
        "android",
        "https://imagenes.20minutos.es/files/image_1920_1080/uploads/imagenes/2023/09/07/nuevo-logotipo-de-android.jpeg",
    ),
    Topic(
        "kotlin",
        "https://blog.sosafeapp.com/content/images/2020/04/blog---mi-primera-app-en-Kotlin.png",
    ),
)
