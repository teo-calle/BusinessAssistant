package com.teo.businessassistant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correo:String?=""
    var contra:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datosRecibidos : Bundle? =intent.extras
        if(datosRecibidos != null){
            correo = datosRecibidos?.getString("correo")
            contra = datosRecibidos?.getString("contra")
            //tv_correo.text=correo
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if (item.itemId==R.id.item1){
           FirebaseAuth.getInstance().signOut()
           goToLoginActivity()
       }
        return super.onOptionsItemSelected(item)
    }
    private fun goToLoginActivity( ){
        val intent = Intent(this,LoginActivity::class.java)
        //intent.putExtra("correo",correo)
        //intent.putExtra("contra",contra)
        startActivity(intent)
        finish()
    }
}
