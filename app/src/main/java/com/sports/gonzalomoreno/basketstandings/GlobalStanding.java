package com.sports.gonzalomoreno.basketstandings;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GlobalStanding extends ActionBarActivity implements View.OnClickListener {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    TableLayout mainLinear;
    Team myTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_standing);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTeam = extras.getParcelable("team");
            Iterator<Player> playerIterator = myTeam.roster.iterator();
            mainLinear = (TableLayout) findViewById(R.id.mainTable);

            while (playerIterator.hasNext()){
                final Player aux = playerIterator.next();
                TableRow rowLinear = new TableRow(this);

                rowLinear.setOrientation(LinearLayout.HORIZONTAL);
                Button name = new Button(this);
                name.setTextColor(Color.WHITE);
                if (aux.getInitTeam()==1)
                    name.setBackgroundResource(R.drawable.player_actived);
                else
                    name.setBackgroundResource(R.drawable.player_disbale);
                TextView points = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView rebounds = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView fouls = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button more = new Button(this);
                more.setBackgroundResource(R.drawable.player_actived);
                more.setText("prueba");
                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create custom dialog object
                        final Dialog dialog = new Dialog(GlobalStanding.this);
                        // Include dialog.xml file
                        dialog.setContentView(R.layout.dialog);
                        // Set dialog title
                        dialog.setTitle("Ficha del Jugador");

                        // set values for custom dialog components - text, image and button
                        TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                        text.setText("Custom dialog Android example.");
                        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                        image.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);

                        dialog.show();

                        Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                        // if decline button is clicked, close the custom dialog
                        declineButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Close dialog
                                dialog.dismiss();
                            }
                        });

                    }
                });

                name.setText(aux.getName());
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (aux.getInitTeam()==1){
                            v.setBackgroundResource(R.drawable.player_disbale);
                            aux.setInitTeam(0);
                        }
                        else{
                            v.setBackgroundResource(R.drawable.player_actived);
                            aux.setInitTeam(1);
                        }



                    }
                });
                points.setText(Integer.toString(aux.getTotalPoints()));

                fouls.setText(Integer.toString(aux.getFouls()));
                //assistance.setText(Integer.toString(aux.getAssistance()));
                rebounds.setText(Integer.toString(aux.getTotalRebounds()));
                rowLinear.addView(name);
                rowLinear.addView(points);
                rowLinear.addView(rebounds);
                rowLinear.addView(fouls);
                rowLinear.addView(more);
                mainLinear.addView(rowLinear);

            }
        }



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

    @Override
    public void onClick(View v) {

    }
}
