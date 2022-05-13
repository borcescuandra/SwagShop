package com.example.shopswag.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopswag.model.Product
import com.example.shopswag.R
import kotlinx.android.synthetic.main.product_list_item.view.*



 class ProductAdapter(private val context: Context,
                      private val products: List<Product>?,
                      private val itemClick: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.product_list_item, parent, false)
        return VH(view, itemClick)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.bindProduct(context, products!![position])
    }

    override fun getItemCount(): Int {
        return products!!.count()
    }

    class VH(itemView: View, private val itemClick: (Product) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val productImage = itemView?.productImage
        private val productName = itemView?.productName
        private val productPrice = itemView?.productPrice

        fun bindProduct(context: Context, product: Product) {
            val resId = context.resources.getIdentifier(product.image, "drawable", context.packageName)
            productImage?.setImageResource(resId)
            productName?.text = product.name
            productPrice?.text = product.price
            itemView.setOnClickListener { itemClick(product) }
        }
    }


}