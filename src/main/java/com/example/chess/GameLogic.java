package com.example.chess;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class GameLogic{


    MenuController menuController;
    Player playerOne, playerTwo;
    GameBoardController gameBoardController;
    CustomizePiece customizePiece = new CustomizePiece();
    Score gameScore;
    int firstMove;
    Movement playerMovement;

    Scene scene;

    public void startGame(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChessApplication.class.getResource("game-board.fxml"));
        Parent root = loader.load();
        stage.setTitle("Chess");
        stage.setScene(scene = new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();

        gameBoardController = loader.getController();
        gameBoardController.setPlayerOneLabel(playerOne.getPlayerName());
        gameBoardController.setPlayerTwoLabel(playerTwo.getPlayerName());

        gameScore = new Score(0,0,0,0);
        gameBoardController.playerOneScore.setText(String.valueOf(gameScore.playerOneScore));
        gameBoardController.playerOneScore.setText(String.valueOf(gameScore.playerTwoScore));

        renderWhitePieces(playerOne);
        renderBlackPieces(playerTwo);

        playerTurn(playerOne, playerMovement = new Movement(playerOne, gameBoardController.getgPane(), this));
        firstMove = 1;

    }

    void renderWhitePieces(Player player) {

        int pid = 1;

        for (int i = 0; i < 8; i++) {

            ImageView pawn = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_pawn.png"))));
            customizePiece.customWhitePiece(pawn);
            gameBoardController.getgPane().add(pawn, i, 6);

            Piece p = new Piece("pawn", pid, pawn,0, i, 6);
            System.out.println("New White Chess Piece: " + p.getName() + pid);
            addChessPiece(player, p);
            pid++;

            GridPane.setHalignment(pawn, HPos.CENTER);
            GridPane.setValignment(pawn, VPos.CENTER);

            if (i == 0 || i == 7) {
                ImageView rook = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_rook.png"))));
                customizePiece.customWhitePiece(rook);
                gameBoardController.getgPane().add(rook, i, 7);

                Piece r = new Piece("rook", pid, rook,0, i, 7);
                System.out.println("New White Chess Piece: " + r.getName() + pid);
                addChessPiece(player, r);
                pid++;

                GridPane.setHalignment(rook, HPos.CENTER);
                GridPane.setValignment(rook, VPos.CENTER);
            } else if (i == 1 || i == 6) {
                ImageView knight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_knight.png"))));
                customizePiece.customWhitePiece(knight);
                gameBoardController.getgPane().add(knight, i, 7);

                Piece k = new Piece("knight", pid, knight,0, i, 7);
                System.out.println("New White Chess Piece: " + k.getName() + pid);
                addChessPiece(player, k);
                pid++;

                GridPane.setHalignment(knight, HPos.CENTER);
                GridPane.setValignment(knight, VPos.CENTER);
            } else if (i == 2 || i == 5) {
                ImageView bishop = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_bishop.png"))));
                customizePiece.customWhitePiece(bishop);
                gameBoardController.getgPane().add(bishop, i, 7);

                Piece b = new Piece("bishop", pid, bishop,0, i, 7);
                System.out.println("New White Chess Piece: " + b.getName() + pid);
                addChessPiece(player, b);
                pid++;

                GridPane.setHalignment(bishop, HPos.CENTER);
                GridPane.setValignment(bishop, VPos.CENTER);
            } else if (i == 3) {
                ImageView queen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_queen.png"))));
                customizePiece.customWhitePiece(queen);
                gameBoardController.getgPane().add(queen, i, 7);

                Piece q = new Piece("queen", pid, queen,0, i, 7);
                System.out.println("New White Chess Piece: " + q.getName() + pid);
                addChessPiece(player, q);
                pid++;

                GridPane.setHalignment(queen, HPos.CENTER);
                GridPane.setValignment(queen, VPos.CENTER);
            } else {
                ImageView king = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_king.png"))));
                customizePiece.customWhitePiece(king);
                gameBoardController.getgPane().add(king, i, 7);

                Piece k = new Piece("king", pid, king,0, i, 7);
                System.out.println("New White Chess Piece: " + k.getName() + pid);
                addChessPiece(player, k);
                pid++;

                GridPane.setHalignment(king, HPos.CENTER);
                GridPane.setValignment(king, VPos.CENTER);
            }
        }

    }

    void renderBlackPieces(Player player) {

        int pid = 1;

        for (int i = 0; i < 8; i++) {
            ImageView pawn = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_pawn.png"))));
            customizePiece.customBlackPiece(pawn);
            gameBoardController.getgPane().add(pawn, i, 1);

            Piece p = new Piece("pawn", pid, pawn,0, i, 1);
            System.out.println("New Black Chess Piece: " + p.getName() + pid);
            addChessPiece(player, p);
            pid++;

            GridPane.setHalignment(pawn, HPos.CENTER);
            GridPane.setValignment(pawn, VPos.CENTER);

            if (i == 0 || i == 7) {
                ImageView rook = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_rook.png"))));
                customizePiece.customBlackPiece(rook);
                gameBoardController.getgPane().add(rook, i, 0);

                Piece r = new Piece("rook", pid, rook,0, i, 0);
                System.out.println("New Black Chess Piece: " + r.getName() + pid);
                addChessPiece(player, r);
                pid++;

                GridPane.setHalignment(rook, HPos.CENTER);
                GridPane.setValignment(rook, VPos.CENTER);
            } else if (i == 1 || i == 6) {
                ImageView knight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_knight.png"))));
                customizePiece.customBlackPiece(knight);
                gameBoardController.getgPane().add(knight, i, 0);

                Piece k = new Piece("knight", pid, knight,0, i, 0);
                System.out.println("New Black Chess Piece: " + k.getName() + pid);
                addChessPiece(player, k);
                pid++;

                GridPane.setHalignment(knight, HPos.CENTER);
                GridPane.setValignment(knight, VPos.CENTER);
            } else if (i == 2 || i == 5) {
                ImageView bishop = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_bishop.png"))));
                customizePiece.customBlackPiece(bishop);
                gameBoardController.getgPane().add(bishop, i, 0);

                Piece b = new Piece("bishop", pid, bishop,0, i, 0);
                System.out.println("New Black Chess Piece: " + b.getName() + pid);
                addChessPiece(player, b);
                pid++;

                GridPane.setHalignment(bishop, HPos.CENTER);
                GridPane.setValignment(bishop, VPos.CENTER);
            } else if (i == 3) {
                ImageView queen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_queen.png"))));
                customizePiece.customBlackPiece(queen);
                gameBoardController.getgPane().add(queen, i, 0);

                Piece qB = new Piece("queen", pid, queen,0, i, 0);
                System.out.println("New Black Chess Piece: " + qB.getName() + pid);
                addChessPiece(player, qB);
                pid++;

                GridPane.setHalignment(queen, HPos.CENTER);
                GridPane.setValignment(queen, VPos.CENTER);
            } else {
                ImageView king = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_king.png"))));
                customizePiece.customBlackPiece(king);
                gameBoardController.getgPane().add(king, i, 0);

                Piece k = new Piece("king", pid, king,0, i, 0);
                System.out.println("New Black Chess Piece: " + k.getName() + pid);
                addChessPiece(player, k);
                pid++;

                GridPane.setHalignment(king, HPos.CENTER);
                GridPane.setValignment(king, VPos.CENTER);
            }
        }
    }

    void addChessPiece(Player a, Piece c) {
        a.pieces.add(c);
    }

    void playerTurn(Player player, Movement playerMovement) {

            for(Piece piece : player.getPieces()){
                piece.image.setOnMousePressed(event ->{
                        if(playerMovement.moveMade)
                            {
                                event.consume();
                                return;
                            }
                        playerMovement.movePiece(piece);
                        return;
                });

            }
            return;
    }

    void resetBoard(Player player,Movement m,Piece piece) {

        gameScore.playerOneScore = 0;
        gameScore.playerTwoScore = 0;
        gameBoardController.playerOneScore.setText(String.valueOf(gameScore.playerOneScore));
        gameBoardController.playerOneScore.setText(String.valueOf(gameScore.playerTwoScore));

        for (Piece e : playerOne.getPieces()) {
            gameBoardController.getgPane().getChildren().remove(e.image);
        }

        for (Piece e : playerTwo.getPieces()) {
            gameBoardController.getgPane().getChildren().remove(e.image);
        }

        Iterator<Piece> playerPieceIt;
        if (player.getPlayerId() == 1) {
            playerPieceIt = playerOne.getPieces().iterator();
        }else{
            playerPieceIt = playerTwo.getPieces().iterator();
        }

        while (playerPieceIt.hasNext()) {
            playerPieceIt.next();
            playerPieceIt.remove();
        }

        for(Piece p : playerOne.getPieces()){
            System.out.println(p.name);
        }

        for(Piece p : playerTwo.getPieces()){
            System.out.println(p.name);
        }

        firstMove += 1;

        playerOne = new Player(1, gameBoardController.getPlayerOneLabel().getText());
        playerTwo = new Player(2, gameBoardController.getPlayerTwoLabel().getText());

        if (firstMove % 2 == 0) {
            renderWhitePieces(playerTwo);
            renderBlackPieces(playerOne);
            playerMovement.turnBoard(gameBoardController.getgPane(),playerTwo,playerOne);
            playerTurn(playerTwo,new Movement(playerTwo, gameBoardController.getgPane(), this));
        } else {
            renderWhitePieces(playerOne);
            renderBlackPieces(playerTwo);
            playerMovement.turnBoard(gameBoardController.getgPane(),playerOne,playerTwo);
            playerTurn(playerOne,new Movement(playerTwo, gameBoardController.getgPane(), this));
        }
    }
}