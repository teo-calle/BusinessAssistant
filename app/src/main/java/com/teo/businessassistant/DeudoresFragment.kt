package com.teo.businessassistant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.teo.businessassistant.model.Deudores
import kotlinx.android.synthetic.main.fragment_actulizarcliente.*
import kotlinx.android.synthetic.main.fragment_deudores.*

class DeudoresFragment : Fragment() {
    var idDeudoresFirebase:String?=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estyrep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideDeudoresDatosET()
        val database= FirebaseDatabase.getInstance()
        val myRef=database.getReference("Deudores")
        val nombre_deudor = et_nombre_deudor.text.toString()
        val cantidad_deudor = et_deuda.text.toString().toLong()


        bt_buscard.setOnClickListener {
            buscarenFirebase(myRef,nombre_deudor)
        }

        bt_agregardeudor.setOnClickListener {

            guardardeudorEnFirebase(nombre_deudor,cantidad_deudor)
        }
    }

    private fun guardardeudorEnFirebase(nombre_deudor: String, cantidad_deudor: Long) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()  /*Instancia de nuestra base de datos*/
        val myRef: DatabaseReference =database.getReference("Deudores")
        val id :String?=myRef.push().key
        val Deudores= Deudores(
            id,
            nombre_deudor,
            cantidad_deudor
        )
        myRef.child(id!!).setValue(Deudores)
    }


    private fun buscarenFirebase(myRef: DatabaseReference, nombre_deudor: String) {
        var DeudorExiste=false
        val postListener=object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val Deudor=datasnapshot.getValue(Deudores::class.java)
                    if(Deudor?.nombre_deudor==nombre_deudor){
                        DeudorExiste=true
                       et_deuda.setText(Deudor.cantidad_deudor.toString())
                        et_nombre_deudor.setText(Deudor.nombre_deudor)
                        idDeudoresFirebase=Deudor.id
                    }
                }
                if(!DeudorExiste)
                    Toast.makeText(requireContext(),"El cliente no existe", Toast.LENGTH_SHORT).show()

            }
        }

        myRef.addValueEventListener(postListener)
    }


    private fun hideDeudoresDatosET() {
        et_deuda.visibility = View.GONE
        et_nueva_direccion.visibility = View.GONE
        et_correoactual.visibility = View.GONE
        bt_actualizarcliente.visibility=View.GONE
    }

}



