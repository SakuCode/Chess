package com.example.chess;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Iterator;

public class Movement {

    Player player,enemy;
    GridPane gridPane;
    GameLogic gl;
    public boolean moveMade;

    public Movement(Player player, GridPane gridPane, GameLogic gl) {
        this.player = player;
        this.gridPane = gridPane;
        this.gl = gl;
        moveMade = false;
    }

    private void sourceOnDragDetected(ImageView source) {

        source.setOnDragDetected(event -> {
            //Drag was detected, start drag-and-drop gesture
            //Allow any transfer node
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            //Put ImageView on DragBoard
            ClipboardContent cbContent = new ClipboardContent();
            cbContent.putImage(source.getImage());
            db.setContent(cbContent);
            source.setVisible(true);
            event.consume();
        });
    }

    private void sourceOnDragDone(ImageView source, GridPane target, Piece piece) {
        source.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                target.getChildren().remove(source);
                event.consume();
            }else{
                Dragboard db = event.getDragboard();
                target.getChildren().remove(source);
                ImageView image = new ImageView(db.getImage());
                Piece p = new Piece(piece.getName(),piece.id,image,piece.moves,piece.col,piece.row);

                GridPane.setHalignment(image, HPos.CENTER);
                GridPane.setValignment(image, VPos.CENTER);

                target.add(image,piece.col,piece.row);

                player.pieces.remove(piece);
                player.pieces.add(p);

                if(gl.firstMove % 2 == 1) {
                    if (player.getPlayerId() == 1) {
                        gl.customizePiece.customWhitePiece(image);
                    } else {
                        p.getImage().setRotate(180);
                        gl.customizePiece.customBlackPiece(image);
                    }
                } else{
                    if (player.getPlayerId() == 1) {
                        p.getImage().setRotate(180);
                        gl.customizePiece.customBlackPiece(image);
                    } else {
                        gl.customizePiece.customWhitePiece(image);
                    }
                }
                gl.playerTurn(player, Movement.this);
            }
        });
    }

    private void targetOnDragOver(GridPane target) {

        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }

    private void targetOnDragEntered(GridPane target, ImageView source, Piece piece) {

        target.setOnDragEntered(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                source.setVisible(false);
                target.setOpacity(0.7);
                System.out.println("Drag entered");
                System.out.println("Searching valid place for " + piece.getName());
            }
            event.consume();
        });
    }

    private void targetOnDragExited(GridPane target, ImageView source) {
        target.setOnDragExited(event -> {
            source.setVisible(false);
            target.setOpacity(1);
            event.consume();
        });
    }

    private void targetOnDragDropped(GridPane target, ImageView source, Piece piece) {

        target.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            Node node = event.getPickResult().getIntersectedNode();

            if (db.hasImage() && node != target) {

                Integer col = GridPane.getColumnIndex(node);
                Integer row = GridPane.getRowIndex(node);
                int x = col == null ? 0 : col;
                int y = row == null ? 0 : row;


                if (isValidPlace(piece,player,x,y)) {
                    try {

                        success = true;
                        moveMade = true;

                        Iterator<Piece> enemyPiecesIt = enemy.getPieces().iterator();

                        while (enemyPiecesIt.hasNext()) {
                            Piece enemyPieceA = enemyPiecesIt.next();
                            if (enemyPieceA.col == x && enemyPieceA.row == y) {
                                target.getChildren().remove(enemyPieceA.image);
                                enemyPiecesIt.remove();
                                scoreAdd(player, enemy, enemyPieceA, piece);
                            }
                        }

                        ImageView image = new ImageView(db.getImage());
                        StackPane empty = new StackPane();
                        target.add(empty, piece.col, piece.row);
                        GridPane.setHalignment(image, HPos.CENTER);
                        GridPane.setValignment(image, VPos.CENTER);

                        target.getChildren().removeAll(source);
                        target.add(image, x, y);

                        Piece p = new Piece(piece.getName(),piece.id,image,piece.moves,x,y);

                        player.getPieces().remove(piece);
                        gl.addChessPiece(player, p);


                        if (playerHasMoved(player)) {
                            System.out.println("Player " + player.getPlayerId() + " made move");
                            if (player == gl.playerOne) {
                                gl.playerTurn(gl.playerTwo, gl.playerMovement = new Movement(gl.playerTwo, gl.gameBoardController.getgPane(), gl));
                            } else {
                                gl.playerTurn(gl.playerOne, gl.playerMovement = new Movement(gl.playerOne, gl.gameBoardController.getgPane(), gl));
                            }
                        }

                        if (player.getPlayerId() == 1) {
                            gl.customizePiece.customWhitePiece(image);
                        } else {
                            gl.customizePiece.customBlackPiece(image);
                        }
                        event.consume();

                    } catch (Exception e) {
                            System.out.println("Failed to move Chess Piece!");
                            e.printStackTrace();
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void movePiece(Piece piece) {

        ImageView source = piece.getImage();
        GridPane target = gridPane;

        sourceOnDragDetected(source);
        targetOnDragOver(target);
        targetOnDragEntered(target, source, piece);
        targetOnDragExited(target, source);
        targetOnDragDropped(target, source, piece);
        sourceOnDragDone(source,gl.gameBoardController.getgPane(),piece);
    }

    public void scoreAdd(Player player, Player enemy, Piece enemyPiece, Piece playerPiece){

        switch(enemyPiece.getName()){
            case "pawn":
                if(enemy.getPlayerId() == 2){
                    gl.gameScore.playerOneScore += 1;
                    gl.gameScore.playerTwoScore -= 1;
                } else{
                    gl.gameScore.playerTwoScore += 1;
                    gl.gameScore.playerOneScore -= 1;
                }
                break;
            case "knight":
            case "bishop":
                if(enemy.getPlayerId() == 2){
                    gl.gameScore.playerOneScore += 3;
                    gl.gameScore.playerTwoScore -= 3;
                } else{
                    gl.gameScore.playerTwoScore += 3;
                    gl.gameScore.playerOneScore -= 3;
                }
                break;
            case "rook":
                if(enemy.getPlayerId() == 2){
                    gl.gameScore.playerOneScore += 5;
                    gl.gameScore.playerTwoScore -= 5;
                } else{
                    gl.gameScore.playerTwoScore += 5;
                    gl.gameScore.playerOneScore -= 5;
                }
                break;
            case "queen":
                if(enemy.getPlayerId() == 2){
                    gl.gameScore.playerOneScore += 9;
                    gl.gameScore.playerTwoScore -= 9;
                } else{
                    gl.gameScore.playerTwoScore += 9;
                    gl.gameScore.playerOneScore -= 9;
                }
                break;
            case "king":
                if(enemy.getPlayerId() == 2){
                    gl.gameScore.playerOneWholeScore += 1;
                    gl.resetBoard(player,this,playerPiece);
                }else{

                    gl.gameScore.playerTwoWholeScore += 1;
                    gl.resetBoard(player,this,playerPiece);
                }
                break;
            default:
                System.out.println("Piece is not named right!");
        }
        gl.gameBoardController.playerOneScore.setText(String.valueOf(gl.gameScore.playerOneScore));
        gl.gameBoardController.playerTwoScore.setText(String.valueOf(gl.gameScore.playerTwoScore));
        gl.gameBoardController.playerOneWholeScore.setText(String.valueOf(gl.gameScore.playerOneWholeScore));
        gl.gameBoardController.playerTwoWholeScore.setText(String.valueOf(gl.gameScore.playerTwoWholeScore));
    }

    public boolean playerHasMoved(Player player) {

        if (moveMade) {
            turnBoard(gl.gameBoardController.getgPane(), player,enemy);
            return true;
        }
        return false;
    }

    /**
     * Turns the game board and pieces after each turn
     * @param gridPane The game board
     * @param player The player currently moving
     * @param enemy The other player(Opponent)
     */
    public void turnBoard(GridPane gridPane, Player player,Player enemy) {

        double paneTempY = gl.gameBoardController.getPlayerOnePane().getLayoutY();
        gl.gameBoardController.getPlayerOnePane().setLayoutY(gl.gameBoardController.getPlayerTwoPane().getLayoutY());
        gl.gameBoardController.getPlayerTwoPane().setLayoutY(paneTempY);

        double wholeScoreTempY = gl.gameBoardController.playerOneWholeScore.getLayoutY();
        gl.gameBoardController.playerOneWholeScore.setLayoutY(gl.gameBoardController.playerTwoWholeScore.getLayoutY());
        gl.gameBoardController.playerTwoWholeScore.setLayoutY(wholeScoreTempY);

        double scoreTempY = gl.gameBoardController.playerOneScore.getLayoutY();
        gl.gameBoardController.playerOneScore.setLayoutY(gl.gameBoardController.playerTwoScore.getLayoutY());
        gl.gameBoardController.playerTwoScore.setLayoutY(scoreTempY);

        if(gl.firstMove % 2 == 1) {
            if (player.getPlayerId() == 1) {
                gridPane.setRotate(180);

                for (Piece e : player.getPieces()) {
                    e.image.setRotate(180);
                }

                for (Piece e : enemy.getPieces()) {
                    e.image.setRotate(180);
                }
            } else {
                gridPane.setRotate(0);
                for (Piece e : player.getPieces()) {
                    e.image.setRotate(0);
                }

                for (Piece e : enemy.getPieces()) {
                    e.image.setRotate(0);
                }
            }
        } else{
            if (player.getPlayerId() == 1) {
                gridPane.setRotate(0);

                for (Piece e : player.getPieces()) {
                    e.image.setRotate(0);
                }

                for (Piece e : enemy.getPieces()) {
                    e.image.setRotate(0);
                }
            } else {
                gridPane.setRotate(0);
                for (Piece e : player.getPieces()) {
                    e.image.setRotate(180);
                }

                for (Piece e : enemy.getPieces()) {
                    e.image.setRotate(180);
                }
            }
        }
    }

    private boolean cellHasPlayerPiece(Player player, int col, int row) {
        for (Piece piece : player.getPieces()) {
            if (piece.row == row && piece.col == col) {
                return true;
            }
        }
        return false;
    }

    boolean isValidPlace(Piece piece, Player player, int col, int row){

        if(player.getPlayerId()==1){
            enemy = gl.playerTwo;
        }else{
            enemy = gl.playerOne;
        }

        if(!cellHasPlayerPiece(player, col, row)){
            System.out.println(piece.getName() + " old place: " + piece.col + "," + piece.row);
            System.out.println(piece.getName() + " new place: " + col + "," + row);
            switch(piece.getName()){
                case "pawn":
                    if(piece.pawnMovement(player,piece,col,row,enemy)) {
                        return true;
                    } else{
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                case "rook":
                    if(piece.rookMovement(piece,col,row)) {
                        return true;
                    }else {
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                case "knight":
                    if(piece.knightMovement(piece,col,row)){
                        return true;
                    }else {
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                case "bishop":
                    if(piece.bishopMovement(piece,col,row)){
                        return true;
                    }else {
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                case "queen":
                    if(piece.queenMovement(piece,col,row)){
                        return true;
                    }else {
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                case "king":
                    if(piece.kingMovement(piece,col,row)){
                        return true;
                    }else {
                        System.out.println("Invalid place for " + piece.getName());
                        return false;
                    }
                default:
                    return false;
            }
        }
        return false;
    }
}