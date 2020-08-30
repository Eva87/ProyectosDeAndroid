package com.grabador.audio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Vozatexto extends AppCompatActivity {

    private final int REQ_CODE_SPEECH=100;
    private TextView entradavoz;
    private ImageButton botonhablar;
    ArrayList <String> result;
    private long backElapsedTime;;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vozatexto);

        entradavoz = findViewById(R.id.textodeentrada);
        botonhablar= findViewById(R.id.botonhablar);

        botonhablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarentradavoz();
            }
        });
    }

    private void iniciarentradavoz() {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    REQ_CODE_SPEECH);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result = null;
        if (requestCode==REQ_CODE_SPEECH){
            if(resultCode==RESULT_OK && data != null){
                result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                entradavoz.setText(result.get(0));
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String TextCode = "Fichero" + date ;

        try {
            File root=Environment.getExternalStorageDirectory();
            String rootString= root.getAbsolutePath();
            String tmpString=rootString+"/TranscripcionesdeVoz";
            File tmp=new File(tmpString);
            tmp.mkdir();
            String filePath="";
            String fileString = TextCode + ".txt";
            filePath=tmpString+"/" + fileString;
            File file=new File(tmp,fileString);
            if ( file.exists()) {
                entradavoz.append("\nFichero ya existe"+filePath);
            }else{
                FileOutputStream out=new FileOutputStream(file);
                PrintWriter writer= new PrintWriter(out);
                String s = entradavoz.getText().toString();
                writer.println(s);
                writer.println("");
                writer.println("Fin transcripción");
                writer.flush();
                writer.close();
                entradavoz.append("\nFchero grabado"+filePath);
            }
        } catch (Exception e) {
            e .printStackTrace();
            entradavoz.append("\nError:"+e);
        }

        //esta funcion hace que espere 5 segundos
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //que hacer despues de 5 segundos
                Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();
                entradavoz.setText("");

            }
        }, 5000);
    }
    public void onBackPressed() {
        if (backElapsedTime + 1500 > System.currentTimeMillis()) {
            Intent intent = new Intent(Vozatexto.this, MainActivity.class);
            SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);

            finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Pulsa de nuevo para salir", Toast.LENGTH_SHORT).show();
        }
        backElapsedTime = System.currentTimeMillis();
    }
}
