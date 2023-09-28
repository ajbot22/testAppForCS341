package com.example.testappforse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
public class ArrayAdapter: AppCompatActivity() {

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Get a reference to the Spinner
            val spinner = findViewById<Spinner>(R.id.spinner)

                    // Create an ArrayAdapter using the string array and a default spinner layout
                    val adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.spinner_items,  // Replace with your data source (e.g., an array resource)
                    android.R.layout.simple_spinner_item
            )

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Set the adapter for the Spinner
            spinner.adapter = adapter

            // Optionally, set an item selection listener to handle Spinner item selections
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    // Handle the selected item here
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected here
                }
            }
        }
    }


