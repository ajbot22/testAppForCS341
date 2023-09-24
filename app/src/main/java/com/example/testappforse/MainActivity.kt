package com.example.testappforse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testappforse.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  A test data sampler if we cannot get the url working for some reason
            In that case, skip the retrieveJson func and just use parse with this data

        val jsonData = """{
        "productID": "12345",
        "productName": "Example Product",
        "quantity": "10",
        "catID": "6789",
        "img": "example.jpg"
         }"""
         */

        val jsonStr = retrieveJsonFromUrl()
        val data = parseJsonToDataModel(jsonStr)
        //TODO: (ADD) Check a shared preferences for a list of added items and quantities
        //TODO: (ADD) Populate the screen with the list of items
        //TODO: (REMOVE) Either add a swipe to delete functionality or some kind of multi-select to delete
        //TODO: (REMOVE) Create a Recyler View for all the data objects gained from Add
        //TODO: (CHECKOUT) Connect to the database to checkout the items
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                //TODO: Rename and change the images for these buttons to be add,remove,checkout
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}

data class DataModel(
    val productID: String,
    val productName: String,
    val quantity: String,
    val catID: String,
    val img: String
)
fun retrieveJsonFromUrl(): String {
    val url = URL("https://bluejaypantry.etowndb.com/data_src/data.php?table=products")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.connect()

    return connection.inputStream.bufferedReader().use { it.readText() }
}

fun parseJsonToDataModel(jsonData: String): DataModel {
    val gson = Gson()
    return gson.fromJson(jsonData, DataModel::class.java)
}

