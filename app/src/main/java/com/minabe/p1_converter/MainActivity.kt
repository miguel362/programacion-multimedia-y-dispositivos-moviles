package com.minabe.p1_converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    //variables para enlazar los editText
    val resultDecimal: EditText by lazy {
        findViewById(R.id.etDecimal)
    }

    val resultBinary: EditText by lazy {
        findViewById(R.id.etBinary)
    }

    //variables para los botones
    lateinit var btnCalcularADecimal: Button
    lateinit var btnCalcularABinario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcularADecimal = findViewById(R.id.btnADecimal) as Button
        btnCalcularABinario = findViewById(R.id.btnABinario) as Button

        btnCalcularADecimal.setOnClickListener(){
            if(correcto(resultBinary.text.toString())){//método para saber si es una cadena de 0 y 1
                if(resultBinary.text.toString().length>10){ //Si la cantidad de cifras es mayor que 10...
                    resultDecimal.setText("Máximo 10 dígitos por favor")
                }else {
                    //Guardo en una variable lo que hay en el edittext binario
                    var numBin: Int = Integer.parseInt(resultBinary.text.toString())
                    //Variable donde guardar el resultado final
                    var resultado = 0
                    var i = 0
                    var remainder: Int
                    while (numBin != 0) {
                        //Guardo en remainder el resto de dividir numBin entre 10
                        remainder = numBin % 10
                        //Quito la ultima cifra de numBin
                        numBin /= 10
                        //Voy sumando en resultado el resultado de cada vuelta del bucle.
                        resultado += (remainder * Math.pow(2.0, i.toDouble())).toInt()
                        ++i
                    }
                    resultDecimal.setText("$resultado")
                }
            } else{
                resultBinary.setError("Error al convertir")
                resultDecimal.setText("")//limpio pantalla
            }
        }

        btnCalcularABinario.setOnClickListener {
            if(correctoDecimal(resultDecimal.text.toString())){//método para saber si hay una cadena de números
                if(Integer.parseInt(resultDecimal.text.toString())>1023){//si el número es mayor que 1023
                    resultBinary.setText("No se puede calcular un valor mayor que el 1023...")
                } else {
                    //Guardo en una variable de tipo entero el dato puesto en el editText
                    val numDecim: Int = Integer.parseInt(resultDecimal.text.toString())
                    //Guardo en una variable el resultado del método que pasa de decimal
                    // a binario el número anterior
                    val binary = Integer.toBinaryString(numDecim)
                    //Pongo en el editText correspondiente el resultado en binario
                    resultBinary.setText("$binary")
                }
            } else{
                resultDecimal.setError("Error al convertir")
                resultBinary.setText("")//limpio pantalla
            }
        }
    }
    //función para averiguar si el campo binario no está vacío, no es nulo y sólo contiene 0 ó 1
    fun correcto(s: String?): Boolean {
        if (s == null || s.length == 0) {
            return false
        }
        for (c in s)
        {
            if (c < '0' || c > '1') {
                return false
            }
        }
        return true
    }

    ////función para averiguar si el campo Decimal no está vacío, no es nulo y sólo contiene números
    fun correctoDecimal(s: String?): Boolean {
        if (s == null || s.length == 0) {
            return false
        }
        for (c in s)
        {
            if (c < '0' || c > '9') {
                return false
            }
        }
        return true
    }
}