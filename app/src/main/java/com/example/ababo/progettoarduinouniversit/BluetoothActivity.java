package com.example.ababo.progettoarduinouniversit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;

public class BluetoothActivity extends AppCompatActivity {

    private ListView vListViewBluetooth;
    private Button vButtonBluetooth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        vListViewBluetooth = findViewById(R.id.listBluetooth);
        vButtonBluetooth = findViewById(R.id.bBluetooth);
        Toolbar toolbar8 = findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar8);
    }

}
