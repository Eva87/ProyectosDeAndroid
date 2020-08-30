package com.juego.memory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class JuegoMemory extends AppCompatActivity {

    private String username;

    ImageView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c0;

    Integer[] CartasArray ={101,102,103,104,105,106,201,202,203,204,205,206};

    int imagen101, imagen102, imagen103, imagen104, imagen105, imagen106,
            imagen201, imagen202, imagen203, imagen204, imagen205, imagen206;

    int primeracarta, segundacarta;
    int primeraclicada, segundaclicada;
    int Numerocarta =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_memory);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        c0 = (ImageView) findViewById(R.id.la0);
        c1 = (ImageView) findViewById(R.id.la1);
        c2 = (ImageView) findViewById(R.id.la2);
        c3 = (ImageView) findViewById(R.id.la3);
        c4 = (ImageView) findViewById(R.id.la4);
        c5 = (ImageView) findViewById(R.id.la5);
        c6 = (ImageView) findViewById(R.id.la6);
        c7 = (ImageView) findViewById(R.id.la7);
        c8 = (ImageView) findViewById(R.id.la8);
        c9 = (ImageView) findViewById(R.id.la9);
        c10 = (ImageView) findViewById(R.id.la10);
        c11 = (ImageView) findViewById(R.id.la11);


        c0.setTag("0");
        c1.setTag("1");
        c2.setTag("2");
        c3.setTag("3");
        c4.setTag("4");
        c5.setTag("5");
        c6.setTag("6");
        c7.setTag("7");
        c8.setTag("8");
        c9.setTag("9");
        c10.setTag("10");
        c11.setTag("11");


        RecursoFrontaldeCartas();
        Collections.shuffle(Arrays.asList(CartasArray));


        c0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c0,carta);
            }
        });


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c1,carta);
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c2,carta);
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c3,carta);
            }
        });


        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c4,carta);
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c5,carta);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c6,carta);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c7,carta);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c8,carta);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c9,carta);
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c10,carta);
            }
        });

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                doStuff(c11,carta);
            }
        });
    }

    private void doStuff (ImageView iv, int cart){
        if(CartasArray[cart] == 101){
            iv.setImageResource(imagen101);
        } else if(CartasArray[cart] == 102){
            iv.setImageResource(imagen102);
        } else if(CartasArray[cart] == 103) {
            iv.setImageResource(imagen103);
        } else if(CartasArray[cart] == 104) {
            iv.setImageResource(imagen104);
        } else if(CartasArray[cart] == 105) {
            iv.setImageResource(imagen105);
        } else if(CartasArray[cart] == 106) {
            iv.setImageResource(imagen106);
        } else if(CartasArray[cart] == 201) {
            iv.setImageResource(imagen201);
        } else if(CartasArray[cart] == 202) {
            iv.setImageResource(imagen202);
        } else if(CartasArray[cart] == 203) {
            iv.setImageResource(imagen203);
        } else if(CartasArray[cart] == 204) {
            iv.setImageResource(imagen204);
        } else if(CartasArray[cart] == 205) {
            iv.setImageResource(imagen205);
        } else if(CartasArray[cart] == 206) {
            iv.setImageResource(imagen206);
        }

        if (Numerocarta == 1){
            primeracarta = CartasArray[cart];
            if(primeracarta > 200){
                primeracarta = primeracarta - 100;
            }
            Numerocarta = 2;
            primeraclicada = cart;
            iv.setEnabled(false);
        }else if(Numerocarta == 2){
            segundacarta = CartasArray[cart];
            if ( segundacarta >200){
                segundacarta = segundaclicada - 100;
            }
            Numerocarta = 1;
            segundaclicada = cart;
            c0.setEnabled(false);
            c1.setEnabled(false);
            c2.setEnabled(false);
            c3.setEnabled(false);
            c4.setEnabled(false);
            c5.setEnabled(false);
            c6.setEnabled(false);
            c7.setEnabled(false);
            c8.setEnabled(false);
            c9.setEnabled(false);
            c10.setEnabled(false);
            c11.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);

        }
    }

    private void calculate(){
        if (primeracarta == segundacarta){
            if (primeraclicada ==0){
                c0.setVisibility(View.INVISIBLE);
            } else if (primeraclicada ==1){
                c1.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==2){
                c2.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==3){
                c3.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==4){
                c4.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==5){
                c5.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==6){
                c6.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==7){
                c7.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==8){
                c8.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==9){
                c9.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==10){
                c10.setVisibility(View.INVISIBLE);
            }else if (primeraclicada ==11){
                c11.setVisibility(View.INVISIBLE);
            }

            if (segundaclicada ==0){
                c0.setVisibility(View.INVISIBLE);
            } else if (segundaclicada ==1){
                c1.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==2){
                c2.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==3){
                c3.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==4){
                c4.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==5){
                c5.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==6){
                c6.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==7){
                c7.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==8){
                c8.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==9){
                c9.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==10){
                c10.setVisibility(View.INVISIBLE);
            }else if (segundaclicada ==11){
                c11.setVisibility(View.INVISIBLE);
            }

        }else{
            c0.setImageResource(R.drawable.fondocarta);
            c1.setImageResource(R.drawable.fondocarta);
            c2.setImageResource(R.drawable.fondocarta);
            c3.setImageResource(R.drawable.fondocarta);
            c4.setImageResource(R.drawable.fondocarta);
            c5.setImageResource(R.drawable.fondocarta);
            c6.setImageResource(R.drawable.fondocarta);
            c7.setImageResource(R.drawable.fondocarta);
            c8.setImageResource(R.drawable.fondocarta);
            c9.setImageResource(R.drawable.fondocarta);
            c10.setImageResource(R.drawable.fondocarta);
            c11.setImageResource(R.drawable.fondocarta);

        }

        c0.setEnabled(true);
        c1.setEnabled(true);
        c2.setEnabled(true);
        c3.setEnabled(true);
        c4.setEnabled(true);
        c5.setEnabled(true);
        c6.setEnabled(true);
        c7.setEnabled(true);
        c8.setEnabled(true);
        c9.setEnabled(true);
        c10.setEnabled(true);
        c11.setEnabled(true);

        checkend();

    }

    private void checkend(){
        if(c0.getVisibility() == View.INVISIBLE &&
                c1.getVisibility() == View.INVISIBLE &&
                c2.getVisibility() == View.INVISIBLE &&
                c3.getVisibility() == View.INVISIBLE &&
                c4.getVisibility() == View.INVISIBLE &&
                c5.getVisibility() == View.INVISIBLE &&
                c6.getVisibility() == View.INVISIBLE &&
                c7.getVisibility() == View.INVISIBLE &&
                c8.getVisibility() == View.INVISIBLE &&
                c9.getVisibility() == View.INVISIBLE &&
                c10.getVisibility() == View.INVISIBLE &&
                c11.getVisibility() == View.INVISIBLE ){
            AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(JuegoMemory.this);
            alertdialogbuilder
                    .setMessage("Fin partida ")
                    .setCancelable(true)
                    .setPositiveButton("Nueva ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),JuegoMemory.class);
                            intent = new Intent(JuegoMemory.this, JuegoMemory.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          /*  Intent intent = new Intent(JuegoMemory.this, JuegoMemory.class);
                            startActivity(intent);*/
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertdialogbuilder.create();
            alertDialog.show();
        }
    }


    private void RecursoFrontaldeCartas (){
        imagen101 = R.drawable.la0;
        imagen102 = R.drawable.la1;
        imagen103 = R.drawable.la2;
        imagen104 = R.drawable.la3;
        imagen105 = R.drawable.la4;
        imagen106 = R.drawable.la5;
        imagen201 = R.drawable.la6;
        imagen202 = R.drawable.la7;
        imagen203 = R.drawable.la8;
        imagen204 = R.drawable.la9;
        imagen205 = R.drawable.la10;
        imagen206 = R.drawable.la11;
    }



}
