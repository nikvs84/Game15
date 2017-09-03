package com.nikvs84.game15.controller;

import android.view.View;

import com.nikvs84.game15.model.GameModel;

/**
 * Created by Admin on 03.09.2017.
 */

public class RBListener implements View.OnClickListener {
    GameModel gameModel;
    public RBListener(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void onClick(View v) {
        gameModel.startNewLevel(gameModel.getGameRowsCount());
    }
}
