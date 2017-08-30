package com.nikvs84.game15.controller;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.model.Direction;
import com.nikvs84.game15.model.GameModel;
import com.nikvs84.game15.model.GameObject;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameController implements EventListener {
    MainActivity mainActivity;
    GameModel gameModel;

    public GameController(MainActivity mainActivity, GameModel gameModel) {
        this.mainActivity = mainActivity;
        this.gameModel = gameModel;
    }

    // getters and setters


    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    // functional
    @Override
    public void move(GameObject object) {
        object.move();
    }

    @Override
    public void move(GameObject object, Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted() {

    }
}
