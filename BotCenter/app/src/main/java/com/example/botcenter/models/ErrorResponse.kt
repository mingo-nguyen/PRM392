package com.example.botcenter.models

data class ErrorResponse(
    val type: String,
    val title: String,
    val status: Int,
    val detail: String,
    val path: String,
    val message: String
)
