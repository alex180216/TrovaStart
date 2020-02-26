package com.example.trovastart.perfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trovastart.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PerfilPromoActivity extends AppCompatActivity {

    private String nombrePeluRecibido, telefonoPeluRecibido, horarioPeluRecibido;
    private String promoPeluRecibido;
    private String distanciaPeluRecibido, urlFacebookRecibido, urlInstagramRecibido;
    private Uri imagePeluRecibida;
    private String mapaPeluRecibido;
    private TextView perfilNombrePelu, perfilPromoPelu, perfilDistanciaPelu, label_description, perfilHorarioPelu, perfilTelefonoPelu, perfil_open_close;
    private ImageView image_mapa, buton_description, image_perfil, image_facebook, image_instagram, image_whatsapp;
    private LinearLayout linear_description;
    private String horaAperturaPeluRecibido;
    private String horaCierrePeluRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_promo);

        //inicializacion de elementos
        perfilNombrePelu = (TextView) findViewById(R.id.perfil_nombre_barberia);
        perfilPromoPelu = (TextView) findViewById(R.id.textView_promo_disponible);
        image_mapa = (ImageView) findViewById(R.id.imageMapa);
        perfilDistanciaPelu = (TextView) findViewById(R.id.tv_distancia);
        image_perfil = (ImageView) findViewById(R.id.image_peluqueria);
        perfilHorarioPelu = (TextView) findViewById(R.id.textView_horarios);
        //perfilPrecioPelu = (TextView) findViewById(R.id.textView_costo);
        image_facebook = (ImageView) findViewById(R.id.image_facebook);
        image_instagram = (ImageView) findViewById(R.id.image_instagram);
        image_whatsapp = (ImageView) findViewById(R.id.image_whatsapp);
        perfilTelefonoPelu = (TextView) findViewById(R.id.textView_telefono);
        perfil_open_close = (TextView) findViewById(R.id.textView_open_close);


        //recibo informacion enviada desde FragmentCercanas

        nombrePeluRecibido = getIntent().getExtras().get("nombre").toString();
        promoPeluRecibido = getIntent().getExtras().get("promo").toString();
        distanciaPeluRecibido = getIntent().getExtras().get("distancia").toString();
        mapaPeluRecibido = getIntent().getExtras().get("mapa").toString();
        imagePeluRecibida = Uri.parse(String.valueOf(getIntent().getExtras().get("image")));
        telefonoPeluRecibido = getIntent().getExtras().get("telefono").toString();
        //precioPeluRecibido = getIntent().getExtras().get("precio").toString();
        horarioPeluRecibido = getIntent().getExtras().get("horario").toString();
        horaAperturaPeluRecibido = String.valueOf(getIntent().getExtras().get("hora_apertura"));
        horaCierrePeluRecibido = String.valueOf(getIntent().getExtras().get("hora_cierre"));


        //asigna el string adecuado a cada elemento del perfil
        perfilNombrePelu.setText(nombrePeluRecibido.toUpperCase());
        perfilPromoPelu.setText(promoPeluRecibido.toUpperCase());
        perfilDistanciaPelu.setText(distanciaPeluRecibido.toUpperCase());
        Picasso.with(this).load(imagePeluRecibida).error(R.mipmap.ic_trova_logo).fit().centerInside().into(image_perfil);
        //perfilPrecioPelu.setText(precioPeluRecibido.toUpperCase());
        perfilHorarioPelu.setText(horarioPeluRecibido.toUpperCase());


        image_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapaPeluRecibido = getIntent().getExtras().get("mapa").toString().trim();
                Uri uri = Uri.parse(mapaPeluRecibido);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

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


        String hora_sistema = getHora("HH:mm"); //hora sistema
        String abierto = horaAperturaPeluRecibido; //hora establecida
        String cerrado = horaCierrePeluRecibido;


        if (abierto.compareTo(hora_sistema) < 0 & cerrado.compareTo(hora_sistema) > 0) { //paso el horario de apertura
            perfil_open_close.setText("ABIERTO");
            perfil_open_close.setTextColor(Color.GREEN);
        } else {
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
