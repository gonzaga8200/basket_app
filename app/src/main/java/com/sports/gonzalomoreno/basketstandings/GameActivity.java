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


public class GameActivity extends ActionBarActivity {
    String[] players;
    ListView listMatch;
    TableLayout dataTable,dataTable2;
    public static int [][] standingsBoard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            players = extras.getStringArray("player_list");
            standingsBoard = new int[11][players.length];
            for (int i=0; i < players.length; i++){
                final int playerNumber = i;









                final Button percentage2Points = new Button(this);
                percentage2Points.setId(i);
                percentage2Points.setText("0/0");
                percentage2Points.setWidth(10);

                final Button percentage1Points = new Button(this);
                percentage1Points.setId(i);
                percentage1Points.setText("0/0");

                final Button percentage3Points = new Button(this);
                percentage3Points.setId(i);
                percentage3Points.setText("0/0");


                final Button twoPointsScore = new Button(this);
                twoPointsScore.setBackgroundResource(R.drawable.scored);
                twoPointsScore.setTextColor(Color.WHITE);
                twoPointsScore.setId(i);
                twoPointsScore.setTextSize(10);
                twoPointsScore.setText("2p V");

                final Button onePointsScore = new Button(this);
                onePointsScore.setBackgroundResource(R.drawable.scored);
                onePointsScore.setTextColor(Color.WHITE);
                onePointsScore.setId(i);
                onePointsScore.setTextSize(10);
                onePointsScore.setText("1p V");

                final Button threePointsScore = new Button(this);
                threePointsScore.setBackgroundResource(R.drawable.scored);
                threePointsScore.setTextColor(Color.WHITE);
                threePointsScore.setTextSize(10);
                threePointsScore.setId(i);
                threePointsScore.setText("3p V");

                /** TWO POINTS SCORE **/


                twoPointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[0][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[0][playerNumber] + standingsBoard[1][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[0][playerNumber]);
                        percentage2Points.setText(totalScore + '/' + totalThrows);
                    }
                });

                /** ONE POINTS SCORE **/


                onePointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[2][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[2][playerNumber] + standingsBoard[3][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[2][playerNumber]);
                        percentage1Points.setText(totalScore + '/' + totalThrows);
                    }
                });

                /** THREE POINTS SCORE **/


                threePointsScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        standingsBoard[4][playerNumber] += 1;
                        String totalThrows = Integer.toString(standingsBoard[4][playerNumber] + standingsBoard[5][playerNumber]);
                        String totalScore =  Integer.toString(standingsBoard[4][playerNumber]);
                        percentage3Points.setText(totalScore + '/' + totalThrows);
                    }
                });

                /******* FAILED *******/

                /** TWO POINTS **/

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

                /** ONE POINTS **/

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

                /** THREE POINTS **/

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
                });




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
