package com.asd.passapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var auth:FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        txtEmail=findViewById(R.id.txtEmail)
        auth= FirebaseAuth.getInstance()
        progressBar= findViewById(R.id.progressBar)
    }

    fun send(view:View)
    {
        val email=txtEmail.text.toString()

        //Verificar que no esta vacio
        if(!TextUtils.isEmpty(email))
        {
            //Enviar la contraseña al correo del usuario
            auth.sendPasswordResetEmail((email)).addOnCompleteListener(this)
            {
                //Comprobacion
                task ->

                if(task.isSuccessful)
                {
                    progressBar.visibility=View.VISIBLE
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else
                {
                    Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}