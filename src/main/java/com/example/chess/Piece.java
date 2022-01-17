package com.example.chess;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Piece{

    /*
    * Piece ID:
    * (From left to right on board)
    * Pawn: odd numbers (1,3,5,7,9,11,13,15)
    * Rook: 2,16
    * Knight: 4,14
    * Bishop: 6,12
    * Queen: 8
    * King: 10
    * */

    String name;
    public int id;
    ImageView image;

    public int moves;

    int col;
    int row;

    //Board is 8x8
    int boardLength = 8;

    /**
     * Piece class constructor where every piece is formed
     * @param name Name of the players piece
     * @param id Gives every piece an individual id
     * @param image Image of the piece
     * @param moves The amount of moves the piece has made
     * @param col The grid column where the piece is
     * @param row The grid row where the piece is
     */
    public Piece(String name, int id, ImageView image,int moves, int col, int row) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.col = col;
        this.row = row;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Method that excepts only the legal moves for a pawn
     * @param player The player who owns this piece
     * @param piece The piece of this class(pawn)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @param enemy The other player
     * @return true if it is a legal move, false if piece can not go there
     */
    public boolean pawnMovement(Player player, Piece piece, int col, int row,Player enemy) {
        piece = this;

        //White Player
        if(player.getPlayerId() == 1){
            if(row == piece.row-1 && col == piece.col) {
                for(Piece enemyA : enemy.getPieces()) {
                    if (row == enemyA.row && col == enemyA.col) {
                        return false;
                    }
                }
                setMoves(moves + 1);
                return true;
            }
            else if(moves == 0){
                if (row == piece.row-2 && col == piece.col) {
                    setMoves(moves + 1);
                    return true;
                }
            }

        //Black Player
        }else {
            if (row == piece.row+1 && col == piece.col) {
                for(Piece enemyA : enemy.getPieces()) {
                    if (row == enemyA.row && col == enemyA.col) {
                        return false;
                    }
                }
                setMoves(moves + 1);
                return true;
            }
            else if(moves == 0){
                if (row == piece.row+2 && col == piece.col) {
                    setMoves(moves + 1);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that excepts only the legal moves for a knight
     * @param piece The piece of this class(knight)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @return true if it is a legal move, false if piece can not go there
     */
    public boolean knightMovement(Piece piece, int col, int row) {
        piece = this;
        if ( (piece.col+2 == col && piece.row+1 == row ||  piece.row-1 == row)
                || (piece.col-2 == col && piece.row+1 == row ||  piece.row-1 == row)
                || (piece.row+2 == row && piece.col+1 == col ||  piece.col-1 == col)
                || (piece.row-2 == row && piece.col+1 == col ||  piece.col-1 == col)) {
                    return true;
        }
        return false;
    }

    /**
     * Method that excepts only the legal moves for a rook
     * @param piece The piece of this class(rook)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @return true if it is a legal move, false if piece can not go there
     */

    public boolean rookMovement(Piece piece, int col, int row) {
        piece = this;

            if(piece.col == col || piece.row == row ){
                return true;
            }
        return false;
    }

    /**
     * Method that excepts only the legal moves for a bishop
     * @param piece The piece of this class(bishop)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @return true if it is a legal move, false if piece can not go there
     */
    public boolean bishopMovement(Piece piece,int col, int row) {
        piece = this;
        for(int i = 0;i<boardLength;i++){
            if(piece.col+i == col && piece.row+i == row
                || piece.col-i == col && piece.row-i == row
                || piece.col-i == col && piece.row+i == row
                || piece.col+i == col && piece.row-i == row){
                    return true;
            }
        }
        return false;
    }

    /**
     * Method that excepts only the legal moves for a queen
     * @param piece The piece of this class(queen)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @return true if it is a legal move, false if piece can not go there
     */
    public boolean queenMovement(Piece piece, int col, int row) {
        if(bishopMovement(piece,col,row) || rookMovement(piece,col,row)){
            return true;
        }
        return false;
    }

    /**
     * Method that excepts only the legal moves for a king
     * @param piece The piece of this class(king)
     * @param col The pieces NEW! column
     * @param row The pieces NEW! row
     * @return true if it is a legal move, false if piece can not go there
     */
    public boolean kingMovement(Piece piece, int col, int row) {
        piece = this;
        if( (piece.col == col+1 || piece.col == col-1 || piece.col == col)
                && (piece.row == row+1 || piece.row == row-1 || piece.row == row)){
            return true;
        }
        return false;
    }
}
