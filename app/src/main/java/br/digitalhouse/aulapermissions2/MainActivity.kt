package br.digitalhouse.aulapermissions2

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.btnLigar)
        btn.setOnClickListener {

            //valida permissão
            if(ContextCompat.checkSelfPermission(this
                    ,android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){

                //solicita permissão ao usuario
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),1)
            }else{
                //inicia a tela de ligação com um telefone
                var ligar = Intent(Intent.ACTION_CALL,  Uri.parse("tel:" + "9999999999"))
                startActivity(ligar)
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //verifica de o request code é o mesmo que passamos na requisição
        if(requestCode == 1){
            //verifica se o item dentro da array de permissões é permissão garantida
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(this,"Permissão garantida",Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this,"Permissão Negada",Toast.LENGTH_LONG).show()
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}