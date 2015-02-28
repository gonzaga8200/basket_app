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
    TextView playerSt1,playerSt2,playerSt3,playerSt4,playerSt5,scored2;
    TextView [] players = new TextView[5];

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, null, 0);
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
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //players[0].setText("pirikichi");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        break;
                    case DragEvent.ACTION_DROP:

                        Iterator<Player> playerIterator = startingLineup.iterator();
                        String points = new String("");
                        while (playerIterator.hasNext()){
                            Player aux = playerIterator.next();
                            if (aux.getName().equals(((TextView) v).getText().toString())){
                                aux.set2pointsScored();
                                points = Integer.toString(aux.getTotalPoints());
                                break;
                            }
                        }
                        //((TextView) v).setText(points);
                        /*int ressourceId = getResources().getIdentifier(""+i,"id",this.getContext().getPackageName());
                        Button button = (Button) findViewById(ressourceId);
                        TextView auxElement = (TextView) findViewById(R.id.aux.getName()+'std')*/
                        //int ressourceId = getResources().getIdentifier("jugador"+textSplitted[1]+"_std","id",getBaseContext().getPackageName());

                        int entero = v.getId();
                        String position = "";
                        for (int i=0; i < players.length; i++){
                            if (entero == players[i].getId()){
                                position = Integer.toString(i+1);
                                break;
                            }
                        }
                        int ressourceId = getResources().getIdentifier("jugador"+position+"_std","id",getBaseContext().getPackageName());
                        TextView auxElement = (TextView) findViewById(ressourceId);
                        auxElement.setText(points);
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
        scored2 = (TextView) findViewById(R.id.scored_two);
        scored2.setOnTouchListener(new MyTouchListener());


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startingLineup = extras.getParcelableArrayList("starting_lineup");
            Iterator<Player> playerIterator = startingLineup.iterator();
            int j=0;
            while (playerIterator.hasNext()){
                Player aux = playerIterator.next();
                if (aux.getInitTeam()==1){
                    players[j].setText(aux.getNumber());
                }
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
