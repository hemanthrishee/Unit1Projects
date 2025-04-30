package com.example.unit1projects.unit1.realtimeDatabaseFirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R

class DatabaseModifActivity : AppCompatActivity() {
    private lateinit var createEmployee: Button
    private lateinit var listEmployee: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_database_modif)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createEmployee = findViewById(R.id.createEmployee)
        listEmployee = findViewById(R.id.listEmployee)

        createEmployee.setOnClickListener {
            val intent = Intent(this, CreateEmployeeActivity::class.java)
            startActivity(intent)
        }

        listEmployee.setOnClickListener {
            val intent = Intent(this, EmployeeListActivity::class.java)
            startActivity(intent)
        }
    }
}