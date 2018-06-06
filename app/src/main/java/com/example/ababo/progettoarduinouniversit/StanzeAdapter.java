package com.example.ababo.progettoarduinouniversit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;

import java.util.List;

public class StanzeAdapter extends BaseAdapter {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = firebaseDatabase.getReference("Stanze");
    private Context context;
    private List<Stanza> elencoStanze;

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

    public  void refreshdata() {
        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void getupdates(DataSnapshot dataSnapshot) {

        elencoStanze.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Stanza s = new Stanza();
            s.setNome("ciao");
            s.setDispositivi(2);
            s.setMatricola("ciao");
            elencoStanze.add(s);
        }

        }


    public void setElencoStanze(List<Stanza> elencoStanze) {
        this.elencoStanze = elencoStanze;
        notifyDataSetChanged();
    }

}
