package com.sports.gonzalomoreno.basketstandings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GlobalStanding extends ActionBarActivity {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    LinearLayout mainLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_standing);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLineup = extras.getParcelableArrayList("starting_lineup");
            Iterator<Player> playerIterator = startingLineup.iterator();
            mainLinear = (LinearLayout) findViewById(R.id.parentLinear);

            while (playerIterator.hasNext()){
                LinearLayout rowLinear = new LinearLayout(this);

                rowLinear.setOrientation(LinearLayout.HORIZONTAL);
                TextView name = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView points = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView rebounds = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView fouls = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView assistance = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                Player aux = playerIterator.next();
                name.setText(aux.getName());
                points.setText(Integer.toString(aux.getTotalPoints()));
                fouls.setText(Integer.toString(aux.getFouls()));
                assistance.setText(Integer.toString(aux.getAssistance()));
                rebounds.setText(Integer.toString(aux.getTotalRebounds()));
                rowLinear.addView(name);
                rowLinear.addView(points);
                rowLinear.addView(rebounds);
                rowLinear.addView(fouls);
                rowLinear.addView(assistance);
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
