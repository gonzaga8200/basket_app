package com.sports.gonzalomoreno.basketstandings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.Iterator;


public class PlayerStandsActivity extends ActionBarActivity implements View.OnClickListener {
    TableLayout mainLinear;
    Button closeButton;

    Team myTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stands);

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
                name.setTextSize(12);
                if (aux.getInitTeam()==1)
                    name.setBackgroundResource(R.drawable.player_actived);
                else
                    name.setBackgroundResource(R.drawable.player_disbale);
                final TextView points = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView rebounds = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                TextView fouls = (TextView)getLayoutInflater().inflate(R.layout.standing_player, null);
                Button more = new Button(this);
                more.setBackgroundResource(R.drawable.more);
                more.setText("+");
                more.setTextSize(12);
                more.setTextColor(Color.WHITE);

                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create custom dialog object
                        final Dialog dialog = new Dialog(PlayerStandsActivity.this);
                        // Include dialog.xml file
                        dialog.setContentView(R.layout.dialog);
                        // Set dialog title
                        dialog.setTitle(aux.getName());

                        // set values for custom dialog components - text, image and button
                        TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                        image.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);

                        dialog.show();

                        TextView points = (TextView) dialog.findViewById(R.id.ptsJugador);
                        points.setText(Integer.toString(aux.getTotalPoints()));


                        TextView points2pPercentage = (TextView) dialog.findViewById(R.id.twoPointsJugador);
                        points2pPercentage.setText(aux.getPercentage2());
                        TextView points2pPercentageSymbol = (TextView) dialog.findViewById(R.id.twoPointsPercentageJugador);
                        points2pPercentageSymbol.setText(aux.getPercentageSymbol2());

                        TextView points3pPercentage = (TextView) dialog.findViewById(R.id.threePointsJugador);
                        points3pPercentage.setText(aux.getPercentage3());
                        TextView points3pPercentageSymbol = (TextView) dialog.findViewById(R.id.threePointsPercentageJugador);
                        points3pPercentageSymbol.setText(aux.getPercentageSymbol3());

                        TextView points1pPercentage = (TextView) dialog.findViewById(R.id.onePointsJugador);
                        points1pPercentage.setText(aux.getPercentage1());
                        TextView points1pPercentageSymbol = (TextView) dialog.findViewById(R.id.onePointsPercentageJugador);
                        points1pPercentageSymbol.setText(aux.getPercentageSymbol1());

                        TextView offRebounds = (TextView) dialog.findViewById(R.id.offReboundsJugador);
                        offRebounds.setText(Integer.toString(aux.getOffRebounds()));

                        TextView defRebounds = (TextView) dialog.findViewById(R.id.defReboundsJugador);
                        defRebounds.setText(Integer.toString(aux.getDefRebounds()));

                        TextView totalRebounds = (TextView) dialog.findViewById(R.id.totalReboundsJugador);
                        totalRebounds.setText(Integer.toString(aux.getTotalRebounds()));

                        TextView assistance = (TextView) dialog.findViewById(R.id.assistanceJugador);
                        assistance.setText(Integer.toString(aux.getAssistance()));

                        TextView fouls = (TextView) dialog.findViewById(R.id.foulsJugador);
                        fouls.setText(Integer.toString(aux.getFouls()));


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
        closeButton = (Button) findViewById(R.id.buttonClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTeam.isValidStartingLineUp()){
                    Intent i = new Intent(getBaseContext(),GameActivity.class);
                    i.putExtra("team",myTeam);
                    setResult(RESULT_OK, i);
                    finish();
                    startActivity(i);
                }
                else{
                    errorNumberPlayers();
                }
            }
        });



    }
    private void errorNumberPlayers() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                PlayerStandsActivity.this);



        alertDialog.setNegativeButton("Cerrar", null);

        alertDialog.setMessage(R.string.error_number_players);
        alertDialog.setTitle("Error Jugadores");
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (myTeam.isValidStartingLineUp()){
            Intent i = new Intent(getBaseContext(),GameActivity.class);
            i.putExtra("team",myTeam);
            setResult(RESULT_OK, i);
            finish();
            startActivity(i);
        }
        else{
            this.errorNumberPlayers();
        }
        //super.onBackPressed();
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_stands, menu);
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

    @Override
    public void onClick(View v) {

    }
}
