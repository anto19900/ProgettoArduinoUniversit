package com.example.ababo.progettoarduinouniversit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Firebase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Stanze");
    private List<Stanza> elencoStanze;
    StanzeAdapter adapter;

    public void refreshdata(){
       myRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               Stanza sa = new Stanza();
               sa.setNome(dataSnapshot.child("nome").getValue().toString());
               sa.setMatricola(dataSnapshot.child("matricola").getValue().toString());
               sa.setDispositivi(dataSnapshot.child("dispositivi").getValue().hashCode());
               elencoStanze.add(sa);
               System.out.println(sa.getNome());
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
