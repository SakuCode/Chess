package com.example.chess;

import java.util.ArrayList;

public class Player {

    int id;
    String name;

    ArrayList<Piece> pieces = new ArrayList<>();


    public Player(int id, String name){
        this.name = name;
        this.id = id;
    }

    public String getPlayerName(){
        return name;
    }

    public void setPlayerName(String name){
        this.name = name;
    }

    public int getPlayerId(){
        return id;
    }

    public void setPlayerId(int id){
        this.id = id;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

}
