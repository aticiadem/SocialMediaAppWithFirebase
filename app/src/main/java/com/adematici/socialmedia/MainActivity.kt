package com.adematici.socialmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser!=null){
            val intent = Intent(this,MainScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun signUp(view: View){
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }

    fun signIn(view: View){
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
            //
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val intent = Intent(this,MainScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this,"Please Fill In All Fields",Toast.LENGTH_LONG).show()
        }
    }


}