package com.teo.businessassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_navegation.*


class NavegationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navegation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_inventario.setOnClickListener {
            findNavController().navigate(R.id.action_navegationFragment_to_inventarioFragment)
        }

        bt_compras.setOnClickListener {
            findNavController().navigate(R.id.action_navegationFragment_to_comordFragment)
        }

        bt_tareas.setOnClickListener {
            findNavController().navigate(R.id.action_navegationFragment_to_tareasFragment)
        }
        bt_clientes.setOnClickListener {
            findNavController().navigate(R.id.action_navegationFragment_to_clientesFragment)
        }

        bt_estadisticas.setOnClickListener {
            findNavController().navigate(R.id.action_navegationFragment_to_estyrepFragment)
        }
    }
}