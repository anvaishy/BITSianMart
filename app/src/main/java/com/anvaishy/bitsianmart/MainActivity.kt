package com.anvaishy.bitsianmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() , navigatorboi {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.hoster, loginFragment())
            .commit()

    }

    override fun navigatefrag(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.hoster, fragment)
        if(addToStack){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}