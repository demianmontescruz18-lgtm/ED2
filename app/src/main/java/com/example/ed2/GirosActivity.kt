package com.example.ed2

import android.graphics.Color
import android.view.View
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GirosActivity : AppCompatActivity() , SensorEventListener {

    private lateinit var tvvalores : TextView
    private lateinit var tvestado : TextView
    private lateinit var imagen : ImageView
    private lateinit var sensorManager: SensorManager

    // crear objeto parao ocupar el sensor de luz
    private var giroscopio : Sensor? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_giros)

        tvestado = findViewById(R.id.tvestado)
        tvvalores = findViewById(R.id.tvvalores)
        imagen = findViewById(R.id.img)
        imagen.setImageResource(R.drawable.fondo3)

        //permitir el uso de los serivios de hardware del dispositivo
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager


        //seleccionar el sensor de luz
        giroscopio = sensorManager .getDefaultSensor(Sensor.TYPE_GYROSCOPE)


        //validar si el sensor se encuentra en el dispositivo
        if(giroscopio == null){
            tvestado.text = "No hay sensores "
        }



    }

    //METODO OBLIGATORIO PARA EL USO DE SENSORES


    //metodo para medir los cambios de lso alores del sensor
    override fun onSensorChanged(event: SensorEvent?) {

        //verificar el uso correcto del sensor
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {

            //variable que guarde la informacion del sensor
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]



            tvvalores.text = "X: $x\nY: $y\nZ: $z"

            if (x >= 2) {
                tvestado.text = "Giro fuerte en el eje X "
                findViewById<View>(R.id.main).setBackgroundColor(Color.GREEN)
                imagen.setImageResource(R.drawable.fondo)

            } else if (y >= 2) {
                tvestado.text = "Giro fuerte en el eje Y 🐺"
                imagen.setImageResource(R.drawable.fondo1)
                findViewById<View>(R.id.main).setBackgroundColor(Color.YELLOW)
                findViewById<TextView>(R.id.tvtitulo).setTextColor(Color.YELLOW)
                tvvalores.setTextColor(Color.BLACK)
                tvestado.setTextColor(Color.BLACK)



            } else if (z >= 2) {
                tvestado.text = "Giro fuerte en el eje Z 🤑"
                imagen.setImageResource(R.drawable.fondo2)
                findViewById<View>(R.id.main).setBackgroundColor(Color.BLUE)
                findViewById<TextView>(R.id.tvtitulo).setTextColor(Color.BLUE)
                tvvalores.setTextColor(Color.BLACK)
                tvestado.setTextColor(Color.BLACK)


            } else {
                tvestado.text = "Ningun movimiento detectado :V"
                imagen.setImageResource(R.drawable.fondo3)
                findViewById<View>(R.id.main).setBackgroundColor(Color.WHITE)
                findViewById<TextView>(R.id.tvtitulo).setTextColor(Color.WHITE)
                tvvalores.setTextColor(Color.BLACK)
                tvestado.setTextColor(Color.BLACK)

            }
        }
    }



    //Metodo para medir el valor matemativo del sensor
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //METODO PARA DETENER EL USO DEL SENSOR



    }


    override fun onResume() {
        super.onResume()
        giroscopio?.let{

            sensorManager.registerListener(

                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL

            )

        }
    }

}

