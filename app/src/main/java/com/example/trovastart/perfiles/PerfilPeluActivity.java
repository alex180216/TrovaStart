package com.example.trovastart.perfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trovastart.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PerfilPeluActivity extends AppCompatActivity {

    private String nombrePeluRecibido, direccionPeluRecibido, telefonoPeluRecibido, distanciaRecibida;
    private String precioPeluRecibido, horarioPeluRecibido, urlFacebookRecibido, urlInstagramRecibido, urlMapaRecibido;
    private Uri imagenRecibido;
    private TextView perfilNombrePelu, perfilTelefonoPelu, perfilPrecioPelu, perfil_open_close, perfilHorarioPelu, perfilDistanciaPelu;
    private TextView perfilDireccionPelu;
    private ImageView image_perfil, image_facebook, image_instagram, image_whatsapp, image_mapa;
    private String horaAperturaPeluRecibido;
    private String horaCierrePeluRecibido;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pelu);

        //inicializacion de elementos
        perfilNombrePelu = (TextView) findViewById(R.id.perfil_nombre_barberia);
        perfilDireccionPelu = (TextView) findViewById(R.id.textView_direccion_peluqueria);
        perfilTelefonoPelu = (TextView) findViewById(R.id.textView_telefono);
        image_perfil = (ImageView) findViewById(R.id.image_peluqueria);
        perfilHorarioPelu = (TextView) findViewById(R.id.textView_horarios);
        //perfilPrecioPelu = (TextView) findViewById(R.id.textView_costo);
        image_facebook = (ImageView) findViewById(R.id.image_facebook);
        image_instagram = (ImageView) findViewById(R.id.image_instagram);
        image_whatsapp = (ImageView) findViewById(R.id.image_whatsapp);
        image_mapa = (ImageView) findViewById(R.id.imageMapa);
        perfilDistanciaPelu = (TextView) findViewById(R.id.tv_distancia);
        perfil_open_close = (TextView) findViewById(R.id.textView_open_close);


        //iniciacion de firestore
        db = FirebaseFirestore.getInstance();




        //recibo informacion enviada desde FragmentCercanas

        nombrePeluRecibido = getIntent().getExtras().get("nombre").toString();
        direccionPeluRecibido = getIntent().getExtras().get("direccion").toString();
        imagenRecibido = Uri.parse(String.valueOf(getIntent().getExtras().get("image")));
        telefonoPeluRecibido = getIntent().getExtras().get("telefono").toString();
        //precioPeluRecibido = getIntent().getExtras().get("precio").toString();
        horarioPeluRecibido = getIntent().getExtras().get("horario").toString();
        distanciaRecibida = getIntent().getExtras().get("distancia").toString();
        horaAperturaPeluRecibido = String.valueOf(getIntent().getExtras().get("hora_apertura"));
        horaCierrePeluRecibido = String.valueOf(getIntent().getExtras().get("hora_cierre"));






        //asigna el string adecuado a cada elemento del perfil
        perfilNombrePelu.setText(nombrePeluRecibido.toUpperCase());
        perfilDireccionPelu.setText(direccionPeluRecibido.toUpperCase());
        Picasso.with(this).load(imagenRecibido).error(R.mipmap.ic_trova_logo).fit().centerInside().into(image_perfil);
        perfilTelefonoPelu.setText(telefonoPeluRecibido.toUpperCase());
        //perfilPrecioPelu.setText(precioPeluRecibido.toUpperCase());
        perfilHorarioPelu.setText(horarioPeluRecibido.toUpperCase());
        perfilDistanciaPelu.setText(distanciaRecibida.toUpperCase());



        image_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlFacebookRecibido = getIntent().getExtras().get("facebook").toString();
                Uri uri = Uri.parse(urlFacebookRecibido);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        image_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlInstagramRecibido = getIntent().getExtras().get("instagram").toString();
                Uri uri = Uri.parse(urlInstagramRecibido);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        image_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "No hay numero de WhatsApp asociado", Toast.LENGTH_SHORT).show();
            }
        });

        image_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlMapaRecibido = getIntent().getExtras().get("mapa").toString().trim();
                Uri uri = Uri.parse(urlMapaRecibido);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        String hora_sistema = getHora("HH:mm"); //hora sistema
        String abierto = horaAperturaPeluRecibido; //hora establecida
        String cerrado = horaCierrePeluRecibido;


        if(abierto.compareTo(hora_sistema) < 0 & cerrado.compareTo(hora_sistema) > 0){ //paso el horario de apertura
            perfil_open_close.setText("ABIERTO");
            perfil_open_close.setTextColor(Color.GREEN);
        }else{
            perfil_open_close.setText("CERRADO");
            perfil_open_close.setTextColor(Color.RED);
        }



    }
    public String getHora(String strFormato) {

        Calendar objCalendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);
        String strHora = simpleDateFormat.format(objCalendar.getTime());
        return strHora;

    }

}

