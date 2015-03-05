package com.sports.gonzalomoreno.basketstandings;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Gonzaga on 04/03/2015.
 */
public class Team {
    ArrayList<Player> roster = new ArrayList<Player>();
    ArrayList<Player> startingLineup =new ArrayList<Player>();
    public Team (){

    }
    public ArrayList<Player> getRoster (){
        return this.roster;
    }
    public void addPlayerToRoster (Player player){
        this.roster.add(player);
    }
    public void setStartingLineup(){
        Iterator<Player> playerIterator = this.roster.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getInitTeam()==1){
                startingLineup.add(aux);
            }
        }
    }
    public ArrayList<Player> getStartingLineup(){
        return startingLineup;
    }
    public Player getPlayer(String playerName){
        Iterator<Player> playerIterator = this.roster.iterator();
        while (playerIterator.hasNext()){
            Player aux = playerIterator.next();
            if (aux.getName().equals(playerName)){
                return aux;
            }
        }
        return null;
    }
    public Team(Parcel in) {
        readFromParcel(in);
    }
    private void readFromParcel(Parcel in) {
        this.roster = (ArrayList<Player>) in.readSerializable();
        this.startingLineup = (ArrayList<Player>) in.readSerializable();


    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(roster);
        dest.writeSerializable(startingLineup);

    }



    public static final Parcelable.Creator<Team> CREATOR
            = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel in) {
            return new Team();
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
