package com.example.ababo.progettoarduinouniversit;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.ababo.progettoarduinouniversit.datamodel.Stanza;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FirebaseClient {
    Context c;
    ListView listView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Stanze");
    ArrayList<Stanza> stanze = new ArrayList<>();
    StanzeAdapter stanzeAdapter;


    public FirebaseClient(Context c,ListView listView){
        this.c=c;
        this.listView=listView;


    }
    public void refreshdata()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Stanza s = new Stanza();
                s.setNome(dataSnapshot.getValue(String.class));
                stanze.add(s);

                String value = dataSnapshot.getValue(String.class);

                Log.d("2", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
