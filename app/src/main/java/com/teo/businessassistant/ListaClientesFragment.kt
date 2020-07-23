package com.teo.businessassistant
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.teo.businessassistant.model.Cliente
import com.teo.businessassistant.model.ClienteRVAdapter
import kotlinx.android.synthetic.main.fragment_listaclientes.*

class ListaClientesFragment : Fragment() {
    var idClienteFirebase: String? = ""
    private var allClientes:MutableList<Cliente> = mutableListOf()
    //lateinit var ClienteRVAdapter: ClienteRVAdapter
    private lateinit var clientesAdapter: ClienteRVAdapter
    private lateinit var myRef: DatabaseReference
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val user: FirebaseUser? = mAuth.currentUser
    val listaC = user?.uid.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_listaclientes, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cargarClientes()
        rv_clientes.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        clientesAdapter = ClienteRVAdapter(allClientes as ArrayList<Cliente>)
        rv_clientes.adapter = clientesAdapter
    }

    private fun cargarClientes(){
        val database= FirebaseDatabase.getInstance()
        myRef =database.getReference(listaC)

        //  allClientes.clear()

        val postListener= object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError){
            }
            override fun onDataChange(snapshot:DataSnapshot){
                for(datasnapshot: DataSnapshot in snapshot.children){
                    val cliente = datasnapshot.getValue(Cliente::class.java)
                    allClientes.add(cliente!!)
                    idClienteFirebase = cliente?.id



                }
                clientesAdapter.notifyDataSetChanged()
            }
        }
        myRef.addListenerForSingleValueEvent(postListener)
    }
    /*override fun onResume(){
        super.onResume()
        cargarClientes()
    }*/

}



