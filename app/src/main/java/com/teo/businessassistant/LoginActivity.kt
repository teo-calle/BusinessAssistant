package com.teo.businessassistant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    val mAuth: FirebaseAuth =FirebaseAuth.getInstance();

 /* Inicia en NavegationFragment cuando ya hay una sesión iniciada*/
    override fun onStart() {
        super.onStart()
        val user=mAuth.currentUser
        if(user != null)
            goToMainActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        tv_registrar.setOnClickListener {
            startActivity(Intent(this,RegistroActivity::class.java))
        }

        bt_iniciarsesion.setOnClickListener{
            val correo=et_CorreoCliente.text.toString()
            val contra=et_Contra.text.toString()

            signInWithFirebase(correo, contra)
        }
    }

/* Iniciar sesion con firebase*/
    private fun signInWithFirebase(correo: String, contra: String) {
        mAuth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    goToMainActivity()
                } else {
                    showMessage("Authentication failed.")
                    Log.w("TAG", "signInWithnEmail:failure", task.getException());
                }
            }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(
            this, msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
