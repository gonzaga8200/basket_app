package com.sports.gonzalomoreno.basketstandings;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

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
    public void set2pointsScored (){
        this.standings[0]++;
    }
    public void set2pointsFailed (){
        this.standings[1]++;
    }
    public void set1pointsScored (){
        this.standings[2]++;
    }
    public void set1pointsFailed (){
        this.standings[3]++;
    }
    public void set3pointsScored (){
        this.standings[4]++;
    }
    public void set3pointsFailed (){
        this.standings[5]++;
    }
    public void setOffRebound (){
        this.standings[6]++;
    }
    public void setDefRebound (){
        this.standings[7]++;
    }
    public void setAssistance (){
        this.standings[8]++;
    }
    public void setFouls (){
        this.standings[9]++;
    }
    public void setTurnovers (){
        this.standings[10]++;
    }
    public int getTotal2Throws (){
        return this.standings[0] + this.standings[1];
    }
    public int getTotal2Scored (){
        return this.standings[0];
    }
    public int getTotal2Failed (){
        return this.standings[1];
    }
    public int getTotal1Throws (){
        return this.standings[2] + this.standings[3];
    }
    public int getTotal1Scored (){
        return this.standings[2];
    }
    public int getTotal1Failed (){
        return this.standings[3];
    }
    public int getTotal3Throws (){
        return this.standings[4] + this.standings[5];
    }
    public int getTotal3Scored (){
        return this.standings[4];
    }
    public int getTotal3Failed (){
        return this.standings[4];
    }
    public int getTotalPoints (){
        return this.getTotal1Scored() + this.getTotal2Scored()*2 + this.getTotal3Scored()*3;
    }
    public int getOffRebounds (){
        return this.standings[6];
    }
    public int getDefRebounds (){
        return this.standings[7];
    }
    public int getTotalRebounds (){
        return this.standings[6] + this.standings[7];
    }
    public int getAssistance (){
        return this.standings[8];
    }
    public int getFouls (){
        return this.standings[9];
    }
    public int getTurnovers(){
        return this.standings[10];
    }
    public String getPercentage2 (){
        String scored = Integer.toString(this.getTotal2Scored());
        String total = Integer.toString(this.getTotal2Throws());
        return scored + '/' +total;
    }
    public String getPercentage3 (){
        String scored = Integer.toString(this.getTotal3Scored());
        String total = Integer.toString(this.getTotal3Throws());
        return scored + '/' +total;
    }
    public String getPercentage1 (){
        String scored = Integer.toString(this.getTotal1Scored());
        String total = Integer.toString(this.getTotal1Throws());
        return scored + '/' +total;
    }
    public String getPercentageSymbol2 (){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if (getTotal2Throws()!=0)
            return numberFormat.format(((double)getTotal2Scored()/(double)getTotal2Throws())*100) +'%';
        else return "0%";
    }
    public String getPercentageSymbol3 (){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if (getTotal3Throws()!=0)
            return numberFormat.format(((double)getTotal3Scored()/(double)getTotal3Throws())*100) +'%';
        else return "0%";
    }
    public String getPercentageSymbol1 (){
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if (getTotal1Throws()!=0)
            return numberFormat.format(((double)getTotal1Scored()/(double)getTotal1Throws())*100) +'%';
        else return "0%";
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
