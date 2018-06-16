package com.example.ababo.progettoarduinouniversit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraficiActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Temperatura");
    Integer vettore [] = new Integer[5];
    private LineGraphSeries<DataPoint> series;

    private int lastX = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafici);
        GraphView graph = (GraphView) findViewById(R.id.grafico);
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);

        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(40);
        viewport.setScrollable(true);
     myRef.addChildEventListener(new ChildEventListener() {
         int i = 0;
         @Override

         public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


             if (dataSnapshot.getValue().toString() != null) {
                 addEntry( Integer.parseInt(dataSnapshot.getValue().toString()));

             }

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

    // add random data to graph
    private void addEntry(int i) {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        series.appendData(new DataPoint(lastX++, i), true, 10);
    }

}