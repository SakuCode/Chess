package com.example.chess;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

/**
 * Simple class made to customize each piece on the game board
 */

public class CustomizePiece {

    ColorAdjust whiteAdjust;
    ColorAdjust blackAdjust;

    public CustomizePiece() {

        whiteAdjust = new ColorAdjust();
        whiteAdjust.setContrast(0);
        whiteAdjust.setHue(0);
        whiteAdjust.setBrightness(0.7);
        whiteAdjust.setSaturation(0);

        blackAdjust = new ColorAdjust();
        blackAdjust.setContrast(0);
        blackAdjust.setHue(0.3);
        blackAdjust.setBrightness(0.4);
        blackAdjust.setSaturation(0.5);
    }

    public void customWhitePiece(ImageView piece){
        piece.setEffect(whiteAdjust);
    }

    public void customBlackPiece(ImageView piece){
        piece.setEffect(blackAdjust);

    }


}
