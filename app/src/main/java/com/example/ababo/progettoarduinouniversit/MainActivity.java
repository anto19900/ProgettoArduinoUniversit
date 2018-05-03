package com.example.ababo.progettoarduinouniversit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ababo.progettoarduinouniversit.datamodel.DataSource;

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
        }
    }

