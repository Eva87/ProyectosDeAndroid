package com.grabador.audio;

import android.Manifest;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.media.MediaPlayer;
        import android.media.MediaRecorder;
        import android.media.MediaScannerConnection;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import androidx.appcompat.app.AlertDialog;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class MainActivity extends Activity implements MediaPlayer.OnCompletionListener {
    private  MediaRecorder recorder;
    private  MediaPlayer player;
    private File archivo;
    private ImageButton bgrabar, bparar, breproducir;
    private ImageButton hacerfotografia, vergaleria,vergaleriavideos;
    private int val;
    private String username;
    private long backElapsedTime;
    private ImageButton Buttonvolvergrabar;
    private Intent intent;
    private static final int REQUEST_VIDEO_CAPTURE =1;
    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=1;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA=0;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO=2;

    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECORD_AUDIO)){
            }else{
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECORD_AUDIO},MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            }else{
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgrabar = (ImageButton) findViewById(R.id.grabaraudio);
        bparar = (ImageButton) findViewById(R.id.Pararaudio);
        breproducir = (ImageButton) findViewById(R.id.Reproduciraudio);

        if(ContextCompat.checkSelfPermission (MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},1000);
        }
    }


    public void textoAVoz (View view){
        Intent intent = new Intent(MainActivity.this, TextoaVoz.class);
        startActivity(intent);
        finish();
    }

    public void grabar(View v) {
        bgrabar.setBackground(getDrawable(R.drawable.microfonorojo));
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory().getPath());
        try {
            SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = preferences.edit();
            val = preferences.getInt("guardaraudio", 0);
            archivo = File.createTempFile("Audio" + val , ".3gp", path);
            val++;
            myEditor.putInt("guardaraudio", val);
            myEditor.commit();
        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();
        bgrabar.setEnabled(false);
        bparar.setEnabled(true);
    }

    public void detener(View v) {
        bgrabar.setBackground(getDrawable(R.drawable.microfonoverde));
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }
        bgrabar.setEnabled(true);
        bparar.setEnabled(false);
        breproducir.setEnabled(true);
    }

    public void reproducir(View v) {
        bgrabar.setBackground(getDrawable(R.drawable.microfono));
        try {
            player.start();
        }catch(Exception e){
        }
        bgrabar.setEnabled(false);
        bparar.setEnabled(false);
        breproducir.setEnabled(false);
    }

    public void vozATexto(View view) {

        intent = new Intent(MainActivity.this, Vozatexto.class);
        startActivity(intent);
        finish();
    }

    public void onCompletion(MediaPlayer mp) {
        bgrabar.setEnabled(true);
        bparar.setEnabled(true);
        breproducir.setEnabled(true);
    }

    public void onBackPressed() {
        if (backElapsedTime + 1500 > System.currentTimeMillis()) {
            SharedPreferences preferences = getSharedPreferences(username, Context.MODE_PRIVATE);
            finish();
        } else {
            Toast.makeText(this, ("Pulsa de nuevo para salir"), Toast.LENGTH_SHORT).show();
        }
        backElapsedTime = System.currentTimeMillis();
    }

    public void onRequestPermissionsResult (int requestCode, String permissions[], int[] grantResult){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO:{
                if(grantResult.length>0 &&grantResult[0]==PackageManager.PERMISSION_GRANTED){
                }else{
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:{
                if(grantResult.length>0 && grantResult[0]==PackageManager.PERMISSION_GRANTED){
                }else{
                }
                return;
            }

        }
    }
}
