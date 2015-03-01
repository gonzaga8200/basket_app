package com.sports.gonzalomoreno.basketstandings;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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


public class GameActivity extends ActionBarActivity {
    ArrayList<Player> startingLineup = new ArrayList<Player>();
    TextView playerSt1,playerSt2,playerSt3,playerSt4,playerSt5,
            scored2,scored3,scored1,scored2Failed,scored1Failed,scored3Failed,offRebounds,defRebounds,fouls,assistance;
    TextView [] players = new TextView[5];
    String draggable;

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, null, 0);

                draggable = ((TextView) view).getText().toString();
                //view.setVisibility(View.INVISIBLE);
                return false;

        }
    }

    private void set1points (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set1pointsScored();
                break;
            }
        }
    }
    private void set1pointsFailed (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set1pointsFailed();
                break;
            }
        }
    }
    private void set2points (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set2pointsScored();
                break;
            }
        }
    }
    private void set2pointsFailed (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set2pointsFailed();
                break;
            }
        }
    }
    private void set3points (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set3pointsScored();
                break;
            }
        }
    }
    private void set3pointsFailed (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.set3pointsFailed();
                break;
            }
        }
    }
    private void setOffRebounds (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.setOffRebound();
                break;
            }
        }
    }
    private void setDefRebounds (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.setDefRebound();
                break;
            }
        }
    }
    private void setFouls (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.setFouls();
                break;
            }
        }
    }
    private void setAssistance (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                aux.setAssistance();
                break;
            }
        }
    }
    private String getPoints (View v){
        Iterator<Player> playerIterator = startingLineup.iterator();
        String points = new String("");
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(((TextView) v).getText().toString())){
                points = Integer.toString(aux.getTotalPoints());
                return points;
            }
        }
        return "";
    }
    private String getPositionPlayerUpdated(View v){
        String position = "";
        for (int i=0; i < players.length; i++){
            if (v.getId() == players[i].getId()){
                position = Integer.toString(i+1);
                return position;
            }
        }
        return position;
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
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        break;
                    case DragEvent.ACTION_DROP:
                        String textStanding = getResources().getString(R.string.two_points);
                        switch (draggable){
                            case "2p V"  :
                                set2points(v);
                                break;
                            case "3p V":
                                set3points(v);
                                break;
                            case "1p V":
                                set1points(v);
                                break;
                            case "2p X":
                                set2pointsFailed(v);
                                break;
                            case "3p X":
                                set3pointsFailed(v);
                                break;
                            case "1p X":
                                set1pointsFailed(v);
                                break;
                            case "Off. Reb":
                                setOffRebounds(v);
                                break;
                            case "Def. Reb":
                                setDefRebounds(v);
                                break;
                            case "Fouls":
                                setFouls(v);
                                break;
                            case "Assis":
                                setAssistance(v);
                                break;
                            default:
                                break;
                        }

                        int ressourceId = getResources().getIdentifier("jugador"+getPositionPlayerUpdated(v)+"_std","id",getBaseContext().getPackageName());
                        TextView auxElement = (TextView) findViewById(ressourceId);
                        auxElement.setText(getPoints(v));


                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    default:
                        break;
                }
                return true;
            }
        }
        players[0] = (TextView) findViewById(R.id.jugador1);
        players[0].setOnDragListener(new MyDragListener());
        playerSt1 = (TextView) findViewById(R.id.jugador1_std);
        players[1] = (TextView) findViewById(R.id.jugador2);
        players[1].setOnDragListener(new MyDragListener());
        playerSt2 = (TextView) findViewById(R.id.jugador2_std);
        players[2] = (TextView) findViewById(R.id.jugador3);
        players[2].setOnDragListener(new MyDragListener());
        playerSt3 = (TextView) findViewById(R.id.jugador3_std);
        players[3] = (TextView) findViewById(R.id.jugador4);
        players[3].setOnDragListener(new MyDragListener());
        playerSt4 = (TextView) findViewById(R.id.jugador4_std);
        players[4] = (TextView) findViewById(R.id.jugador5);
        players[4].setOnDragListener(new MyDragListener());
        playerSt5 = (TextView) findViewById(R.id.jugador5_std);
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


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLineup = extras.getParcelableArrayList("starting_lineup");
            Iterator<Player> playerIterator = startingLineup.iterator();
            int j=0;
            while (playerIterator.hasNext()){
                Player aux = playerIterator.next();
                if (aux.getInitTeam()==1){
                    players[j].setText(aux.getNumber());
                    j++;
                }

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
