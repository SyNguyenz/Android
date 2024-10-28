package com.example.inbox.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _emails = MutableLiveData<List<Email>>().apply {
        value = listOf(
            Email("Nguyen Van A", "10:00 AM", "Nội dung email 1", true),
            Email("Tran Thi B", "11:00 AM", "Nội dung email 2", false),
            Email("Nguyen Van C", "12:00 PM", "Nội dung email 3", true),
            Email("Nguyen Van A", "10:00 AM", "Nội dung email 1", true),
            Email("Tran Thi B", "11:00 AM", "Nội dung email 2", false),
            Email("Nguyen Van C", "12:00 PM", "Nội dung email 3", true),
            Email("Le Van D", "1:00 PM", "Nội dung email 4", false),  // Thêm email thứ 4
            Email("Pham Thi E", "2:00 PM", "Nội dung email 5", true)

        )
    }
    val emails: LiveData<List<Email>> = _emails
}