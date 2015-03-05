package com.sports.gonzalomoreno.basketstandings;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Gonzaga on 04/03/2015.
 */
public class Team implements Parcelable {
    ArrayList<Player> roster = new ArrayList<Player>();

    public Team (){

    }
    public ArrayList<Player> getRoster (){
        return this.roster;
    }
    public void addPlayerToRoster (Player player){
        this.roster.add(player);
    }
    public ArrayList<Player> getStartingLineup(){
        ArrayList<Player> startingLineup =new ArrayList<Player>();
        Iterator<Player> playerIterator = this.roster.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getInitTeam()==1){
                startingLineup.add(aux);
            }
        }
        return startingLineup;
    }
    public Player getPlayer(String playerName){
        Iterator<Player> playerIterator = this.roster.iterator();
        Player response = null;
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(playerName)){
                response = aux;
                break;
            }
        }
        return response;
    }



    protected Team(Parcel in) {
        if (in.readByte() == 0x01) {
            roster = new ArrayList<Player>();
            in.readList(roster, Player.class.getClassLoader());
        } else {
            roster = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (roster == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(roster);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}