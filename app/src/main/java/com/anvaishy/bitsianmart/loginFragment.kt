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

class loginFragment : Fragment() {
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var fAuth: FirebaseAuth
    private lateinit var loggin :Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_login, container, false)
        user=view.findViewById(R.id.usernamelogin)
        pass=view.findViewById(R.id.passwordlogin)
        fAuth = Firebase.auth
        loggin=view.findViewById(R.id.loggin)
        view.findViewById<Button>(R.id.nayabanda).setOnClickListener {
            var navRegister = activity as navigatorboi
            navRegister.navigatefrag(registerFragment(),false)
        }
        view.findViewById<Button>(R.id.loggin).setOnClickListener {
            validatesign()
        }
        return view

    }
    private fun firebaseSignIn(){
        loggin.isEnabled = false
        fAuth.signInWithEmailAndPassword(user.text.toString(),
            pass.text.toString()).addOnCompleteListener{
                task ->
            if(task.isSuccessful){
                Toast.makeText(activity,"Login Successful", Toast.LENGTH_SHORT).show()
                var navRegister = activity as navigatorboi
                navRegister.navigatefrag(HomeFragment(),false)

            }else{


                loggin.isEnabled = true
                Toast.makeText(context,task.exception?.message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun validatesign() {
        when {
            TextUtils.isEmpty(user.text.toString().trim()) -> {
                Toast.makeText(activity, "Enter Username", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(pass.text.toString().trim()) -> {
                Toast.makeText(activity, "Enter password", Toast.LENGTH_SHORT).show()
            }
            user.text.toString().isNotEmpty() &&
                    pass.text.toString().isNotEmpty() -> {
                firebaseSignIn()
            }
        }
    }
}