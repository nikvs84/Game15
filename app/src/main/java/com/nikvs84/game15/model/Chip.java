package com.nikvs84.game15.model;

import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Admin on 27.08.2017.
 */

public class Chip implements GameObject {
    private int coordX, coordY;
    private int posX, posY;
    private int deltaX, deltaY;
    private TextView chip;
    private GameModel model;

    public Chip(int coordX, int coordY, TextView chip, GameModel model) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.chip = chip;
        this.model = model;

        deltaX = this.chip.getWidth();
        deltaY = this.chip.getWidth();

        this.posX = coordX * deltaX;
        this.posY = coordY * deltaY;

        setPosition(this.chip, coordX, coordY);
    }

    // getters and setters


    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    // functional

    @Override
    public void moveUp() {
        if (coordY > 0) {
            coordY--;

            int destination = posY - deltaY;

            while (posY < destination - model.getDeltaMove()) {
                posY -= model.getDeltaMove();
                posY = destination;
                setPosition(chip, posX, posY);
                model.updateView();
            }

            setPosition(chip, posX, destination);
            model.updateView();
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

    @Override
    public void move() {
        Direction direction = getPossibleDirection();
        if (direction != null) {
            move(direction);
        }
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    private void setPosition(TextView view, int x, int y) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.chip.getLayoutParams();
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
    }

    public boolean isCollision(Chip chip, Direction direction) {
        return (isCollision(chip.getCoordX(), chip.getCoordY(), direction));
    }

    public boolean isCollision(int newCoordX, int newCoordY, Direction direction) {
        boolean result = true;
        int tempX = this.getCoordX();
        int tempY = this.getCoordY();
        switch (direction) {
            case UP:
                tempY--;
                break;
            case DOWN:
                tempY++;
                break;
            case LEFT:
                tempX--;
                break;
            case RIGHT:
                tempX++;
                break;
        }

        if (tempX != newCoordX || tempY != newCoordY) {
            if (tempX >= 0 && tempX < model.getColCount() && tempY >= 0 && tempY < model.getRowCount()) {
                result = false;
            }
        }

        return result;
    }

    public boolean isMovePossible(Direction direction) {
        boolean result = false;
        int newCoordX = coordX, newCoordY = coordY;

        switch (direction) {
            case UP:
                newCoordY--;
                break;
            case DOWN:
                newCoordY++;
                break;
            case LEFT:
                newCoordX--;
                break;
            case RIGHT:
                newCoordX++;
                break;
        }

        for (Chip chip : model.getGameChips()) {
            if (chip.getCoordX() != newCoordX || chip.getCoordY() != newCoordY)
                result = true;
            break;
        }

        return result;
    }

    public Direction getPossibleDirection() {
        Direction result = null;
        for (Direction direction : Direction.values()) {
            if (isMovePossible(direction)) {
                result = direction;
                break;
            }
        }
        return result;
    }

}
