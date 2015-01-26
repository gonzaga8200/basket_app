package com.sports.gonzalomoreno.basketstandings;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, Parcelable{

    EditText playerList;
    ListView upPlayers;
    Button buttonAddPlayer, buttonSend;
    LinearLayout myLinear;
    List<String> supplierNames1 = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerList = (EditText) findViewById(R.id.lista_jugadores);
        upPlayers = (ListView) findViewById(R.id.jugadores_aptos);
        buttonAddPlayer = (Button) findViewById(R.id.boton_add);
        myLinear = (LinearLayout) findViewById(R.id.miLinear);
        ArrayAdapter<String> adapterList2 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,supplierNames1);
        upPlayers.setAdapter(adapterList2);
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
                }
                else{
                    Toast.makeText(getApplicationContext(), " El nombre del jugador no puede estar en blanco ", Toast.LENGTH_LONG).show();
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myLinear.getWindowToken(), 0);
            case R.id.boton_enviar:
                
            default:break;

        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
