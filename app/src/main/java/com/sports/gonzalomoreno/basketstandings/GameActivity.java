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
    String draggable;
    Button buttonStanding;



    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, null, 0);
                //view.setBackground(getResources().getDrawable(R.drawable.scored_selected));

                draggable = ((TextView) view).getText().toString();
                //view.setVisibility(View.INVISIBLE);
                return false;

        }

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
                        v.setBackground(getResources().getDrawable(R.drawable.player_selected));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackground(getResources().getDrawable(R.drawable.players));
                        break;
                    case DragEvent.ACTION_DROP:
                        v.setBackground(getResources().getDrawable(R.drawable.players));
                        Player player = myTeam.getPlayer(((TextView) v).getText().toString());
                        switch (draggable){
                            case "2p V"  :
                                //set2points(v);
                                player.set2pointsScored();
                                break;
                            case "3p V":
                                player.set3pointsScored();
                                break;
                            case "1p V":
                                player.set1pointsScored();
                                break;
                            case "2p X":
                                player.set2pointsFailed();
                                break;
                            case "3p X":
                                player.set3pointsFailed();
                                break;
                            case "1p X":
                                player.set1pointsFailed();
                                break;
                            case "Off. Reb":
                                player.setOffRebound();
                                break;
                            case "Def. Reb":
                                player.setDefRebound();
                                break;
                            case "Fouls":
                                player.setFouls();
                                break;
                            case "Assis":
                                player.setAssistance();
                                break;
                            default:
                                break;
                        }

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
                startActivity(i);
            }
        });



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTeam = extras.getParcelable("team");
            Iterator<Player> playerIterator = myTeam.getStartingLineup().iterator();
            int j=0;
            while (playerIterator.hasNext()){
                int k = j+1;
                Player aux = playerIterator.next();
                int resourcePlayer = getResources().getIdentifier("jugador"+k,"id",getBaseContext().getPackageName());
                int resourceScorer = getResources().getIdentifier("jugador"+k+"_std","id",getBaseContext().getPackageName());
                players[j] = (TextView) findViewById(resourcePlayer);
                players[j].setText(aux.getName());
                players[j].setOnDragListener(new MyDragListener());
                playersScorer[j] = (TextView) findViewById(resourceScorer);
                j++;

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
