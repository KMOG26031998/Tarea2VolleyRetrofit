package com.example.tareacasifinal;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tareacasifinal.Bancos.transfersubscriptionbanklist;
import com.example.tareacasifinal.Interface.kushkiapi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private TextView mJsonview;
    private Button btnenvio;
    public EditText txtNombre, txtClave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonview = findViewById(R.id.jsonView);
        gsontransfersubscriptionbanklist();
    }
    private void gsontransfersubscriptionbanklist() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        kushkiapi ushikap = retrofit.create(kushkiapi.class);
        Call<List<transfersubscriptionbanklist>> call = ushikap.gettransfersubscriptionbanklist("f1db42b50ae44600a18140cf031a4db6");
        call.enqueue(new Callback<List<transfersubscriptionbanklist>>() {
            @Override
            public void onResponse(Call<List<transfersubscriptionbanklist>> call, Response<List<transfersubscriptionbanklist>> response) {
                if (!response.isSuccessful()) {
                    mJsonview.setText("Codigo:" + response.code());
                    return;
                }
                List<transfersubscriptionbanklist> postttransfersubscriptionbanklist = response.body();
                for (transfersubscriptionbanklist tt : postttransfersubscriptionbanklist) {
                    String content = "";
                  /*  content += "Serie: " + tt.getcode() + "\n";*/
                    content += "Nombre_Banco: " + tt.getname() + "\n";
                    mJsonview.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<transfersubscriptionbanklist>> call, Throwable t) {
                mJsonview.setText(t.getMessage());
            }
        });
    }
    public void btnEnviar(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityLog.class);
        txtNombre = (EditText) findViewById(R.id.TxtNombre);
        txtClave = (EditText) findViewById(R.id.TxtClave);
        Bundle a = new Bundle();
        a.putString("usr", txtNombre.getText().toString());
        a.putString("pass", txtClave.getText().toString());
        intent.putExtras(a);
        startActivity(intent);
    }
     public boolean Validarcamposvacios(){
        boolean retorno=true;
        String c1= txtNombre.getText().toString();
        String c2=txtClave.getText().toString();
        if(c1.isEmpty()){
            Toast.makeText(this, "Debes de ingresar sus nombres", Toast.LENGTH_SHORT).show();
            retorno=false;
        }if(c2.isEmpty()){
            Toast.makeText(this, "Debes de ingresar la contraseña correcta", Toast.LENGTH_SHORT).show();
            retorno=false;
        }
        return retorno;
    }
}