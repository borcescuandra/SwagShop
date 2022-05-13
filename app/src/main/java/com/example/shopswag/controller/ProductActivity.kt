package com.example.shopswag.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopswag.EXTRA_CATEGORY
import com.example.shopswag.EXTRA_PRODUCT
import com.example.shopswag.R
import com.example.shopswag.adapter.ProductAdapter
import com.example.shopswag.service.DataService
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val categoryType = intent.getStringExtra(EXTRA_CATEGORY)
        adapter =
            ProductAdapter(this, categoryType?.let { DataService.getProducts(it) }) { product ->
                val intent = Intent(this, ProductInfoActivity::class.java)
                intent.putExtra(EXTRA_PRODUCT, product)
                startActivity(intent)
            }
        productRV.adapter = adapter

        var spanCount = 2
        if (resources.configuration.screenWidthDp > 650)
            spanCount = 3

        val layoutManager = GridLayoutManager(this, spanCount)
        productRV.layoutManager = layoutManager

    }
}