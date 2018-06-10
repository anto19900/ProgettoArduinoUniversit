package com.example.ababo.progettoarduinouniversit.datamodel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DataSource {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Stanze");
    // Lista locale per simulare una ipotetica sorgente dati
    private Hashtable<String, Stanza> elencoStanze;

    // Unica instanza
    private static DataSource instance = null;

    // Costruttore privato
    private DataSource() {
        elencoStanze = new Hashtable<>();
       // popolaDataSource();
    }

    /**
     * Ottiene il riferimento alla sorgente dati
     * @return restituisce l'instanza corrente
     */
    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
    }

    /**
     * Aggiunge un studente
     * @param Stanza studente da aggiungere
     */
    public void addStanza(Stanza stanza) {

       // elencoStanze.put(stanza.getMatricola(), stanza);
        myRef.child(stanza.getMatricola()).setValue(stanza);

    }

    /**
     * Elimina uno studente
     * @param matricola matricola da eliminare
     */
    public void deleteStanza(String matricola) {
        //elencoStanze.remove(matricola);
        myRef.child(matricola).removeValue();
    }

    /**
     * Cerca uno studente partendo dalla matricola
     * @param matricola matricola da cercare
     * @return Studente trovoto (null in caso contrario)
     */
    public Stanza getStanza(String matricola) {
        return elencoStanze.get(matricola);
    }

    /**
     * Restituisce un elenco di studenti che hanno la matricola con il prefisso passato
     * @param prefissoMatricola prefisso da cercare
     * @return elenco studenti trovato
     */
    public List<Stanza> getElencoStanze(String prefissoMatricola) {

        ArrayList<Stanza> risultato = new ArrayList<Stanza>();

        // Itero tutti gli elementi per cercare quelli che soddisfano il requisito richiesto
        for (Map.Entry<String, Stanza> elemento: elencoStanze.entrySet()) {
            if (elemento.getKey().startsWith(prefissoMatricola))
                risultato.add(elemento.getValue());
        }
        return risultato;
    }

    // Popolo il data source con dati di prova
    private void popolaDataSource() {
        elencoStanze.put("1", new Stanza("1", "Room 1", 5));
        elencoStanze.put("2", new Stanza("2", "Room 2", 5));
        elencoStanze.put("3", new Stanza("3", "Room 3", 3));
        elencoStanze.put("4", new Stanza("4", "Room 4", 2));
    }
}
