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
    TableLayout dataTable;
    public static int [][] standingsBoard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dataTable = (TableLayout) findViewById(R.id.tablaDatos);
        ArrayList<Button> playerInfo = new ArrayList<Button>();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            players = extras.getStringArray("player_list");
            standingsBoard = new int[11][players.length];
            for (int i=0; i < players.length; i++){
                final int playerNumber = i;

                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                final TextView myPlayer = new TextView(this);
                myPlayer.setTextColor(Color.parseColor("#D8D8D8"));
                myPlayer.setText(players[i]);

                TableLayout info2points = new TableLayout(this);

                TableRow rowTitle = new TableRow(this);
                TableRow.LayoutParams lpTitle = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                lpTitle.span = 2;
                rowTitle.setLayoutParams(lpTitle);

                TableRow checks2points = new TableRow(this);
                TableRow.LayoutParams lpCheck = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                checks2points.setLayoutParams(lpCheck);

                final Button percentagePoints = new Button(this);
                percentagePoints.setId(i);
                percentagePoints.setText("0/0");
                rowTitle.addView(percentagePoints);

                final Button twoPointsScore = new Button(this);
                twoPointsScore.setId(i);
                twoPointsScore.setText("V");
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
                twoPointsFailed.setText("X");
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

                checks2points.addView(twoPointsScore);
                checks2points.addView(twoPointsFailed);

                info2points.addView(rowTitle);
                info2points.addView(checks2points);

                row.addView(myPlayer);
                row.addView(info2points);






                dataTable.addView(row,i);
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
