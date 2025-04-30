package com.example.unit1projects.unit1.realtimeDatabaseFirebase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.unit1projects.R
import com.google.firebase.database.DatabaseReference

class EmployeeListAdapter(
    private val employees: MutableList<Employee>,
    private var employeesReference: DatabaseReference
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeNameDisplay)
        val editEmployeeDetails: Button = itemView.findViewById(R.id.editEmployeeDetails)
        val deleteEmployee: Button = itemView.findViewById(R.id.deleteEmployee)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.employeeName.text = employee.name

        holder.editEmployeeDetails.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditEmployeeDetailsActivity::class.java)
            intent.putExtra("employeeId", employee.id)
            intent.putExtra("employeeName", employee.name)
            intent.putExtra("employeeEmail", employee.email)
            intent.putExtra("employeeDOB", employee.dob)
            intent.putExtra("employeePhone", employee.phone)
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteEmployee.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                val employeeId = employees[currentPosition].id
                employeesReference.child(employeeId).removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Employee deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Error deleting employee",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = employees.size
}