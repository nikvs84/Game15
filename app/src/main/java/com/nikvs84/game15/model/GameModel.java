package com.nikvs84.game15.model;

import android.support.v7.app.AppCompatActivity;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.controller.EventListener;
import com.nikvs84.game15.controller.GameController;

import java.util.Set;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameModel {
    EventListener controller;
    MainActivity view;
    Set<Chip> gameChips;
    public static final int ROW_COUNT = 4;
    public static final int COL_COUNT = 4;
    public static final int DELTA_MOVE = 5;

    private GameObject[][] gameField;

    // constructors
    public GameModel(MainActivity mainActivity) {
        this.view = mainActivity;
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

    public GameObject[][] getGameField() {
        return gameField;
    }

    public void setGameField(GameObject[][] gameField) {
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
}
