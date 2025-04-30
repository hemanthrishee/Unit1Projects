package com.example.unit1projects.unit1.realtimeDatabaseFirebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.google.firebase.database.FirebaseDatabase

class CreateEmployeeActivity : AppCompatActivity() {
    private lateinit var employeeName: EditText
    private lateinit var employeeEmail: EditText
    private lateinit var employeePhone: EditText
    private lateinit var employeeDOB: EditText

    private lateinit var createEmployeeButton: Button

    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        employeeName = findViewById(R.id.employeeName)
        employeeEmail = findViewById(R.id.employeeEmail)
        employeePhone = findViewById(R.id.employeePhone)
        employeeDOB = findViewById(R.id.employeeDOB)

        createEmployeeButton = findViewById(R.id.createEmployeeButton)

        firebaseDatabase = FirebaseDatabase.getInstance()
        val mainRef = firebaseDatabase.reference

        createEmployeeButton.setOnClickListener {
            val name = employeeName.text.toString()
            val email = employeeEmail.text.toString()
            val phone = employeePhone.text.toString()
            val dob = employeeDOB.text.toString()

            val employeeRef = mainRef.child("employees").push()
            val employeeId = employeeRef.key!!
            val employee = Employee(employeeId, name, email, phone, dob)
            employeeRef.setValue(employee).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Employee created successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to create employee", Toast.LENGTH_SHORT).show()
                }
            }

            finish()
        }
    }
}