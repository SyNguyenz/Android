package com.example.inbox.ui.home

data class Email(
    val senderName: String,
    val sentTime: String,
    val content: String,
    val isStarred: Boolean
)
