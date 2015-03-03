package com.sports.gonzalomoreno.basketstandings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GlobalStanding extends ActionBarActivity {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    TableLayout mainLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_standing);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLineup = extras.getParcelableArrayList("starting_lineup");
            Iterator<Player> playerIterator = startingLineup.iterator();
            mainLinear = (TableLayout) findViewById(R.id.mainTable);

            while (playerIterator.hasNext()){
                TableRow rowLinear = new TableRow(this);

                rowLinear.setOrientation(LinearLayout.HORIZONTAL);
                Button name = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button points = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button points2 = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button points3 = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button points1 = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                /*Button rebounds = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button fouls = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button assistance = (Button)getLayoutInflater().inflate(R.layout.standing_player, null);*/
                Player aux = playerIterator.next();
                name.setText(aux.getName());
                points.setText(Integer.toString(aux.getTotalPoints()));
                points2.setText(aux.getPercentage2());
                points3.setText(aux.getPercentage3());
                points1.setText(aux.getPercentage1());
                /*fouls.setText(Integer.toString(aux.getFouls()));
                assistance.setText(Integer.toString(aux.getAssistance()));
                rebounds.setText(Integer.toString(aux.getTotalRebounds()));*/
                rowLinear.addView(name);
                rowLinear.addView(points2);
                rowLinear.addView(points3);
                rowLinear.addView(points1);
                rowLinear.addView(points);
                mainLinear.addView(rowLinear);

            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_global_standing, menu);
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
