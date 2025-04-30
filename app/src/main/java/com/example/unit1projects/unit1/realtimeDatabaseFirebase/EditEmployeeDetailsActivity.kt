package com.example.unit1projects.unit1.realtimeDatabaseFirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditEmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var employeeNameEdit: EditText
    private lateinit var employeeEmailEdit: EditText
    private lateinit var employeePhoneEdit: EditText
    private lateinit var employeeDobEdit: EditText

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private lateinit var employeeId: String
    private lateinit var employeeName: String
    private lateinit var employeeEmail: String
    private lateinit var employeePhone: String
    private lateinit var employeeDob: String

    private lateinit var employeeReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_employee_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        employeeId = intent.getStringExtra("employeeId") ?: ""
        employeeName = intent.getStringExtra("employeeName") ?: ""
        employeeEmail = intent.getStringExtra("employeeEmail") ?: ""
        employeePhone = intent.getStringExtra("employeePhone") ?: ""
        employeeDob = intent.getStringExtra("employeeDOB") ?: ""

        employeeNameEdit = findViewById(R.id.employeeNameEdit)
        employeeNameEdit.setText(employeeName)

        employeeEmailEdit = findViewById(R.id.employeeEmailEdit)
        employeeEmailEdit.setText(employeeEmail)

        employeePhoneEdit = findViewById(R.id.employeePhoneEdit)
        employeePhoneEdit.setText(employeePhone)

        employeeDobEdit = findViewById(R.id.employeeDobEdit)
        employeeDobEdit.setText(employeeDob)

        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            employeeName = employeeNameEdit.text.toString()
            employeeEmail = employeeEmailEdit.text.toString()
            employeePhone = employeePhoneEdit.text.toString()
            employeeDob = employeeDobEdit.text.toString()

            val employee: Employee = Employee(employeeId, employeeName, employeeEmail, employeePhone, employeeDob)

            employeeReference = FirebaseDatabase.getInstance().reference.child("employees").child(employeeId)
            employeeReference.setValue(employee).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Employee Details Updated", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, EmployeeListActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error Updating Employee Details", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EmployeeListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}