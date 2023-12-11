package com.example.consultamedica.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.consultamedica.R
import com.example.consultamedica.adapter.ServicosAdapter
import com.example.consultamedica.databinding.ActivityHomeBinding
import com.example.consultamedica.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private var listaServicos: MutableList<Servicos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo,$nome"
        val recyclerViewServicos = binding.recyclerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this, 20)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServico()

        binding.btAgendar.setOnClickListener {
            val intent = Intent( this, Agendamento::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
    }

    }

    private  fun  getServico(){

        val servico1 = Servicos(R.drawable.img3, "Consulta Presencial")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.img4, "Telemedicina")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.img3, "Dúvidas")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.img3, "Suporte")
        listaServicos.add(servico4)


    }

}