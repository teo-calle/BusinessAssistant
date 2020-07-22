package com.teo.businessassistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.teo.businessassistant.model.Cliente
import com.teo.businessassistant.model.ClienteRVAdapter
import kotlinx.android.synthetic.main.fragment_listaclientes.*

class ListaClientesFragment : Fragment() {
   var allClientes:MutableList<Cliente> =mutableListOf()
    lateinit var ClienteRVAdapter: ClienteRVAdapter
    private lateinit var clientesAdapter: ClienteRVAdapter
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listaclientes, container, false)
        cargarClientes()
        this.rv_clientes.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        //ClienteRVAdapter.notifyDataSetChanged()

        clientesAdapter = ClienteRVAdapter(allClientes as ArrayList<Cliente>)
        rv_clientes.adapter = clientesAdapter


    }


    private fun cargarClientes(){
        val database= FirebaseDatabase.getInstance()
        myRef =database.getReference("clientes")

      //  allClientes.clear()

        val postListener= object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError){
            }
            override fun onDataChange(dataSnapshot:DataSnapshot){
            for(Snapshot:DataSnapshot in dataSnapshot.children){
                System.out.println(dataSnapshot.toString())
                val cliente = Snapshot.getValue(Cliente::class.java)
                allClientes.add(cliente!!)
            }
        }
    }
    myRef.addListenerForSingleValueEvent(postListener)
}
   override fun onResume(){
       super.onResume()
       cargarClientes()
   }

}



