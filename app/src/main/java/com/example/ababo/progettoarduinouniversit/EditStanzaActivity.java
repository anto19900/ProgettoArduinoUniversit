package com.example.ababo.progettoarduinouniversit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;

public class EditStanzaActivity extends AppCompatActivity {

    EditText vNome;
    EditText vDispositivi;
    EditText vMatricola;
    Button vOk;
    Button vCancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_studente);
        vNome = findViewById(R.id.tNome);
        vMatricola = findViewById(R.id.tMatricola);
        vDispositivi = findViewById(R.id.tDispositivi);
        vOk = findViewById(R.id.bOk);
        vCancel = findViewById(R.id.bAnnulla);

        final Intent intent = getIntent();
        Stanza stanza = (Stanza)intent.getSerializableExtra("stanza");

        if (stanza != null ){
            vMatricola.setText(stanza.getMatricola());
            vDispositivi.setText(Integer.toString(stanza.getDispositivi()));
            vNome.setText(stanza.getNome());

        }

        vOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stanza stanza = leggiDatiStanza();
                if (stanza != null){
                    Intent intent = new Intent();
                    intent.putExtra("stanza",stanza);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"DATI OBBLIGATORI",Toast.LENGTH_LONG).show();
                }
            }
        });


        vCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    private Stanza leggiDatiStanza(){
        Stanza stanza = new Stanza();
        stanza.setMatricola(vMatricola.getText().toString());
        stanza.setNome(vNome.getText().toString());
        stanza.setCognome(vDispositivi.toString());

        return stanza;


    }
}
