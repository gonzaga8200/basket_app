package com.sports.gonzalomoreno.basketstandings;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    EditText playerList;
    ListView upPlayers;
    Button buttonAddPlayer, buttonSend;
    LinearLayout myLinear;
    List<String> supplierNames1 = new ArrayList<String>();
    ArrayList<Player> startingLineup = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerList = (EditText) findViewById(R.id.lista_jugadores);
        upPlayers = (ListView) findViewById(R.id.jugadores_aptos);
        buttonAddPlayer = (Button) findViewById(R.id.boton_add);
        myLinear = (LinearLayout) findViewById(R.id.miLinear);
        ArrayAdapter<String> adapterList2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,android.R.id.text1,supplierNames1);
        upPlayers.setAdapter(adapterList2);
        upPlayers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        upPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                   //Toast.makeText(TestPreprationActivity.this, "" + upPlayers.getItemAtPosition(arg2),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), R.string.mensaje_jugador_introducido, Toast.LENGTH_LONG).show();

            }

        });
        upPlayers.getCheckedItemIds();
        buttonSend = (Button) findViewById(R.id.boton_enviar);


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
            case R.id.boton_add:
                if (!playerList.getText().toString().isEmpty()){
                    supplierNames1.add(playerList.getText().toString());
                    //upPlayers.addView(playerList);

                    playerList.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.error_jugador_blanco, Toast.LENGTH_SHORT).show();
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myLinear.getWindowToken(), 0);
                break;
            case R.id.boton_enviar:
                SparseBooleanArray checked = upPlayers.getCheckedItemPositions();

                if (upPlayers.getCheckedItemCount()!=5){
                    Toast.makeText(getApplicationContext(),"Debes de seleccionar 5 jugadores para el quinteto inicial", Toast.LENGTH_SHORT).show();
                }
                else{

                    String[] selItemArray = new String[supplierNames1.size()];
                    selItemArray = supplierNames1.toArray(selItemArray);
                    Intent i = new Intent(getBaseContext(),GameActivity.class);
                    i.putExtra("player_list",selItemArray);


                    for (int j = 0; j < upPlayers.getCount(); j++) {
                        Player newPlayer = new Player(upPlayers.getItemAtPosition(j).toString(),upPlayers.getItemAtPosition(j).toString());
                        if (checked.get(j)) {
                            newPlayer.setInitTeam(1);
                        }
                        startingLineup.add(newPlayer);
                    }

                    i.putParcelableArrayListExtra("starting_lineup",startingLineup);
                    i.putExtra("starting_lineup",startingLineup);
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
