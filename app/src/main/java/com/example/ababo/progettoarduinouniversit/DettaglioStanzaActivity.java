package com.example.ababo.progettoarduinouniversit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        Intent intent = getIntent();
        Stanza stanza = (Stanza)intent.getSerializableExtra("stanza");
        if (stanza != null) {
            vCasa.setText(stanza.getNome());
            bCasa1.setTransitionName("ciao");
            bCasa2.setTransitionName("come va");
            //qui altre cose da passare tipo pulsante
        }

    }
}