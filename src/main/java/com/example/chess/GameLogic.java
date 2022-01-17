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
import java.util.Objects;

public class GameLogic extends ImageView {

    Player white, black;
    GameBoardController gbc;
    CustomizePiece custom = new CustomizePiece();

    Movement movement;

    Scene scene;

    public void startGame(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChessApplication.class.getResource("game-board.fxml"));
        Parent root = loader.load();
        stage.setTitle("Chess");
        stage.setScene(scene = new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();

        gbc = loader.getController();
        gbc.setPlayerOneLabel(white.getPlayerName());
        gbc.setPlayerTwoLabel(black.getPlayerName());

        renderWhitePieces();
        renderBlackPieces();

        playerTurn(white, movement = new Movement(white, gbc.getgPane(), this));

    }

    void renderWhitePieces() {
        int pid = 1;

        for (int i = 0; i < 8; i++) {

            ImageView pawn = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_pawn.png"))));
            custom.customWhitePiece(pawn);
            gbc.getgPane().add(pawn, i, 6);

            Piece p = new Piece("pawn", pid, pawn,0, i, 6);
            System.out.println("New White Chess Piece: " + p.getName() + pid);
            addChessPiece(white, p);
            pid++;

            GridPane.setHalignment(pawn, HPos.CENTER);
            GridPane.setValignment(pawn, VPos.CENTER);

            if (i == 0 || i == 7) {
                ImageView rook = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_rook.png"))));
                custom.customWhitePiece(rook);
                gbc.getgPane().add(rook, i, 7);

                Piece r = new Piece("rook", pid, rook,0, i, 7);
                System.out.println("New White Chess Piece: " + r.getName() + pid);
                addChessPiece(white, r);
                pid++;

                GridPane.setHalignment(rook, HPos.CENTER);
                GridPane.setValignment(rook, VPos.CENTER);
            } else if (i == 1 || i == 6) {
                ImageView knight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_knight.png"))));
                custom.customWhitePiece(knight);
                gbc.getgPane().add(knight, i, 7);

                Piece k = new Piece("knight", pid, knight,0, i, 7);
                System.out.println("New White Chess Piece: " + k.getName() + pid);
                addChessPiece(white, k);
                pid++;

                GridPane.setHalignment(knight, HPos.CENTER);
                GridPane.setValignment(knight, VPos.CENTER);
            } else if (i == 2 || i == 5) {
                ImageView bishop = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_bishop.png"))));
                custom.customWhitePiece(bishop);
                gbc.getgPane().add(bishop, i, 7);

                Piece b = new Piece("bishop", pid, bishop,0, i, 7);
                System.out.println("New White Chess Piece: " + b.getName() + pid);
                addChessPiece(white, b);
                pid++;

                GridPane.setHalignment(bishop, HPos.CENTER);
                GridPane.setValignment(bishop, VPos.CENTER);
            } else if (i == 3) {
                ImageView queen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_queen.png"))));
                custom.customWhitePiece(queen);
                gbc.getgPane().add(queen, i, 7);

                Piece q = new Piece("queen", pid, queen,0, i, 7);
                System.out.println("New White Chess Piece: " + q.getName() + pid);
                addChessPiece(white, q);
                pid++;

                GridPane.setHalignment(queen, HPos.CENTER);
                GridPane.setValignment(queen, VPos.CENTER);
            } else {
                ImageView king = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_king.png"))));
                custom.customWhitePiece(king);
                gbc.getgPane().add(king, i, 7);

                Piece k = new Piece("king", pid, king,0, i, 7);
                System.out.println("New White Chess Piece: " + k.getName() + pid);
                addChessPiece(white, k);
                pid++;

                GridPane.setHalignment(king, HPos.CENTER);
                GridPane.setValignment(king, VPos.CENTER);
            }
        }

    }

    void renderBlackPieces() {

        int pid = 1;

        for (int i = 0; i < 8; i++) {
            ImageView pawn = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_pawn.png"))));
            custom.customBlackPiece(pawn);
            gbc.getgPane().add(pawn, i, 1);

            Piece p = new Piece("pawn", pid, pawn,0, i, 1);
            System.out.println("New Black Chess Piece: " + p.getName() + pid);
            addChessPiece(black, p);
            pid++;

            GridPane.setHalignment(pawn, HPos.CENTER);
            GridPane.setValignment(pawn, VPos.CENTER);

            if (i == 0 || i == 7) {
                ImageView rook = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_rook.png"))));
                custom.customBlackPiece(rook);
                gbc.getgPane().add(rook, i, 0);

                Piece r = new Piece("rook", pid, rook,0, i, 0);
                System.out.println("New Black Chess Piece: " + r.getName() + pid);
                addChessPiece(black, r);
                pid++;

                GridPane.setHalignment(rook, HPos.CENTER);
                GridPane.setValignment(rook, VPos.CENTER);
            } else if (i == 1 || i == 6) {
                ImageView knight = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_knight.png"))));
                custom.customBlackPiece(knight);
                gbc.getgPane().add(knight, i, 0);

                Piece k = new Piece("knight", pid, knight,0, i, 0);
                System.out.println("New Black Chess Piece: " + k.getName() + pid);
                addChessPiece(black, k);
                pid++;

                GridPane.setHalignment(knight, HPos.CENTER);
                GridPane.setValignment(knight, VPos.CENTER);
            } else if (i == 2 || i == 5) {
                ImageView bishop = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_bishop.png"))));
                custom.customBlackPiece(bishop);
                gbc.getgPane().add(bishop, i, 0);

                Piece b = new Piece("bishop", pid, bishop,0, i, 0);
                System.out.println("New Black Chess Piece: " + b.getName() + pid);
                addChessPiece(black, b);
                pid++;

                GridPane.setHalignment(bishop, HPos.CENTER);
                GridPane.setValignment(bishop, VPos.CENTER);
            } else if (i == 3) {
                ImageView queen = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_queen.png"))));
                custom.customBlackPiece(queen);
                gbc.getgPane().add(queen, i, 0);

                Piece qB = new Piece("queen", pid, queen,0, i, 0);
                System.out.println("New Black Chess Piece: " + qB.getName() + pid);
                addChessPiece(black, qB);
                pid++;

                GridPane.setHalignment(queen, HPos.CENTER);
                GridPane.setValignment(queen, VPos.CENTER);
            } else {
                ImageView king = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/chess_king.png"))));
                custom.customBlackPiece(king);
                gbc.getgPane().add(king, i, 0);

                Piece k = new Piece("king", pid, king,0, i, 0);
                System.out.println("New Black Chess Piece: " + k.getName() + pid);
                addChessPiece(black, k);
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
                        playerMovement.movePiece(piece,player);
                        return;
                });

            }
            return;
    }
}