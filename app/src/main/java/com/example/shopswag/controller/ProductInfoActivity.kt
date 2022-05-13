package com.example.shopswag.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopswag.EXTRA_PRODUCT
import com.example.shopswag.R
import com.example.shopswag.model.Product
import kotlinx.android.synthetic.main.activity_product_info.*
import kotlinx.android.synthetic.main.product_list_item.*
import kotlinx.android.synthetic.main.product_list_item.productImage
import kotlinx.android.synthetic.main.product_list_item.productName
import kotlinx.android.synthetic.main.product_list_item.productPrice

class ProductInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)

        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        val resId = this.resources.getIdentifier(product!!.image, "drawable", this.packageName)

        productImage.setImageResource(resId)
        productName.text = product!!.name
        productPrice.text = product!!.price
        productDesc.text = product!!.desc
    }
}