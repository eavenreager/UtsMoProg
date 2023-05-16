package com.example.gamesuitjawa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txscore;
    private ImageView player,com;
    private ImageButton btnbatu,btngunting,btnkertas;
    private int scorePlayer=0, scoreKomputer =0;
    private MediaPlayer playersmenang,playerskalah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txscore =findViewById(R.id.txscore);
        com =findViewById(R.id.com);
        player =findViewById(R.id.player);
        btnbatu =findViewById(R.id.btnbatu);
        btngunting =findViewById(R.id.btngunting);
        btnkertas=findViewById(R.id.btnkertas);

        playersmenang = MediaPlayer.create(this, R.raw.menang);
        playerskalah = MediaPlayer.create(this, R.raw.kalah);

        btnbatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setImageResource(R.drawable.batu_bawah);
              String message = gamestart( "batu");
                Toast.makeText(MainActivity.this,"Kamu Memilih Batu "+message,Toast.
                        LENGTH_SHORT).show();
                txscore.setText("Score Player : "+Integer.toString(scorePlayer)+" Score Komputer : "+
                        Integer.toString(scoreKomputer));
            }
        });
        btngunting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setImageResource(R.drawable.gunting_bawah);
                String message = gamestart( "gunting");
                Toast.makeText(MainActivity.this,"Kamu Memilih Gunting "+message,Toast.
                        LENGTH_SHORT).show();
                txscore.setText("Score Player : "+Integer.toString(scorePlayer)+" Score Komputer : "+
                        Integer.toString(scoreKomputer));
            }
        });
        btnkertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setImageResource(R.drawable.kertas_bawah);
               String message = gamestart("kertas");
                Toast.makeText(MainActivity.this,"Kamu Memilih Kertas "+message,Toast.
                        LENGTH_SHORT).show();
                txscore.setText("Score Player : "+Integer.toString(scorePlayer)+" Score Komputer : "+
                        Integer.toString(scoreKomputer));
            }
        });
    }
    protected void onPause() {
        super.onPause();
        playersmenang.release();
        playerskalah.release();
    }

    private String gamestart( String player) {
        String komputer = "";
        Random angka_random= new Random();

        int angka_komputer = angka_random.nextInt(3)+1;

        if (angka_komputer == 1){
            komputer="batu";
        } else if (angka_komputer == 2) {
            komputer="gunting";
        } else if (angka_komputer == 3) {
            komputer ="kertas";
        }
        if (komputer == "batu"){
            com.setImageResource(R.drawable.batu_atas);
        } else if (komputer == "gunting") {
            com.setImageResource(R.drawable.gunting_atas);
        } else if (komputer == "kertas") {
            com.setImageResource(R.drawable.kertas_atas);
        }
//aturan main
        if (komputer == player){
            return "Seri";
        }else if(player == "batu" && komputer == "gunting"){
            scorePlayer++;
            playersmenang.start();
            return "Kamu Menang";

        } else if (player == "batu" && komputer == "kertas") {
            scoreKomputer++;
            playerskalah.start();
            return "Kamu Kalah";

        } else if (player == "gunting" && komputer == "kertas") {
            scorePlayer++;
            playersmenang.start();
            return "Kamu Menang";

        } else if (player == "gunting" && komputer == "batu") {
            scoreKomputer++;
            playerskalah.start();
            return "Kamu Kalah";

        } else if (player == "kertas" && komputer == "batu") {
            scorePlayer++;
            playersmenang.start();
            return "Kamu Menang";

        }else {
            scoreKomputer++;
            playerskalah.start();
            return "Kamu Kalah";

        }

    }
}