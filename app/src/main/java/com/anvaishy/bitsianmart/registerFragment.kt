package com.anvaishy.bitsianmart

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registerFragment : Fragment() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cfpassword: EditText
    private lateinit var fAuth : FirebaseAuth
    private lateinit var goin :Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_register, container, false)
        username = view.findViewById(R.id.usernamereg)
        password = view.findViewById(R.id.passwordreg)
        cfpassword = view.findViewById(R.id.passwordconfirm)
        fAuth=  Firebase.auth
        goin = view.findViewById(R.id.goin)
        view.findViewById<Button>(R.id.goin).setOnClickListener {

            validateEmptyForm()
        }
        return view
    }
    private fun verifybitsian(uname: String): Boolean {
        var ch0=uname.get(0)
        if(ch0!='f'&&ch0!='F'){
            return false
        }
        var ch1=uname.get(1)
        if(!ch1.isDigit()){
            return false
        }
        var ch2=uname.get(2)
        if(!ch2.isDigit()){
            return false
        }
        var ch3=uname.get(3)
        if(!ch3.isDigit()){
            return false
        }
        var ch4=uname.get(4)
        if(!ch4.isDigit()){
            return false
        }
        var ch5=uname.get(5)
        if(!ch5.isDigit()){
            return false
        }
        var ch6=uname.get(6)
        if(!ch6.isDigit()){
            return false
        }
        var ch7=uname.get(7)
        if(!ch7.isDigit()){
            return false
        }
        var ch8=uname.get(8)
        if(!ch8.isDigit()){
            return false
        }
        if(uname.get(9)!='@'){
            return false
        }
        return true;
    }
    private fun firebaseSignUp(){
        goin.isClickable=false
        goin.isEnabled=false
        fAuth.createUserWithEmailAndPassword(
            username.text.toString(),
            password.text.toString()
        ).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                var navRegister = activity as navigatorboi
                navRegister.navigatefrag(HomeFragment(),false)
            }else{
                goin.isClickable=true
                goin.isEnabled=true
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                Toast.makeText(activity, "Enter Username", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                Toast.makeText(activity, "Enter password", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(cfpassword.text.toString().trim())->{
                Toast.makeText(activity, "retype password", Toast.LENGTH_SHORT).show()
            }
            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    cfpassword.text.toString().isNotEmpty() ->
            { if(verifybitsian(username.text.toString())) {
                if (password.text.toString().length >= 5) {
                    if (password.text.toString() == cfpassword.text.toString()) {
                        firebaseSignUp()
                    } else {
                        Toast.makeText(activity, "Passwords didnt match", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Password too short", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(activity, "Non BITSian Email Address", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }
}