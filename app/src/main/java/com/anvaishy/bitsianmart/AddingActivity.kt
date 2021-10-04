package com.anvaishy.bitsianmart

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class AddingActivity : AppCompatActivity() {
    lateinit var pname:EditText
    lateinit var pprice:EditText
    lateinit var ptag:EditText
    lateinit var addbtn:Button
    lateinit var dispimg:ImageView
    lateinit var imgbtn:Button
    lateinit var img:Uri
    lateinit var product:String
    lateinit var price:String
    lateinit var tag:String
    lateinit var url:String
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)
        pname=findViewById(R.id.Pname)
        pprice=findViewById(R.id.Pprice)
        ptag=findViewById(R.id.Ptag)
        var back = findViewById<Button>(R.id.back)
        addbtn=findViewById(R.id.addData)
        imgbtn=findViewById(R.id.addimage)
        dispimg=findViewById(R.id.dispimg)
        val getimg=registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                img=it
                dispimg.setImageURI(it)
            }
        )
        imgbtn.setOnClickListener {
            getimg.launch("image/")
        }
        back.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.backer, HomeFragment())
                .commit()
        }
        addbtn.setOnClickListener {
            upload()
        }
    }
    private fun upload() {
        val formatter = SimpleDateFormat("yyyy_MM__dd__HH__mm__ss", Locale.getDefault())
        val now = Date()
        val fileName=formatter.format(now).toString()
        val storagereference = FirebaseStorage.getInstance().getReference(fileName)
        storagereference.putFile(img).addOnSuccessListener{


        }
        database=FirebaseDatabase.getInstance().getReference("products")

        url=fileName
        product=pname.text.toString()
        price=pprice.text.toString()
        tag=ptag.text.toString()

        val product = productfire(url,product,price,tag)

        database.child(fileName).setValue(product).addOnSuccessListener {

            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            pname.text.clear()
            pprice.text.clear()
            ptag.text.clear()
            dispimg.setImageResource(R.drawable.logo)
        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}