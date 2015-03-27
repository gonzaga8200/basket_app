package com.sports.gonzalomoreno.basketteamstats;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TeamStandsActivity extends ActionBarActivity {

    Team myTeam;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_stands);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTeam = extras.getParcelable("team");
            TextView points = (TextView) findViewById(R.id.ptsJugador);
            points.setText(Integer.toString(myTeam.getTotalPoints()));


            TextView points2pPercentage = (TextView) findViewById(R.id.twoPointsJugador);
            points2pPercentage.setText(myTeam.getPercentage2());
            TextView points2pPercentageSymbol = (TextView) findViewById(R.id.twoPointsPercentageJugador);
            points2pPercentageSymbol.setText(myTeam.getPercentageSymbol2());

            TextView points3pPercentage = (TextView) findViewById(R.id.threePointsJugador);
            points3pPercentage.setText(myTeam.getPercentage3());
            TextView points3pPercentageSymbol = (TextView) findViewById(R.id.threePointsPercentageJugador);
            points3pPercentageSymbol.setText((myTeam.getPercentageSymbol3()));

            TextView points1pPercentage = (TextView) findViewById(R.id.onePointsJugador);
            points1pPercentage.setText(myTeam.getPercentage1());
            TextView points1pPercentageSymbol = (TextView) findViewById(R.id.onePointsPercentageJugador);
            points1pPercentageSymbol.setText(myTeam.getPercentageSymbol1());

            TextView offRebounds = (TextView) findViewById(R.id.offReboundsJugador);
            offRebounds.setText(Integer.toString(myTeam.getOffensiveRebounds()));

            TextView defRebounds = (TextView) findViewById(R.id.defReboundsJugador);
            defRebounds.setText(Integer.toString(myTeam.getDefensiveRebounds()));

            TextView totalRebounds = (TextView) findViewById(R.id.totalReboundsJugador);
            totalRebounds.setText(Integer.toString(myTeam.getTotalRebounds()));

            TextView assistance = (TextView) findViewById(R.id.assistanceJugador);
            assistance.setText(Integer.toString(myTeam.getTotalAssistance()));

            TextView fouls = (TextView) findViewById(R.id.foulsJugador);
            fouls.setText(Integer.toString(myTeam.getTotalFouls()));
        }
        closeButton = (Button) findViewById(R.id.declineButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),GameActivity.class);
                i.putExtra("team",myTeam);
                setResult(RESULT_OK, i);
                finish();
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(),GameActivity.class);
        i.putExtra("team",myTeam);
        setResult(RESULT_OK, i);
        finish();
        startActivity(i);


        super.onBackPressed();
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_stands, menu);
        return true;
    }*/

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
