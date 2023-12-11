package com.example.consultamedica.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.consultamedica.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private var calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10) {
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10) {
                mes = "" + (monthOfYear + 1)
            } else {
                mes = (monthOfYear + 1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10) {
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }

        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {

            val medico1 = binding.medico1
            val medico2 = binding.medico2
            val medico3 = binding.medico3

            when {
                hora.isEmpty() -> {
                    mensagem(it,"Preencha o horário!", "#FF0000")
                }

                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it,"Clínica médica está fechada - horário de atendimento das 08:00 AS 19:00!", "#FF0000")

                }

                data.isEmpty() -> {
                    mensagem(it,"Coloque uma data!", "#FF0000")

                }

                medico1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Maria de Souza",data,hora)

                }

                medico2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Priscila Silva Gomes",data,hora)

                }

                medico3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Rodrigo Pereira Novais",data,hora)

                }

                else -> {
                    mensagem(it,"Escolha um médico!", "#FF0000")

                }
            }

        }

    }

    private fun mensagem(view: View, mensagem: String, cor: String) {
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }

    private fun salvarAgendamento(view: View, cliente: String, medico: String, data: String, hora: String){

       // val db = irebaseFirestore.getInstance()//

        val dadosUsuario = hashMapOf(
            "cliente" to cliente,
            "medico" to medico,
            "data" to data,
            "hora" to hora
        )

        //db.collection("agendamento").document(cliente).set(dadosUsuario).addOnCompleteListener {
            mensagem(view, "agendamento realizado com sucesso!", "#FF03DAC5")//
      //  }.addOnFailureListener {
            mensagem(view, "Erro no servidor!", "#FF0000")//
        }

    }


