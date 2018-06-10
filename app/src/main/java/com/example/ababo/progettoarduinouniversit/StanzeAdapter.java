package com.example.ababo.progettoarduinouniversit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StanzeAdapter extends BaseAdapter {
    private Context context;
    private List<Stanza> elencoStanze;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Stanze");

    /**
     * Costruttore
     * @param context contesto in cui operare
     * @param elencoStanze elenco dei dati da visualizzare
     */
    public StanzeAdapter(Context context, List<Stanza> elencoStanze) {
        this.context = context;
        this.elencoStanze = elencoStanze;
    }

    // Invocato per ottenere il numero di elementi nella lista
    @Override
    public int getCount() {
        return elencoStanze.size();
    }

    // Invocato per ottenere l'iesimo elemento
    @Override
    public Stanza getItem(int i) {
        return elencoStanze.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Invocato per ottenere la view della riga da visualizzare
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Nel caso il layout non sia stato iniettato, provvedo
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.riga_stanze, null);

        // Ottengo gli ID correnti
        TextView vMatricola = view.findViewById(R.id.textMatricola);
        TextView vNomeCompleto = view.findViewById(R.id.textNome);

        // Imposto i valori da visualizzare nella list view
        Stanza s = elencoStanze.get(i);
        vMatricola.setText(s.getMatricola());
        vNomeCompleto.setText(s.getNome());
        //vNomeCompleto.setText(s.getCognome() + " " + s.getNome());

        // Restituisco la view alla lista
        return view;
    }

    public void setElencoStanze(List<Stanza> elencoStanze) {
        this.elencoStanze = elencoStanze;
        notifyDataSetChanged();
    }
    public void refreshdata(){
        elencoStanze.clear();
        myRef.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Stanza sa = new Stanza();
                sa.setNome(dataSnapshot.child("nome").getValue().toString());
                sa.setMatricola(dataSnapshot.child("matricola").getValue().toString());
                sa.setDispositivi(dataSnapshot.child("dispositivi").getValue().hashCode());
                elencoStanze.add(sa);

                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
