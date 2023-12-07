package com.example.consultamedica.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consultamedica.databinding.ServicosItemBinding
import com.example.consultamedica.model.Servicos

class ServicosAdapter(private val context: Context, private val listaServicos: MutableList<Servicos>):
    RecyclerView.Adapter<ServicosAdapter.ServicoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
      val itemLista = ServicosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ServicoViewHolder(itemLista)
    }

    override fun getItemCount() = listaServicos.size

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        holder.imgServico.setImageResource(listaServicos[position].img!!)
        holder.txtServico.text = listaServicos[position].nome
    }

    inner class  ServicoViewHolder(binding: ServicosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServico = binding.imgServico
        val txtServico = binding.txtServico



    }

}