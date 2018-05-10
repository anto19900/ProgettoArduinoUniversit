package com.example.ababo.progettoarduinouniversit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;


public class DettaglioStanzaActivity extends AppCompatActivity {
    TextView vCasa;
    Button bCasa1;
    Button bCasa2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_stanza);
        vCasa = findViewById(R.id.tCasaSpecifica);
        bCasa1 = findViewById(R.id.buttonCasa1);
        bCasa2 = findViewById(R.id.buttonCasa2);
        TextView vDispositivi2 = findViewById(R.id.tDispositivi);
        TextView tMatricola = findViewById(R.id.tMatricolaDettaglio);
        Intent intent = getIntent();
        Stanza stanza = (Stanza)intent.getSerializableExtra("stanza");
        Toolbar toolbar3 = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar3);
        if (stanza != null) {
            vCasa.setText(stanza.getNome());
            tMatricola.setText(stanza.getMatricola());
            vDispositivi2.setText(String.valueOf(stanza.getDispositivi()));

            //qui altre cose da passare tipo pulsante
        }

    }

}