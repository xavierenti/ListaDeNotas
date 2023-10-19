package com.asd.passapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //verificando los id
        txtName=findViewById(R.id.txtName)
        txtLastName=findViewById(R.id.txtLastName)
        txtEmail=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtPassword)

        progressBar= findViewById(R.id.progressBar)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbReference= database.reference.child("User")


    }
    fun register(view:View){
        createNewAccount()
    }

    fun loginScreen(vie:View)
    {
        login()
    }

    private fun createNewAccount()
    {
        val name:String=txtName.text.toString()
        val lastName:String=txtLastName.text.toString()
        val email:String=txtEmail.text.toString()
        val password:String=txtPassword.text.toString()

        //Se comprueba que todos los campos esten llenos
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            progressBar.visibility=View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
            {
                    task ->
                //Comprobacion
                if(task.isComplete){
                    val user:FirebaseUser=auth.currentUser!!
                    verifyEmail(user)

                    val userBD=dbReference.child(user.uid)

                    userBD.child("Name").setValue(name)
                    userBD.child("lastName").setValue(lastName)
                    action()

                }
            }
        }
    }

    private fun action()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }
    private fun verifyEmail(user:FirebaseUser?)
    {
        user?.sendEmailVerification()?.addOnCompleteListener(this)
        {
                task ->

            if(task.isComplete)
            {
                Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
        }
    }

    fun login()
    {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}