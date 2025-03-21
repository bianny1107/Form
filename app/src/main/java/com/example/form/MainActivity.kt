package com.example.form

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.form.ui.theme.FormTheme

class MainActivity : ComponentActivity() {
    var txtcodigo: EditText?=null //declaración de campos a insertar
    var txtdescripcion: EditText?=null
    var txtprecio: EditText?=null

    override fun onCreate(savedInstanceState:Bundle?){
       super.onCreate(savedInstanceState)
       setContentView(R.layout.layout)

        txtcodigo = findViewById(R.id.txtcodigo) //enlace de campos a insertar
        txtdescripcion = findViewById(R.id.txtdescripcion)
        txtprecio = findViewById(R.id.txtprecio)
    }

    fun insertar(view: View){ //funcion que se ejecuta al hacer click en el boton +
        var conexion = SQLite(this, "tienda", null, 1) //conexion
        var dataBase = conexion.writableDatabase //permite que la base de datos sea escribible
        var codigo = txtcodigo?.text.toString() //permite obtener el texto dentro de la variable txtcodigo
        var descripcion = txtdescripcion?.text.toString()
        var precio = txtprecio?.text.toString()

        if(codigo.isEmpty() == false && descripcion.isEmpty() == false && precio.isEmpty() == false){
            var registro = ContentValues() //arreglo que permite ingresar cada uno de los registros
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)

            dataBase.insert("productos", null, registro) //permite insertar el registro en la db

            txtcodigo?.setText(" ")
            txtdescripcion?.setText(" ")
            txtprecio?.setText(" ")

            Toast.makeText(this, "Insertado exitosamente", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this, "Cada campo debe ser rellenado", Toast.LENGTH_LONG).show()
        }
    }
}



