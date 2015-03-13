package com.sports.gonzalomoreno.basketstandings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemClickListener, DialogInterface.OnMultiChoiceClickListener {

    EditText typePlayer;
    ListView listPlayer;
    Button buttonAddPlayer, buttonSend;
    LinearLayout myLinear;
    List<String> arrayListPlayers = new ArrayList<String>();
    Team myTeam = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typePlayer = (EditText) findViewById(R.id.type_player);
        listPlayer = (ListView) findViewById(R.id.player_list);
        buttonAddPlayer = (Button) findViewById(R.id.button_add);
        myLinear = (LinearLayout) findViewById(R.id.miLinear);
        ArrayAdapter<String> adapterList2 = new ArrayAdapter<String>(this,R.layout.my_list,android.R.id.text1, arrayListPlayers);
        listPlayer.setAdapter(adapterList2);
        listPlayer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Toast.makeText(TestPreprationActivity.this, "" + listPlayer.getItemAtPosition(arg2),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), R.string.mensaje_jugador_introducido, Toast.LENGTH_LONG).show();

            }

        });
        listPlayer.getCheckedItemIds();
        buttonSend = (Button) findViewById(R.id.button_send);


        buttonAddPlayer.setOnClickListener(this);
        buttonSend.setOnClickListener(this);




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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add:
                if (!typePlayer.getText().toString().isEmpty()){
                    if (arrayListPlayers.indexOf(typePlayer.getText().toString())==-1){
                        arrayListPlayers.add(typePlayer.getText().toString());
                        typePlayer.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),R.string.error_repeated_player, Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.error_blank_player, Toast.LENGTH_SHORT).show();
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myLinear.getWindowToken(), 0);
                break;
            case R.id.button_send:
                SparseBooleanArray checked = listPlayer.getCheckedItemPositions();

                if (listPlayer.getCheckedItemCount()!=5){
                    Toast.makeText(getApplicationContext(),R.string.error_number_players, Toast.LENGTH_SHORT).show();
                }
                else{

                    String[] selItemArray = new String[arrayListPlayers.size()];
                    selItemArray = arrayListPlayers.toArray(selItemArray);
                    Intent i = new Intent(getBaseContext(),GameActivity.class);
                    i.putExtra("player_list",selItemArray);


                    for (int j = 0; j < listPlayer.getCount(); j++) {
                        Player newPlayer = new Player(listPlayer.getItemAtPosition(j).toString(), listPlayer.getItemAtPosition(j).toString());
                        if (checked.get(j)) {
                            newPlayer.setInitTeam(1);
                        }
                        myTeam.addPlayerToRoster(newPlayer);
                        //startingLineup.add(newPlayer);
                    }



                    i.putExtra("team",myTeam);
                    finish();
                    startActivity(i);
                }



            default:break;

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

    }
}
