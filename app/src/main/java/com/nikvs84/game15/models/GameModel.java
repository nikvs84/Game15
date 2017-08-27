package com.nikvs84.game15.models;

import android.support.v7.app.AppCompatActivity;

import com.nikvs84.game15.controllers.GameController;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameModel {
    GameController controller;
    AppCompatActivity mainActivity;

    public GameModel(GameController controller, AppCompatActivity mainActivity, GameItem[][] gameField) {
        this.controller = controller;
        this.mainActivity = mainActivity;
        this.gameField = gameField;
    }

    private GameItem[][] gameField;

}
