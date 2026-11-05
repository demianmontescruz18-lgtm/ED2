package com.example.ed2

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvTitulo: TextView

    private lateinit var tvValor: TextView

    private lateinit var tvEstado: TextView

    private lateinit var llMain: LinearLayout

    private lateinit var btnContador : Button

    private var sensorPresion: Sensor? = null

    private lateinit var sensorManager: SensorManager

    var presionBaja = 0
    var presionAlta = 0
    var presionNormal = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvEstado = findViewById(R.id.tvEstado)
        tvValor = findViewById(R.id.tvValor)
        tvTitulo = findViewById(R.id.tvTitulo)
        llMain = findViewById(R.id.llMain)
        btnContador = findViewById(R.id.btnContador)



        //Permitir el uso de los servicioa de Hardware del dispositivo
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        //Seleccionar el sensor de luz
        sensorPresion = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)


        //Validr ¿r si el sensor se encuentra en el dispositico
        if (sensorPresion == null) {
            tvEstado.text = "No hay sensor de presión"
        }

        btnContador.setOnClickListener {

            Toast.makeText(this, "Baja: $presionBaja veces || Normal: $presionNormal veces || Alta: $presionAlta veces",
                Toast.LENGTH_SHORT).show()
        }


    }


    //METODOS PARA MEDIR LOS CAMVIOS EN EL VAOR
    override fun onSensorChanged(event: SensorEvent?) {

        //Verificar el uso correcto de sensor

        if (event?.sensor?.type == Sensor.TYPE_PRESSURE) {

            //Variable que guarde la indormacion del sensot
            val presion = event.values[0]

            tvValor.text = "Presion: $presion hPa"

            if (presion < 1000) {
                tvTitulo.setTextColor(Color.WHITE)
                tvEstado.setTextColor(Color.WHITE)
                tvValor.setTextColor(Color.WHITE)
                tvEstado.text = "Presión baja"
                llMain.setBackgroundColor(Color.BLUE)
                presionBaja++


            } else if (presion <= 1020){
                tvTitulo.setTextColor(Color.BLACK)
                tvEstado.setTextColor(Color.BLACK)
                tvValor.setTextColor(Color.BLACK)
                tvEstado.text = "Presión normal"
                llMain.setBackgroundColor(Color.GREEN)
                presionNormal++

            }else{
                tvTitulo.setTextColor(Color.BLACK)
                tvEstado.setTextColor(Color.BLACK)
                tvValor.setTextColor(Color.BLACK)
                tvEstado.text = "Presión alta"
                llMain.setBackgroundColor(Color.YELLOW)
                presionAlta++
            }

        }
    }

    //MÉTODO PARA MEDIR EL VALOR MATEMATICO DEL SENSOR
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


    override fun onResume() {
        super.onResume()
        sensorPresion?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }
}