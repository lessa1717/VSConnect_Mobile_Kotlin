package com.senai.vsconnect_kotlin.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.senai.vsconnect_kotlin.R
import com.senai.vsconnect_kotlin.adapters.ListaPropagandasAdapter
import com.senai.vsconnect_kotlin.apis.EndpointInterface
import com.senai.vsconnect_kotlin.apis.RetrofitConfig
import com.senai.vsconnect_kotlin.databinding.FragmentListaPropagandasBinding
import com.senai.vsconnect_kotlin.models.Propaganda
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPropagandasFragment : Fragment(), ListaPropagandasAdapter.OnItemClickListener {

    private val clienteRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val endpoints = clienteRetrofit.create(EndpointInterface::class.java)

    private var _binding: FragmentListaPropagandasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListaPropagandasBinding.inflate(inflater, container, false)

        val root: View = binding.root

        // organiza os itens da Recycler em ordem vertical, sendo um debaixo do outro
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerServicos.layoutManager = layoutManager


        // Buscando as propagandas
        endpoints.listarPropagandas().enqueue(object : Callback<List<Propaganda>> {
            override fun onResponse(call: Call<List<Propaganda>>, response: Response<List<Propaganda>>) {
                val propagandas = response.body()

                // Configurando o adapter com a lista de propagandas e a ação de clique
                binding.recyclerServicos.adapter =
                    propagandas?.let { ListaPropagandasAdapter(requireContext(), it, this@ListaPropagandasFragment) }
            }

            override fun onFailure(call: Call<List<Propaganda>>, t: Throwable) {
                println("Falha na requisição: ${t.message}")
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(propaganda: Propaganda) {
        // Use o contexto fornecido no construtor do adaptador
        val sharedPreferences = requireContext().getSharedPreferences("idPropaganda", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        editor.putString("idPropaganda", propaganda.id.toString())

        editor.apply()

        findNavController().navigate(R.id.nav_informacoes_propaganda)
    }

}
