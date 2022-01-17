package com.example.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class GameBoardController extends MenuController{

    @FXML
    ImageView GameBoard;

    public void setPlayerOneLabel(String text) {
        PlayerOneLabel.setText(text);
    }

    @FXML
    Label PlayerOneLabel;

    public void setPlayerTwoLabel(String text) {
        PlayerTwoLabel.setText(text);
    }

    @FXML
    Label PlayerTwoLabel;

    public GridPane getgPane() {
        return gPane;
    }

    @FXML
    GridPane gPane;

    @FXML
    Circle pOneAvatar;

    @FXML
    Circle pTwoAvatar;

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