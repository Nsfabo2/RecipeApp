package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Create an application that makes use of the following API:
https://dojo-recipes.herokuapp.com/recipes/

Use the JSON data that comes back to populate a Recycler View with TextViews

Your app should also allow users to make POST Requests with the following fields:
Title
Author
Ingredients
Instructions
Once the user enters the data and clicks on the save button, the data should be added to the server

Once your application is complete, update the Recycler View to use Card Views to display the recipes
Each Card View should display the Title of the recipe and its Author

When users click on the Card View, they should be presented with the full Recipe (including Ingredients and Instructions)

This information should be displayed in a visually appealing manner (feel free to experiment with using a separate Activity, or showing the information in another way)
 */

//search ref: https://www.youtube.com/watch?v=_kRmxg0JXHQ

class MainActivity : AppCompatActivity() {

    var receipesList =  mutableListOf<DataItem>()
    lateinit var fab: Button
    lateinit var rvMain: RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    lateinit var Searchbtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.AddNewRecpierbtn)
        rvMain = findViewById(R.id.rvMain)
        rvMain.layoutManager = LinearLayoutManager(this@MainActivity)

        Searchbtn = findViewById(R.id.Searchbtn)

        Searchbtn.setOnClickListener {

            val searchtxt = findViewById<EditText>(R.id.SearchET)
            val txt = searchtxt.text.toString().trim()


        }


        if(receipesList.size == 0){
            CoroutineScope(Dispatchers.IO).launch {
                fetchData()
            }
        }
        fab.setOnClickListener {
            startActivity(Intent(this, RecpieActivity::class.java))
        }
    }

    fun updateData(newList: MutableList<DataItem>){
        val diffUtil = DiffUtilClass(receipesList, newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        receipesList = newList
        result.dispatchUpdatesTo(adapter)
    }



    fun fetchData(){
        try {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dojo-recipes.herokuapp.com/")
                .build().create(APIInterface::class.java)

            val retrofitData = retrofitBuilder.getData()
            retrofitData.enqueue(object : Callback<List<DataItem>?> {
                override fun onResponse(
                    call: Call<List<DataItem>?>,
                    response: Response<List<DataItem>?>
                ) {
                    for (d in response.body()!!)
                        receipesList.add(DataItem(d.author, d.ingredients, d.instructions, d.title))
                    adapter = RecyclerViewAdapter(receipesList)
                    rvMain.adapter = adapter

                }

                override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: $t", Toast.LENGTH_LONG).show()
                }
            })
        }catch (e: Exception){
            Toast.makeText(this, "Error: $e", Toast.LENGTH_LONG).show()
        }
    }


}//end class