package com.sports.gonzalomoreno.basketstandings;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GameActivity extends ActionBarActivity {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    TextView playerSt1,playerSt2,playerSt3,playerSt4,playerSt5;
    TextView [] players = new TextView[5];

    public static int [][] standingsBoard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        players[0] = (TextView) findViewById(R.id.jugador1);
        players[1] = (TextView) findViewById(R.id.jugador2);
        players[2] = (TextView) findViewById(R.id.jugador3);
        players[3] = (TextView) findViewById(R.id.jugador4);
        players[4] = (TextView) findViewById(R.id.jugador5);

        playerSt1 = (TextView) findViewById(R.id.jugador1_std);
        playerSt2 = (TextView) findViewById(R.id.jugador2_std);
        playerSt3 = (TextView) findViewById(R.id.jugador3_std);
        playerSt4 = (TextView) findViewById(R.id.jugador4_std);
        playerSt5 = (TextView) findViewById(R.id.jugador5_std);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLineup = extras.getParcelableArrayList("starting_lineup");
            Iterator<Player> playerIterator = startingLineup.iterator();
            int j=0;
            while (playerIterator.hasNext()){
                Player aux = playerIterator.next();
                if (aux.getInitTeam()==1){
                    players[j].setText(aux.getNumber());
                }
            }



            /*for (int i=0; i < startingLineup.length; i++){
                final int playerNumber = i;

                twoPointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[0][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[0][playerNumber] + standingsBoard[1][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[0][playerNumber]);
                        percentage2Points.setText(totalScore + '/' + totalThrows);
                    }
                });




                onePointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[2][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[2][playerNumber] + standingsBoard[3][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[2][playerNumber]);
                        percentage1Points.setText(totalScore + '/' + totalThrows);
                    }
                });




                threePointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[4][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[4][playerNumber] + standingsBoard[5][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[4][playerNumber]);
                        percentage3Points.setText(totalScore + '/' + totalThrows);
                    }
                });





                final Button twoPointsFailed = new Button(this);
                twoPointsFailed.setId(i);
                twoPointsFailed.setTextSize(10);
                twoPointsFailed.setText("2p X");
                twoPointsFailed.setBackgroundResource(R.drawable.failed);
                twoPointsFailed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[1][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[0][playerNumber] + standingsBoard[1][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[0][playerNumber]);
                        percentage2Points.setText(totalScore + '/' + totalThrows);
                    }
                });



                final Button onePointsFailed = new Button(this);
                onePointsFailed.setId(i);
                onePointsFailed.setTextSize(10);
                onePointsFailed.setText("1p X");
                onePointsFailed.setBackgroundResource(R.drawable.failed);
                onePointsFailed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[3][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[2][playerNumber] + standingsBoard[3][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[2][playerNumber]);
                        percentage1Points.setText(totalScore + '/' + totalThrows);
                    }
                });



                final Button threePointsFailed = new Button(this);
                threePointsFailed.setId(i);
                threePointsFailed.setTextSize(10);
                threePointsFailed.setText("3p X");
                threePointsFailed.setBackgroundResource(R.drawable.failed);
                threePointsFailed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[5][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[4][playerNumber] + standingsBoard[5][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[4][playerNumber]);
                        percentage3Points.setText(totalScore + '/' + totalThrows);
                    }
                });*/


        }





    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
