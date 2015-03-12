package com.sports.gonzalomoreno.basketstandings;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GameActivity extends ActionBarActivity  {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    Team myTeam;
    TextView scored2,scored3,scored1,scored2Failed,scored1Failed,scored3Failed,offRebounds,defRebounds,fouls,assistance;
    TextView [] players = new TextView[5];
    TextView [] playersScorer = new TextView[5];
    int draggable;
    Button buttonStanding, buttonTeamStanding;



    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, null, 0);
                //view.setBackground(getResources().getDrawable(R.drawable.scored_selected));

                //draggable = ((TextView) view).getText().toString();
                draggable = view.getId();
                //view.setVisibility(View.INVISIBLE);
                return false;

        }

    }
    private boolean setDraggableAction (Player player, int draggable){
        if (draggable == scored2.getId()){
            player.set2pointsScored();
            return true;
        }
        if (draggable == scored2Failed.getId()){
            player.set2pointsFailed();
            return true;
        }
        if (draggable == scored3.getId()){
            player.set3pointsScored();
            return true;
        }
        if (draggable == scored3Failed.getId()){
            player.set3pointsFailed();
            return true;
        }
        if (draggable == scored1.getId()){
            player.set1pointsScored();
            return true;
        }
        if (draggable == scored1Failed.getId()){
            player.set1pointsFailed();
            return true;
        }
        if (draggable == offRebounds.getId()){
            player.setOffRebound();
            return true;
        }
        if (draggable == defRebounds.getId()){
            player.setDefRebound();
            return true;
        }
        if (draggable == fouls.getId()){
            player.setFouls();
            return true;
        }
        if (draggable == assistance.getId()){
            player.setAssistance();
            return true;
        }
        return false;

    }

       @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        class MyDragListener implements View.OnDragListener {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackground(getResources().getDrawable(R.drawable.camiseta_on));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackground(getResources().getDrawable(R.drawable.camiseta));
                        break;
                    case DragEvent.ACTION_DROP:
                        v.setBackground(getResources().getDrawable(R.drawable.camiseta));
                        Player player = myTeam.getPlayer(((TextView) v).getText().toString());


                        setDraggableAction(player,draggable);


                        int resourceId = getResources().getIdentifier("jugador"+myTeam.getStartingLineup().indexOf(player)+"_std","id",getBaseContext().getPackageName());
                        TextView scorerPlayer = (TextView) findViewById(resourceId);
                        scorerPlayer.setText(Integer.toString(player.getTotalPoints()));


                        break;
                    case DragEvent.ACTION_DRAG_ENDED:

                        break;
                    default:
                        break;
                }
                return true;
            }
        }

        scored1 = (TextView) findViewById(R.id.scored_one);
        scored1.setOnTouchListener(new MyTouchListener());
        scored2 = (TextView) findViewById(R.id.scored_two);
        scored2.setOnTouchListener(new MyTouchListener());
        scored3 = (TextView) findViewById(R.id.scored_three);
        scored3.setOnTouchListener(new MyTouchListener());
        scored2Failed = (TextView) findViewById(R.id.scored_two_failed);
        scored2Failed.setOnTouchListener(new MyTouchListener());
        scored3Failed = (TextView) findViewById(R.id.scored_three_failed);
        scored3Failed.setOnTouchListener(new MyTouchListener());
        scored1Failed = (TextView) findViewById(R.id.scored_one_failed);
        scored1Failed.setOnTouchListener(new MyTouchListener());
        offRebounds = (TextView) findViewById(R.id.rebounds_off);
        offRebounds.setOnTouchListener(new MyTouchListener());
        defRebounds = (TextView) findViewById(R.id.rebounds_def);
        defRebounds.setOnTouchListener(new MyTouchListener());
        fouls = (TextView) findViewById(R.id.fouls);
        fouls.setOnTouchListener(new MyTouchListener());
        assistance = (TextView) findViewById(R.id.assistance);
        assistance.setOnTouchListener(new MyTouchListener());
        buttonStanding = (Button) findViewById(R.id.buttonStanding);
        buttonStanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),GlobalStanding.class);
                i.putExtra("team",myTeam);
                finish();
                startActivity(i);
            }
        });
        buttonTeamStanding = (Button) findViewById(R.id.buttonTeamStanding);
        buttonTeamStanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),TeamStandsActivity.class);
                i.putExtra("team",myTeam);
                finish();
                startActivity(i);
            }
        });




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTeam = extras.getParcelable("team");
            Iterator<Player> playerIterator = myTeam.getStartingLineup().iterator();
            int j=0;
            while (playerIterator.hasNext()){
                Player aux = playerIterator.next();
                int resourcePlayer = getResources().getIdentifier("jugador"+j,"id",getBaseContext().getPackageName());
                int resourceScorer = getResources().getIdentifier("jugador"+j+"_std","id",getBaseContext().getPackageName());
                players[j] = (TextView) findViewById(resourcePlayer);
                players[j].setText(aux.getName());
                players[j].setOnDragListener(new MyDragListener());
                playersScorer[j] = (TextView) findViewById(resourceScorer);
                playersScorer[j].setText(Integer.toString(aux.getTotalPoints()));
                j++;

            }
        }
    }

    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(),MainActivity.class);
        finish();
        startActivity(i);


        super.onBackPressed();
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
