package com.senai.vsconnect_kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senai.vsconnect_kotlin.R
import com.senai.vsconnect_kotlin.models.Propaganda
import com.squareup.picasso.Picasso

class ListaPropagandasAdapter(
    private val context: Context,
    private val listaPropagandas: List<Propaganda>,
    private val itemClickListener: OnItemClickListener // Adicione este parâmetro
) : RecyclerView.Adapter<ListaPropagandasAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(propaganda: Propaganda)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //Essa função é responsável por chamar e atribuir valores para as views do item da RecyclerView
        fun vincularDadosNoItem(propaganda: Propaganda) {
            val imagemPropaganda = itemView.findViewById<ImageView>(R.id.view_imagem_propaganda)
            val urlImagem = "http://172.16.20.189:8080/img/" + propaganda.url_img

            Picasso.get().load(urlImagem).into(imagemPropaganda)


            val textoRedireiconarPropaganda = itemView.findViewById<TextView>(R.id.view_redirecionar_propaganda)
            textoRedireiconarPropaganda.text = "Me leva até lá!"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPropagandasAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context);

        val view = inflater.inflate(R.layout.fragment_propaganda, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaPropagandasAdapter.ViewHolder, position: Int) {
        val itemPropaganda = listaPropagandas[position]

        val textoRedireiconarPropaganda = holder.itemView.findViewById<TextView>(R.id.view_redirecionar_propaganda)

        textoRedireiconarPropaganda.setOnClickListener {
            // Chame o método onItemClick do OnItemClickListener
            itemClickListener.onItemClick(itemPropaganda)
        }

        holder.vincularDadosNoItem(itemPropaganda)
    }

    override fun getItemCount(): Int {
        return listaPropagandas.size
    }
}