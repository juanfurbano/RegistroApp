package com.juanu.registroapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var sexo = "Femenino"
    //private lateinit var sexo: String inicializo despues
    val ciudades = arrayOf<String>("", "Medellin", "Bogota", "Pereira", "Cali", "Bucaramanga")
    private var hobbies = ""
    lateinit var option: Spinner
    private var ciudad = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pickDatebtn = findViewById(R.id.pickDateBtn) as Button

        option = findViewById(R.id.city_spinner) as Spinner
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ciudades)
        option.adapter = adapter
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, i: Int, l: Long) {
                ciudad = option.selectedItem.toString()
            }

        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        pickDatebtn.setOnClickListener {
            val dbp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                pickDatebtn.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
            }, year, month, day)
            dbp.show()
        }




        bnGuardar.setOnClickListener {
            //Listener del Boton, al presionarlo se ejecuta esta función
            var nombre = etNombre.text.toString()       //variable que almacena el nombre digitado en el EditText
            var password = etPassword.text.toString()
            var passwordcon = etPasswordCon.text.toString()


            var correo = etCorreo.text.toString()       //se debe convertir a string por eso .toString()
            var cedula = etCedula.text.toString()
            var date1 = "" + day + "/" + (month + 1) + "/" + year

            var respuesta =
                "Nombre: " + nombre + "\nContraseña: " + password + "\nCorreo: " + correo + "\nCedula: " + cedula + "\nSexo: " + sexo + "\nHobbies:" + hobbies + "\nFecha de Nacimiento: " + date1 + "\nCiudad de Nacimiento: " + ciudad  //Concatenación de los datos ingresados

            if (nombre.isEmpty() || password.isEmpty() || passwordcon.isEmpty() || correo.isEmpty() || cedula.isEmpty()) {
                txResultado.text = "Hay uno o mas campo vacios"
            } else {
                if (password == passwordcon) {
                    txResultado.text = respuesta//Se settean el resultado en el textview final
                } else {
                    txResultado.text = "Las contraseñas no coinciden"
                }
            }
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            when (view.id) {
                R.id.cbnadar ->
                    if (view.isChecked) {
                        hobbies = hobbies + " Nadar"
                    }
                R.id.cbbailar ->
                    if (view.isChecked) {
                        hobbies = hobbies + " Bailar"
                    }
                R.id.cbcine ->
                    if (view.isChecked) {
                        hobbies = hobbies + " Cine"
                    }
                R.id.cbfutbol ->
                    if (view.isChecked) {
                        hobbies = hobbies + " Futbol"
                    }
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            when (view.id) {
                R.id.rbmasculino ->
                    if (view.isChecked) {
                        sexo = "Masculino"
                    }
                R.id.rbfemenino ->
                    if (view.isChecked) {
                        sexo = "Femenino"
                    }
            }
        }
    }
}


