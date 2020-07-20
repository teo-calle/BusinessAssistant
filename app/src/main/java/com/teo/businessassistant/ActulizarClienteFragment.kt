package com.teo.businessassistant

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.teo.businessassistant.model.Cliente
import kotlinx.android.synthetic.main.fragment_buscarcliente.*


class ActulizarClienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actulizarcliente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_buscar2.setOnClickListener {
            val nombre = et_nombre.text.toString()

            buscarenFirebase(nombre)

        bt_actualizar.setOnclickListener{


        }
        }

        }

    private fun buscarenFirebase(nombre: Any) {
        val database= FirebaseDatabase.getInstance()
        val myRef=database.getReference("clientes")
        var clienteExiste=false
        val postListener=object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val cliente=datasnapshot.getValue(Cliente::class.java)
                    if(cliente?.nombre_cliente==nombre){

                        clienteExiste=true

                    }
                }

                if(!clienteExiste)
                    Toast.makeText(requireContext(),"El cliente no existe", Toast.LENGTH_SHORT).show()

                Log.d("data",snapshot.toString())
            }
        }

        myRef.addValueEventListener(postListener)

    }


}