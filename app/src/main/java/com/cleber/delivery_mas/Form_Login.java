package com.cleber.delivery_mas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Form_Login extends AppCompatActivity {

    private TextView txt_cria_conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        IniciarComponentes();
      txt_cria_conta.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Form_Login.this,Form_Cadastro.class);
              startActivity(intent);
          }
      });

    }

    private void IniciarComponentes(){
        txt_cria_conta =findViewById(R.id.txt_cria_conta);
    }
}