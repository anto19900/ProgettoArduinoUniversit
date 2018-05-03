package com.example.ababo.progettoarduinouniversit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ababo.progettoarduinouniversit.datamodel.DataSource;
import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;

public class MainActivity extends AppCompatActivity {



        // Riferimenti alle view
       private ListView vListaStanze;
        // Adapter e data source
       private StanzeAdapter adapter;
        private DataSource dataSource;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Ottengo i riferimenti alle view
            vListaStanze = findViewById(R.id.listaStanze);

            // Ottengo un riferimento al datasource
            dataSource = DataSource.getInstance();

            // Creo l'adapter
            adapter = new StanzeAdapter(this, dataSource.getElencoStudenti(""));

            // Associo l'adapter alla listview
            vListaStanze.setAdapter(adapter);

            vListaStanze.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Stanza stanza = (Stanza)adapter.getItem(i);

                    Intent intent = new Intent(view.getContext(),DettaglioStanzaActivity.class);

                   intent.putExtra("stanza", stanza);

                    startActivity(intent);
                }
            });
        }
    }

