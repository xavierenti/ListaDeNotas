package com.asd.passapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asd.passapp.BaseDeDatos.SQLite

class AddPassActivity : AppCompatActivity() {

    private var txtSitio: EditText?=null
    private var txtEmail: EditText?=null
    private var txtPass: EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_password)

        initializeVariables()
    }

    //Guardar la contraseña y que te lleve a la pagina principal
    fun save (view: View)
    {

        var con=SQLite(this, "passapp" , null, 1)
        var dataBase=con.writableDatabase
        var sitio=txtSitio?.text.toString()
        var email=txtEmail?.text.toString()
        var pass=txtPass?.text.toString()

        if(sitio.isEmpty()==false && email.isEmpty()==false && pass.isEmpty()==false)
        {
            var registro= ContentValues()
            registro.put("sitio", sitio)
            registro.put("email", email)
            registro.put("pass", pass)
            dataBase.insert("passapp",null, registro)
            txtSitio?.setText("")
            txtEmail?.setText("")
            txtPass?.setText("")
            Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this, "Deben estar todos los campos llenos", Toast.LENGTH_LONG).show()
        }

        startActivity(Intent(this,MainActivity::class.java))
    }

    private fun initializeVariables()
    {
        txtSitio = findViewById(R.id.txtSitio)
        txtEmail = findViewById(R.id.txtEmail)
        txtPass = findViewById(R.id.txtPass)

    }

}