package com.nikvs84.game15.model;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.controller.EventListener;
import com.nikvs84.game15.controller.GameController;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameModel {
    public static final int MAX_GAME_NUMBER = 15;
    EventListener controller;
    MainActivity view;
    Set<Chip> gameChips;
    Context context;
    public static final int ROW_COUNT = 4;
    public static final int COL_COUNT = 4;
    public static final int DELTA_MOVE = 5;

    private Chip[][] gameField;

    // constructors
    public GameModel(MainActivity mainActivity) {
        this.view = mainActivity;
        gameField = new Chip[ROW_COUNT][COL_COUNT];
        gameChips = new HashSet<>();
        context = view.getApplicationContext();
    }

    //getters and setters

    public static int getRowCount() {
        return ROW_COUNT;
    }

    public static int getColCount() {
        return COL_COUNT;
    }

    public static int getDeltaMove() {
        return DELTA_MOVE;
    }

    public EventListener getController() {
        return controller;
    }

    public void setController(EventListener controller) {
        this.controller = controller;
    }

    public Chip[][] getGameField() {
        return gameField;
    }

    public void setGameField(Chip[][] gameField) {
        this.gameField = gameField;
    }

    public Set<Chip> getGameChips() {
        return gameChips;
    }

    public void setGameChips(Set<Chip> gameChips) {
        this.gameChips = gameChips;
    }
    // functional

    public void updateView() {
        view.updateView();
    }

    private int[] getCoordinates(int leftBound, int rightBound) {
        int coordX = (int) Math.random() * ROW_COUNT;
        int coordY = (int) Math.random() * COL_COUNT;

        int[] result = new int[]{coordX, coordY};

        return result;
    }

    private int getRandomValue(int leftBound, int rightBound) {
        int result = 0;
        int temp = rightBound - leftBound + 1;
        result = (int) (Math.random() * temp) + leftBound;

        return result;
    }

    public void fillGameField() {
        for (int i = 0; i < gameField.length; i++) {
            Chip[] chips = gameField[i];
            for (int j = 0; j < chips.length; j++) {
                Chip chip = new Chip(i, j);
                chip.setModel(this);
                int chipNumber = getNextNumber();
                chip.setChip(CellFactory.getInstance(context).getButton(chipNumber));
                gameChips.add(chip);
                gameField[i][j] = chip;
            }
        }
    }

    private int getNextNumber() {
        int result = getRandomValue(1, MAX_GAME_NUMBER);
        for (Chip chip: gameChips) {
            if (chip.getChip().getText().equals("" + result)) {
                result = getNextNumber();
            }
        }

        return result;
    }
}
