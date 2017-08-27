package com.nikvs84.game15.controllers;

import android.support.v7.app.AppCompatActivity;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.models.GameModel;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameController {
    MainActivity mainActivity;
    GameModel gameModel;

    public GameController(MainActivity mainActivity, GameModel gameModel) {
        this.mainActivity = mainActivity;
        this.gameModel = gameModel;
    }
}
