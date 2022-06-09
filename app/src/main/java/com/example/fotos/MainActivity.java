package com.example.fotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_camera,btn_gira, btn_filtre;
    ImageView imgView;
    int gir=0;
    String colors="Sensa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_camera = findViewById(R.id.btn_camara);
        btn_gira = findViewById(R.id.btn_gira);
        btn_filtre = findViewById(R.id.btn_filtre);
        imgView = findViewById(R.id.imageView);


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obriCamare();
            }
        });
        btn_gira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gira();
            }
        });
        btn_filtre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtre();
            }
        });
    }


    private void obriCamare(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent, 1);
        //}

    }

    private void gira(){
        gir+=90;
        if(gir==270){
            imgView.setRotation(0);
        }else{  imgView.setRotation(gir);}


    }
    private void filtre(){

        if(colors.equals("Sensa")){
            imgView.setColorFilter((-50000000), PorterDuff.Mode.LIGHTEN);
            colors = "Amb";
        } else if(colors.equals("Amb")) {
            imgView.setColorFilter((+50000000), PorterDuff.Mode.LIGHTEN);
            colors = "Sensa";
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }

    }
}