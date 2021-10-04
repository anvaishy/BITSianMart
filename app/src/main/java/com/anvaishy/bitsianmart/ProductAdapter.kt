package com.anvaishy.bitsianmart

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.*
import android.graphics.Movie.decodeFile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.File

class ProductAdapter(private var productList:MutableList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ProductViewHolder {

        val layoutView:View = LayoutInflater.from(parent.context).
                inflate(R.layout.product_card_view,parent,false)
        return ProductViewHolder(layoutView)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val file = productList[position].img
        val storageRef = FirebaseStorage.getInstance().reference.child(file)
        val localfile = File.createTempFile("tempimg","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.productImage.setImageBitmap(bitmap)
        }
        holder.productTitle.text = productList[position].title
        holder.productPrice.text = productList[position].price
        holder.productTag.text = productList[position].tag


    }
    override fun getItemCount(): Int {
        return productList.size
    }
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.product_image)
        var productTitle: TextView = view.findViewById(R.id.product_title)
        var productPrice: TextView = view.findViewById(R.id.product_price)
        var productTag: TextView = view.findViewById(R.id.product_tag)
    }
}