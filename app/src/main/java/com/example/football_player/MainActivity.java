package com.example.football_player;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDB;
    private FloatingActionButton fabtambah;
    private RecyclerView rvPlayer;
    private AdapterFootBallPlayer adPlayer;
    private ArrayList<String> arrID,arrNama,arrNomor,arrKlub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlayer = findViewById(R.id.rv_player);

        myDB = new MyDatabaseHelper(MainActivity.this);
        fabtambah = findViewById(R.id.fab_tambah);
        fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TambahActivity.class));
            }
        });
    }
    protected void onResume(){
        super.onResume();
        TampilPlayer();
    }

    private void SQLiteToArraylist(){
        Cursor varCusror = myDB.bacaDataPlayer();
        if (varCusror.getCount() == 0){
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (varCusror.moveToNext()){
                arrID.add(varCusror.getString(0));
                arrNama.add(varCusror.getString(1));
                arrNomor.add(varCusror.getString(2));
                arrKlub.add(varCusror.getString(3));
            }
        }
    }

    private  void TampilPlayer(){
        arrID = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrKlub = new ArrayList<>();

        SQLiteToArraylist();

        adPlayer = new AdapterFootBallPlayer(MainActivity.this, arrID,arrNama,arrNomor,arrKlub);

        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPlayer.setAdapter(adPlayer);
    }
}
