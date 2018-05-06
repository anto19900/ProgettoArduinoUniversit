package com.example.ababo.progettoarduinouniversit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
            vAggiungi = findViewById(R.id.fabAggiungi);
            vListaStanze = findViewById(R.id.listaStanze);
            Toolbar toolbar = findViewById(R.id.toolbar);

            setSupportActionBar(toolbar);

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

                registerForContextMenu(vListaStanze);
        }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch per individuare la voce di menu scelta
        switch (item.getItemId()) {

            case R.id.menuGrafici:
                // L'utente ha scelto "impostazioni"
                Log.v(TAG, "Menu-> grafici");
                Intent intent2 = new Intent(MainActivity.this,GraficiActivity.class);
                startActivity(intent2);
                return true;

            case R.id.menuImpostazioni:
                // L'utente ha scelto "logout"
                Log.v(TAG, "Menu-> impostazioni");
                Intent intent3 = new Intent(MainActivity.this,ImpostazioniActivity.class);
                startActivity(intent3);
                return true;

            case R.id.menuLog:
                // L'utente ha scelto "logout"
                Log.v(TAG, "Menu-> Log-in");
                Intent intent4 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent4);
                return true;

            default:
                // Scelta non riconosciuta, passo il controllo al metodo della classe base
                return super.onOptionsItemSelected(item);
        }
    }


    // Processo dei valori di ritorno dalle altre activiy
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQ_ADD_STUDENTE:     // Activity di aggiunta
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

            case REQ_EDIT_STUDENTE:    // Activity di modifica
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
  // creazione contex menu
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.lista_stanza, menu);
        }

        // Selezione di un elemento nel context menu

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // All'interno di info.position posso leggere la posizione dell'elemento selezionato

            switch (item.getItemId()) {

                case R.id.eliminaMenu:
                    // Eliminazione studente
                  dataSource.deleteStanza(adapter.getItem(info.position).getMatricola());
                    adapter.setElencoStanze(dataSource.getElencoStanze(""));
                    return true;

                case R.id.modificamMenu:
                    // Modifica studente. Chiedo lo studente all'adapter e lo passo all'altra activiy
                    Stanza stanza = adapter.getItem(info.position);
                   // matricolaCorrente = stanza.getMatricola();    // Salvo la matricola per poterla eventualmente modificare
                    Intent intent = new Intent(getApplicationContext(), EditStanzaActivity.class);
                     intent.putExtra(EXTRA_STUDENTE, stanza );
                    // Faccio partire l'activiy in modalità edit
                    startActivityForResult(intent, REQ_EDIT_STUDENTE);
                    return true;

                default:
                    return super.onContextItemSelected(item);
            }

        }
    }
    

