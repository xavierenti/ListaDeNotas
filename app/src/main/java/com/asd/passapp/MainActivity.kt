package com.asd.passapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.asd.passapp.BaseDeDatos.SQLite

class MainActivity : AppCompatActivity() {

    private var tbPasswords:TableLayout?=null
    private var txtSitio: EditText?=null
    private var txtEmail: EditText?=null
    private var txtPass: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)


        inicializar()
        }


        fun addPass(view: View)
        {
            startActivity(Intent(this, AddPassActivity::class.java))
        }

        fun logout(view: View)
        {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        fun inicializar()
        {
            txtSitio = findViewById(R.id.txtSitio)
            txtEmail = findViewById(R.id.txtEmail)
            txtPass = findViewById(R.id.txtPass)
            tbPasswords = findViewById(R.id.tbPasswords)
        }

    fun search(view: View)
    {
        val con=SQLite(this, "passapp", null, 1)
        val dataBase=con.writableDatabase
        val sitio=txtSitio?.text.toString()

    }


    //No funciona
    fun llenarTabla()
    {
        val con= SQLite(this, "passapp", null, 1)
        val dataBase=con.writableDatabase
        val fila=dataBase.rawQuery("SELECT sitio, email, pass FROM passapp ", null)
        fila.moveToFirst()
        do{
            val registro= LayoutInflater.from(this).inflate(R.layout.add_tables_db,null,false)
            val tv_sitio=registro.findViewById<View>(R.id.tv_sitio) as TextView
            val tv_email=registro.findViewById<View>(R.id.tv_email) as TextView
            val tv_pass=registro.findViewById<View>(R.id.tv_pass) as TextView

            tv_sitio.setText(fila.getString(0))
            tv_email.setText(fila.getString(1))
            tv_pass.setText(fila.getString(2))
            tbPasswords?.addView(registro)
        }while(fila.moveToNext())
    }
    }