package com.example.trovastart.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.trovastart.R;
import com.example.trovastart.models.Peluqueria;
import com.example.trovastart.perfiles.PerfilPeluActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.sql.Time;
import java.util.Calendar;


public class FragmentCercanas extends Fragment {

    private View cercanasView;
    private RecyclerView myCercanasRecycler;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter<Peluqueria, PeluViewHolder> adapter;
    private Query query;

    private Double myLatitud = 0.0;
    private Double myLongitud = 0.0;






    public FragmentCercanas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //lectura de datos firestore (inicio)
        cercanasView = inflater.inflate(R.layout.fragment_fragment_cercanas, container, false);
        myCercanasRecycler = cercanasView.findViewById(R.id.recycler_cercanas);
        myCercanasRecycler.setLayoutManager(new LinearLayoutManager(cercanasView.getContext()));

        db = FirebaseFirestore.getInstance();
        final CollectionReference peluRef = db.collection("Peluquerias");
        query = peluRef.orderBy("nombre", Query.Direction.ASCENDING);





        return cercanasView;
        ////lectura de datos firestore (fin)





    }





    @Override
    public void onStart() {
        super.onStart();

        FirestoreRecyclerOptions<Peluqueria> options = new FirestoreRecyclerOptions.Builder<Peluqueria>()
                .setQuery(query, Peluqueria.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Peluqueria, PeluViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PeluViewHolder dataViewHolder, final int i, @NonNull Peluqueria data) {
                dataViewHolder.setData(data.getNombre(), data.getDireccion(), data.getDistancia());

                final ImageView boton = dataViewHolder.itemView.findViewById(R.id.boton_estrella);

                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boton.setImageResource(R.drawable.ic_estrella_llena);
                        Boolean favorito = true;
                    }
                });



                //EVENTO ON CLICK EN CADA PELUQUERIA
                dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visit_pelu_nombre = getItem(i).getNombre();
                        String visit_pelu_direccion = getItem(i).getDireccion();
                        String visit_pelu_image = getItem(i).getImage_direction();
                        String visit_pelu_telefono = getItem(i).getTelefono();
                        String visit_pelu_horario = getItem(i).getHorario();
                        String visit_pelu_precio = getItem(i).getPrecio();
                        String visit_pelu_distancia = getItem(i).getDistancia();
                        int visit_pelu_hora_apertura = getItem(i).getHora_apertura();
                        int visit_pelu_hora_cierre = getItem(i).getHora_cierre();
                        String visit_pelu_facebook = getItem(i).getfacebook().trim();
                        String visit_pelu_instagram = getItem(i).getInstagram().trim();
                        String visit_pelu_mapa = getItem(i).getMapa().trim();

                        Intent perfilPeluIntent = new Intent(getActivity(), PerfilPeluActivity.class);

                        //enviame datos de nombre y direccion de la casa a la otra activity
                        perfilPeluIntent.putExtra("nombre", visit_pelu_nombre);
                        perfilPeluIntent.putExtra("direccion", visit_pelu_direccion);
                        perfilPeluIntent.putExtra("image", visit_pelu_image);
                        perfilPeluIntent.putExtra("telefono", visit_pelu_telefono);
                        perfilPeluIntent.putExtra("horario", visit_pelu_horario);
                        perfilPeluIntent.putExtra("precio", visit_pelu_precio);
                        perfilPeluIntent.putExtra("hora_apertura", visit_pelu_hora_apertura);
                        perfilPeluIntent.putExtra("hora_cierre", visit_pelu_hora_cierre);
                        perfilPeluIntent.putExtra("facebook", visit_pelu_facebook);
                        perfilPeluIntent.putExtra("instagram", visit_pelu_instagram);
                        perfilPeluIntent.putExtra("distancia", visit_pelu_distancia);
                        perfilPeluIntent.putExtra("mapa", visit_pelu_mapa);



                        getActivity().startActivity(perfilPeluIntent);
                    }
                });


            }

            @NonNull
            @Override
            public PeluViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cercanas, parent, false);
                return new PeluViewHolder(view);
            }
        };
        myCercanasRecycler.setAdapter(adapter);



        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class PeluViewHolder extends RecyclerView.ViewHolder {
        private View view;

        PeluViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        //TODO agregar latitud, longitud, promociones e image

        void setData(String dataNombre, String dataDireccion, String dataDistancia) {
            TextView nombre = view.findViewById(R.id.textView_nombre_Peluqueria);
            TextView direccion = view.findViewById(R.id.textView_direccion_peluqueria);
            TextView distancia = view.findViewById(R.id.textView_distancia);



            nombre.setText(dataNombre.toUpperCase());
            direccion.setText(dataDireccion.toUpperCase());
            distancia.setText(dataDistancia.toUpperCase());









        }



        }
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }





