package com.example.trovastart.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trovastart.R;
import com.example.trovastart.models.Peluqueria;
import com.example.trovastart.perfiles.PerfilPeluActivity;
import com.example.trovastart.perfiles.PerfilPromoActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FragmentPromo extends Fragment {

    private View promoView;
    private RecyclerView myPromoRecycler;
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter<Peluqueria, PeluViewHolder> adapter;
    private Query query;

    private OnFragmentInteractionListener mListener;

    public FragmentPromo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //lectura de datos firestore (inicio)
        promoView = inflater.inflate(R.layout.fragment_fragment_promo, container, false);
        myPromoRecycler = promoView.findViewById(R.id.recycler_promo);
        myPromoRecycler.setLayoutManager(new LinearLayoutManager(promoView.getContext()));

        db = FirebaseFirestore.getInstance();
        final CollectionReference peluRef = db.collection("Peluquerias");
        query = peluRef.orderBy("nombre", Query.Direction.ASCENDING);

        return promoView;
        ////lectura de datos firestore (fin)
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirestoreRecyclerOptions<Peluqueria> options = new FirestoreRecyclerOptions.Builder<Peluqueria>()
                .setQuery(query, Peluqueria.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Peluqueria, FragmentPromo.PeluViewHolder>(options) {

            @NonNull
            @Override
            public PeluViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promo, parent, false);
                return new PeluViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PeluViewHolder holder, final int position, @NonNull Peluqueria model) {

                    holder.setData(model.getNombre(), model.getPromo());

                final ImageView boton = holder.itemView.findViewById(R.id.boton_estrella);

                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boton.setImageResource(R.drawable.ic_estrella_llena);
                        Boolean favorito = true;
                    }
                });

                    //EVENTO ON CLICK EN CADA ITEM
                    holder.view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String visit_pelu_nombre = getItem(position).getNombre();
                            String visit_pelu_promo = getItem(position).getPromo();
                            String visit_pelu_image = getItem(position).getImage_direction();
                            String visit_pelu_telefono = getItem(position).getTelefono();
                            String visit_pelu_horario = getItem(position).getHorario();
                            String visit_pelu_precio = getItem(position).getPrecio();
                            String visit_pelu_distancia = getItem(position).getDistancia();
                            int visit_pelu_hora_apertura = getItem(position).getHora_apertura();
                            int visit_pelu_hora_cierre = getItem(position).getHora_cierre();
                            String visit_pelu_facebook = getItem(position).getfacebook().trim();
                            String visit_pelu_instagram = getItem(position).getInstagram().trim();
                            String visit_pelu_mapa = getItem(position).getMapa().trim();

                            Intent perfilPeluIntent = new Intent(getActivity(), PerfilPromoActivity.class);

                            //enviame datos de nombre y direccion de la casa a la otra activity
                            perfilPeluIntent.putExtra("nombre", visit_pelu_nombre);
                            perfilPeluIntent.putExtra("promo", visit_pelu_promo);
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
        };

        myPromoRecycler.setAdapter(adapter);



        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private class PeluViewHolder extends RecyclerView.ViewHolder {
        private View view;

        PeluViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        //TODO agregar latitud, longitud, promociones e image

        void setData(String dataNombre, String dataPromo) {
            TextView nombre = view.findViewById(R.id.textView_nombre_Peluqueria);
            TextView promo = view.findViewById(R.id.textView_promo);



            nombre.setText(dataNombre.toUpperCase());
            promo.setText(dataPromo.toUpperCase());




        }



    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
