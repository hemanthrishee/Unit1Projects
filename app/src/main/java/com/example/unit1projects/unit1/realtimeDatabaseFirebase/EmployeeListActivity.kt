package com.example.unit1projects.unit1.realtimeDatabaseFirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unit1projects.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var employeeList: RecyclerView
    private val employees = mutableListOf<Employee>()
    private lateinit var employeesReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employee_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        employeesReference = FirebaseDatabase.getInstance().reference.child("employees")
        employeeList = findViewById(R.id.employeeList)
        employeeList.adapter = EmployeeListAdapter(employees, employeesReference)
        employeeList.layoutManager = LinearLayoutManager(this)

        employeesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                employees.clear()
                for (employeeSnapshot in dataSnapshot.children) {
                    val employee = employeeSnapshot.getValue(Employee::class.java)
                    if (employee != null) {
                        employees.add(employee)
                    }
                }
                employeeList.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EmployeeListActivity, "Failed to load employees", Toast.LENGTH_SHORT).show()
            }
        })
    }
}