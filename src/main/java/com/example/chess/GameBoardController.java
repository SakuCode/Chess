package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class GameBoardController extends MenuController{

    @FXML
    ImageView GameBoard;

    public Label getPlayerOneLabel() {
        return PlayerOneLabel;
    }

    public Label getPlayerTwoLabel() {
        return PlayerTwoLabel;
    }

    @FXML
    Label PlayerOneLabel;
    @FXML
    Label PlayerTwoLabel;
    @FXML
    Pane playerOnePane;
    @FXML
    Pane playerTwoPane;
    @FXML
    Label playerOneWholeScore;
    @FXML
    Label playerTwoWholeScore;
    @FXML
    Label playerOneScore;
    @FXML
    Label playerTwoScore;
    @FXML
    GridPane gPane;
    @FXML
    Circle pOneAvatar;
    @FXML
    Circle pTwoAvatar;

    public void setPlayerOneLabel(String text) {
        PlayerOneLabel.setText(text);
    }

    public void setPlayerTwoLabel(String text) {
        PlayerTwoLabel.setText(text);
    }

    public Pane getPlayerOnePane() {
        return playerOnePane;
    }

    public Pane getPlayerTwoPane() {
        return playerTwoPane;
    }

    public GridPane getgPane() {
        return gPane;
    }

    @FXML
    protected void pOneGlow(){
        final Glow glow = new Glow();
        pOneAvatar.setEffect(glow);
    }

    @FXML
    protected void pTwoGlow(){
        final Glow glow = new Glow();
        pTwoAvatar.setEffect(glow);
    }

    @FXML
    protected void pOneGlowExit(){
        pOneAvatar.setEffect(null);
    }

    @FXML
    protected void pTwoGlowExit(){
        pTwoAvatar.setEffect(null);
    }

}