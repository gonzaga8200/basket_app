package com.sports.gonzalomoreno.basketstandings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    EditText playerList;
    ListView upPlayers;
    Button buttonAddPlayer;
    private List<String> supplierNames1 = new ArrayList<String>();
    String [] valores = new String[]{"poma","pera","taronja","llimona","graim","meló","sindria","fresa","cogombre", "enciam","cirera",
            "plàtan","brécol","julibert","tomàquet","all","carabassa","pebrot","orenga","romaní","castanya"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerList = (EditText) findViewById(R.id.lista_jugadores);
        upPlayers = (ListView) findViewById(R.id.jugadores_aptos);
        buttonAddPlayer = (Button) findViewById(R.id.boton_add);
        supplierNames1.add("asdasd");
        ArrayAdapter<String> adapterList2 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,supplierNames1);
        upPlayers.setAdapter(adapterList2);


        buttonAddPlayer.setOnClickListener(null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
