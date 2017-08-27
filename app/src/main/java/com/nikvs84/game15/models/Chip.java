package com.nikvs84.game15.models;

import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikvs84.game15.MainActivity;

/**
 * Created by Admin on 27.08.2017.
 */

public class Chip implements GameItem {
    private int coordX, coordY;
    private int posX, posY;
    private int deltaX, deltaY;
    private TextView chip;
    private MainActivity mainActivity;
    public static final int DELTA_MOVE = 5;

    public Chip(int coordX, int coordY, TextView chip, MainActivity mainActivity) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.chip = chip;
        this.mainActivity = mainActivity;

        deltaX = this.chip.getWidth();
        deltaY = this.chip.getWidth();

        this.posX = coordX * deltaX;
        this.posY = coordY * deltaY;

        setPosition(this.chip, coordX, coordY);
    }

    @Override
    public void moveUp() {
        if (coordY > 0) {
            coordY--;

            int destination = posY - deltaY;

            while (posY < destination - DELTA_MOVE) {
                posY -= DELTA_MOVE;
                posY = destination;
                setPosition(chip, posX, posY);
                mainActivity.updateView();
            }

            setPosition(chip, posX, destination);
            mainActivity.updateView();
        }
    }

    @Override
    public void moveDown() {
        coordY++;

    }

    @Override
    public void moveLeft() {
        if (coordX > 0) {
            coordX--;

        }
    }

    @Override
    public void moveRight() {
        coordX++;

    }

    private void setPosition(TextView view, int x, int y) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.chip.getLayoutParams();
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
    }

}
