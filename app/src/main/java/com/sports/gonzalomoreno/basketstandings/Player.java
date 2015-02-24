package com.sports.gonzalomoreno.basketstandings;

/**
 * Created by gonzalo.moreno on 24/02/2015.
 */
public class Player {
   String number;
   String name;
   int standings [] = new int[11] ;
   Boolean initTeam = false;

   public Player (String numberPlayer, String namePlayer){
       number = numberPlayer;
       name = namePlayer;
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
    public void setInitTeam (Boolean initStarting){
        initTeam = initStarting;
    }
    public Boolean getInitTeam (){
        return initTeam;
    }
}
