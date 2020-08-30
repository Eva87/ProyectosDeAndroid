package com.grabador.audio;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TextoaVoz extends AppCompatActivity {

    private Button hablarAhoraBoton;
    private EditText editText;
    TextoaVozManager textoavozManager=null;
    private long backElapsedTime;;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textoa_voz);
        textoavozManager=new TextoaVozManager();
        textoavozManager.init(this);

        editText =findViewById(R.id.input_text);
        hablarAhoraBoton=findViewById(R.id.speak_now);

        hablarAhoraBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=editText.getText().toString();
                textoavozManager.initQueue(text);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textoavozManager.shutDown();
    }

    public void onBackPressed() {
        if (backElapsedTime + 1500 > System.currentTimeMillis()) {
            Intent intent = new Intent(TextoaVoz.this, MainActivity.class);
            SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Pulsa de nuevo para salir", Toast.LENGTH_SHORT).show();
        }
        backElapsedTime = System.currentTimeMillis();
    }
}