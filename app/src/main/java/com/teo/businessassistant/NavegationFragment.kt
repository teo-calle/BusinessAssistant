package com.teo.businessassistant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

        val mAuth:FirebaseAuth=FirebaseAuth.getInstance()
        val user: FirebaseUser?=mAuth.currentUser
        val correo=user?.email

        Toast.makeText(activity?.applicationContext,"Bienvenido $correo", Toast.LENGTH_SHORT).show()




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