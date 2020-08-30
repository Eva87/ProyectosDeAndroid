package com.dibujar.paint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import yuku.ambilwarna.AmbilWarnaDialog;


public class MainActivity extends AppCompatActivity {
        private Button colama;
        private Button colbla;
        private Button colazu;
        private Button colnar;
        private Button colroj;
        private Button colverd;
        private Button colmorad;
        private Button colmarron;
        private Button colverdclaro;
        private Button colazulclaro;
        private Button colnegro;
        private Button guardar;
        private Button borrar;
        private Button desehacer;
        private String username;
        private int botonactivo;
        private Intent intent;
        int val = 0;
        private long backElapsedTime;

        int mDefaultColor;
        Button botonpiker;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            initViews();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},225);
                }
            }

            mDefaultColor=ContextCompat.getColor(MainActivity.this, R.color.colorPrimary);



            botonpiker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    openColorPicker();

                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(mDefaultColor);
                }
            });

            
            colama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.YELLOW);
                }
            });

            colbla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.WHITE);
                }
            });

            colazu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.BLUE);
                }
            });

            colnar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#ff7b00"));
                }
            });

            colroj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.RED);
                }
            });

            colverd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#296b46"));
                }
            });

            colmorad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#930895"));
                }
            });

            colmarron.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.LTGRAY);
                }
            });

            colverdclaro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#3ddb24"));
                }
            });

            colazulclaro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#0fdde5"));
                }
            });

            colnegro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(Color.parseColor("#4c070f"));
                }
            });

            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEditor = preferences.edit();
                    val = preferences.getInt("capturaDibujo", 0);
                    saveFile(val);
                    val++;
                    myEditor.putInt("capturaDibujo", val);
                    myEditor.commit();
                }
            });

            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.clear(board.getContext());
                }
            });

            desehacer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Board board=(Board) findViewById(R.id.board_view);
                    board.setEraser();
                }
            });

        /*    Buttonvolverpizarra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });*/
        }

        public void saveFile(int v){
            Board board =(Board) findViewById(R.id.board_view);
            Bitmap bitmap =board.getBitmap();
            String dir = Environment.getExternalStorageDirectory().toString();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File file=new File(dir,"dibujo" + timeStamp + ".png");
            OutputStream out;
            try{
                out =new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
                out.flush();
                out.close();
                Toast.makeText(MainActivity.this, ("Guardado")+dir, Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                new AlertDialog.Builder(this).setMessage("Error: " + e.getLocalizedMessage()).setPositiveButton(android.R.string.ok, null).show();
            }
        }

        public void onRequestPermissionsResults (int permsRequestCode, String [] permissions, int [] grantResults) {
            switch (permsRequestCode){
                case 200:
                    boolean writeAccepted = grantResults [0] == PackageManager.PERMISSION_GRANTED;
                    if (writeAccepted){
                        SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEditor = preferences.edit();
                        val = preferences.getInt("capturaDibujo", 0);
                        saveFile(val);
                        val++;
                        myEditor.putInt("capturaDibujo", val);
                        myEditor.commit();
                    }else{
                        Toast.makeText(MainActivity.this,"Error guardando", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

        private void initViews() {

            colama = findViewById(R.id.buttonamarillo);
            colbla = findViewById(R.id.buttonblanco);
            colazu = findViewById(R.id.buttonazul);
            colnar = findViewById(R.id.buttonnaranja);
            colroj = findViewById(R.id.buttonrojo);
            colverd = findViewById(R.id.buttonverde);
            colmorad = findViewById(R.id.buttonmorado);
            colmarron = findViewById(R.id.buttonmarron);
            colverdclaro = findViewById(R.id.buttonverdeclaro);
            colazulclaro = findViewById(R.id.buttonazulclaro);
            colnegro = findViewById(R.id.buttonnegro);
            guardar = findViewById(R.id.buttonguardardibujo);
            borrar = findViewById(R.id.buttonborrarpizarra);
            desehacer = findViewById(R.id.buttondeshacer);
            botonpiker = findViewById(R.id.botonpicker);

            colama.setBackgroundColor(Color.YELLOW);
            colbla.setBackgroundColor(Color.WHITE);
            colazu.setBackgroundColor(Color.BLUE);
            colnar.setBackgroundColor(Color.parseColor("#ff7b00"));
            colroj.setBackgroundColor(Color.RED);
            colverd.setBackgroundColor(Color.parseColor("#296b46"));
            colmorad.setBackgroundColor(Color.parseColor("#930895"));
            colnegro.setBackgroundColor(Color.parseColor("#4c070f"));
            colverdclaro.setBackgroundColor(Color.parseColor("#3ddb24"));
            colazulclaro.setBackgroundColor(Color.parseColor("#0fdde5"));
            colmarron.setBackgroundColor(Color.BLACK);
        }

        public void onBackPressed() {
            if (backElapsedTime + 1500 > System.currentTimeMillis()) {
                finish();
            } else {
                Toast.makeText(this, "Pulsa nuevamente para salir", Toast.LENGTH_SHORT).show();
            }
            backElapsedTime = System.currentTimeMillis();
        }

        public void openColorPicker(){

            AmbilWarnaDialog colorPiker=new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {

                }

                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    mDefaultColor=color;

                    Board board=(Board) findViewById(R.id.board_view);
                    board.setPaintColor(mDefaultColor);
                }
            });
            colorPiker.show();
        }
    }
