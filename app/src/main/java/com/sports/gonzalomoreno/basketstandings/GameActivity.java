package com.sports.gonzalomoreno.basketstandings;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class GameActivity extends ActionBarActivity {
    String[] players;
    ListView listMatch;
    TableLayout dataTable,dataTable2;
    public static int [][] standingsBoard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dataTable = (TableLayout) findViewById(R.id.tablaDatos);
        dataTable2 = (TableLayout) findViewById(R.id.tablaDatos2);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            players = extras.getStringArray("player_list");
            standingsBoard = new int[11][players.length];
            for (int i=0; i < players.length; i++){
                final int playerNumber = i;

                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TableRow rowStandings = new TableRow(this);
                TableRow.LayoutParams lpStandings = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                rowStandings.setLayoutParams(lpStandings);

                final TextView myPlayer = new TextView(this);
                myPlayer.setTextColor(Color.parseColor("#D8D8D8"));
                myPlayer.setText(players[i]);

                TextView myPlayerStanding = new TextView(this);
                myPlayerStanding.setTextColor(Color.parseColor("#D8D8D8"));
                myPlayerStanding.setText(players[i]);

                final Button percentagePoints = new Button(this);
                percentagePoints.setId(i);
                percentagePoints.setText("0/0");


                final Button twoPointsScore = new Button(this);
                twoPointsScore.setId(i);
                twoPointsScore.setText("2p V");
                twoPointsScore.setBackgroundColor(Color.parseColor("#FF5610"));
                twoPointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[0][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[0][playerNumber] + standingsBoard[1][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[0][playerNumber]);
                        percentagePoints.setText(totalScore + '/' + totalThrows);
                    }
                });

                final Button twoPointsFailed = new Button(this);
                twoPointsFailed.setId(i);
                twoPointsFailed.setText("2p X");
                twoPointsFailed.setBackgroundColor(Color.parseColor("#FF5610"));
                twoPointsFailed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[1][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[0][playerNumber] + standingsBoard[1][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[0][playerNumber]);
                        percentagePoints.setText(totalScore + '/' + totalThrows);
                    }
                });

                row.addView(myPlayer);
                row.addView(twoPointsScore);
                row.addView(twoPointsFailed);
                rowStandings.addView(myPlayerStanding);
                rowStandings.addView(percentagePoints);

                dataTable.addView(row,i);
                dataTable2.addView(rowStandings,i);
            }




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
