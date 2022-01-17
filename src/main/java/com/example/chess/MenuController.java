package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public String getPlayerOne() {
        return playerOne.getText();
    }

    @FXML
    private TextField playerOne;

    public String getPlayerTwo() {
        return playerTwo.getText();
    }

    @FXML
    private TextField playerTwo;

    @FXML
    private Button startBtn;

    @FXML
    public void handlePressed() {
        startBtn.setDisable(playerOne.getText().isEmpty() || playerTwo.getText().isEmpty());
    }

    @FXML
    protected void gameStartBtnClick() {

        GameLogic game = new GameLogic();

        game.white = new Player(1,playerOne.getText());
        game.black = new Player(2,playerTwo.getText());

        try {
            game.startGame(new Stage());
            System.out.println("Player 1: " + game.white.getPlayerName());
            System.out.println("Player 2: " + game.black.getPlayerName());


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Game failed to start!");
        }
    }

}