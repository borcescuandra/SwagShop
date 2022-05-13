package com.example.shopswag.controller


import CategoryAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopswag.EXTRA_CATEGORY
import com.example.shopswag.R
import com.example.shopswag.*
import com.example.shopswag.service.DataService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CategoryAdapter(this, DataService.categories) { category ->
            val productIntent = Intent(this, ProductActivity::class.java)
            productIntent.putExtra(EXTRA_CATEGORY,category.title)
            startActivity(productIntent)
        }
        categoryRV.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        categoryRV.layoutManager = layoutManager
        categoryRV.setHasFixedSize(true)
    }
}