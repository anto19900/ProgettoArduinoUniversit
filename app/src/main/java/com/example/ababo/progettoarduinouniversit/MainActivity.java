package com.example.ababo.progettoarduinouniversit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ababo.progettoarduinouniversit.datamodel.DataSource;
import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Lista stanze";
    // Riferimenti alle view
        private ListView vListaStanze;
        private final int REQ_ADD_STUDENTE = 1;
        private final int REQ_EDIT_STUDENTE = 2;
    private final String EXTRA_STUDENTE = "stanza";
    private FloatingActionButton vAggiungi;
    // Adapter e data source
        private StanzeAdapter adapter;
        private DataSource dataSource;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Ottengo i riferimenti alle view
            vAggiungi = findViewById(R.id.vAggiungi);
            vListaStanze = findViewById(R.id.listaStanze);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            // Ottengo un riferimento al datasource
            dataSource = DataSource.getInstance();

            // Creo l'adapter
            adapter = new StanzeAdapter(this, dataSource.getElencoStanze(""));

            // Associo l'adapter alla listview
            vListaStanze.setAdapter(adapter);

            vListaStanze.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Stanza stanza = (Stanza)adapter.getItem(i);

                    Intent intent = new Intent(view.getContext(),DettaglioStanzaActivity.class);

                   intent.putExtra(EXTRA_STUDENTE, stanza);

                    startActivity(intent);
                }
            });


            // Imposto l'azione relativa al pulsante Aggiungi
            vAggiungi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), EditStanzaActivity.class);
                    startActivityForResult(intent, REQ_ADD_STUDENTE);
                }
            });
        }

    // Processo dei valori di ritorno dalle altre activiy
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQ_ADD_STUDENTE :     // Activity di aggiunta
                if (resultCode == RESULT_OK) {   // Pulsante Ok premuto

                    // Estraggo le informazioni sullo studente da aggiungere
                    Stanza stanza = (Stanza) data.getSerializableExtra(EXTRA_STUDENTE);

                    if (stanza != null) {
                        // Aggiungo lo studente al datasource
                        dataSource.addStanza(stanza);
                        // Imposto il nuovo set di dati
                        adapter.setElencoStanze(dataSource.getElencoStanze(""));
                    }
                }
                break;

            case REQ_EDIT_STUDENTE :    // Activity di modifica
                if (resultCode == RESULT_OK) {   // Pulsante Ok premuto

                    // Estraggo le informazioni sullo studente da modificare
                    Stanza stanza = (Stanza) data.getSerializableExtra(EXTRA_STUDENTE);

                    if (stanza != null) {
                        // Sostituisco lo studente nel datasource
                        dataSource.deleteStanza("");
                        dataSource.addStanza(stanza);
                        // Imposto il nuovo set di dati
                        adapter.setElencoStanze(dataSource.getElencoStanze(""));
                    }
                }
                break;

            default:
                Log.v(TAG, "Result code non valido");
                break;
        }



        }
    }

