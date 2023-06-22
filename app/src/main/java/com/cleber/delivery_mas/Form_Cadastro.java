package com.cleber.delivery_mas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Form_Cadastro extends AppCompatActivity {

    private CircleImageView fotoUsuario;
    private Button bt_selecionarFoto,bt_castrar;
    private EditText edit_nome,edit_emal,edit_senha;
    private TextView tex_mensagemErro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        IniciarComponente();
        edit_nome.addTextChangedListener(cadastroTextWatcher);
        edit_emal.addTextChangedListener(cadastroTextWatcher);
        edit_senha.addTextChangedListener(cadastroTextWatcher);

    }
    public void IniciarComponente(){

        fotoUsuario = findViewById(R.id.fotoUsuario);
        bt_selecionarFoto = findViewById(R.id.selecioneFoto);
        bt_castrar  = findViewById(R.id.bt_cadastra);
        edit_nome = findViewById(R.id.edit_nome);
        edit_emal = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        tex_mensagemErro = findViewById(R.id.txt_mansagemErro);

        bt_castrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastraUsuario(v);

            }
        });

    }

    public void CadastraUsuario(View view){
        String email = edit_emal.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(view,"Cadastra raslizadp com sucsso !",Snackbar.LENGTH_INDEFINITE)
                            .setAction("ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    finish();
                                }
                            });

                    snackbar.show();

                    }else{

                    String  erro ;

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "coloque um senha de 6  carecteria !";
                    }catch (FirebaseAuthInvalidCredentialsException e){

                         erro = erro
                    }

                   }catch(Exception e){}

                }
            }
        });
    }


     TextWatcher cadastroTextWatcher = new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

             String nome = edit_nome.getText().toString();
             String email = edit_emal.getText().toString();
             String senha  = edit_senha.getText().toString();

             if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()){

                 bt_castrar.setEnabled(true);
                 bt_castrar.setBackgroundColor(getResources().getColor(R.color.red));

             }else {

                 bt_castrar.setEnabled(false);
                 bt_castrar.setBackgroundColor(getResources().getColor(R.color.gray));
             }

         }

         @Override
         public void afterTextChanged(Editable s) {

         }
     };
}