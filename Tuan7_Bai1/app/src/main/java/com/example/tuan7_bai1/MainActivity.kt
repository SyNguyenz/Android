package com.example.tuan7_bai1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    private lateinit var etMSSV: EditText
    private lateinit var etHoTen: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedDate: TextView
    private lateinit var spinnerWard: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerProvince: Spinner
    private lateinit var checkBoxSports: CheckBox
    private lateinit var checkBoxCinema: CheckBox
    private lateinit var checkBoxMusic: CheckBox
    private lateinit var checkBoxTerms: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var btnShowCalendar: Button
    private var selectedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần
        etMSSV = findViewById(R.id.etMSSV)
        etHoTen = findViewById(R.id.etHoTen)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        calendarView = findViewById(R.id.calendarView)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        spinnerWard = findViewById(R.id.spinnerWard)
        spinnerDistrict = findViewById(R.id.spinnerDistrict)
        spinnerProvince = findViewById(R.id.spinnerProvince)
        checkBoxSports = findViewById(R.id.checkBoxSports)
        checkBoxCinema = findViewById(R.id.checkBoxCinema)
        checkBoxMusic = findViewById(R.id.checkBoxMusic)
        checkBoxTerms = findViewById(R.id.checkBoxTerms)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnShowCalendar = findViewById(R.id.btnShowCalendar)

        // Hiển thị hoặc ẩn CalendarView
        btnShowCalendar.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        // Xử lý chọn ngày từ CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvSelectedDate.text = "Ngày sinh đã chọn: $selectedDate"
        }

        // Xử lý nút Submit
        btnSubmit.setOnClickListener {
            if (validateInput()) {
                Toast.makeText(this, "Thông tin đã được gửi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(): Boolean {
        if (etMSSV.text.toString().isEmpty()) {
            etMSSV.error = "Vui lòng nhập MSSV"
            return false
        }
        if (etHoTen.text.toString().isEmpty()) {
            etHoTen.error = "Vui lòng nhập Họ tên"
            return false
        }
        if (radioGroupGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Vui lòng chọn Giới tính", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Vui lòng nhập Email"
            return false
        }
        if (etPhone.text.toString().isEmpty()) {
            etPhone.error = "Vui lòng nhập Số điện thoại"
            return false
        }
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Vui lòng chọn Ngày sinh", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!checkBoxTerms.isChecked) {
            Toast.makeText(this, "Vui lòng đồng ý với các điều khoản", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
