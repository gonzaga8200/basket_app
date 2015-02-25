package com.sports.gonzalomoreno.basketstandings;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gonzalo.moreno on 24/02/2015.
 */
public class Player implements Parcelable {
   String number;
   String name;
   int standings [] = new int[11] ;
   int initTeam = 0;

   public Player (String numberPlayer, String namePlayer){
       number = numberPlayer;
       name = namePlayer;
   }

    public Player(Parcel in) {
        number = new String();
        name = new String();
        readFromParcel(in);
    }
    public void setNumber (String numberPlayer){
        number = numberPlayer;
    }
    public void setName (String namePlayer){
        name = namePlayer;
    }
    public String getNumber (){
        return number;
    }
    public String getName (){
        return name;
    }
    public void setInitTeam (int initStarting){
        initTeam = initStarting;
    }
    public int getInitTeam (){
        return initTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        this.number = in.readString();
        this.name = in.readString();
        in.readIntArray(standings);
        this.initTeam = in.readInt();

    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(name);
        dest.writeIntArray(standings);
        dest.writeInt(initTeam);

    }


    public static final Parcelable.Creator<Player> CREATOR
            = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
