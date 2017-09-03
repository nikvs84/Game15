package com.nikvs84.game15.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.R;
import com.nikvs84.game15.model.Chip;
import com.nikvs84.game15.model.Direction;
import com.nikvs84.game15.model.GameModel;
import com.nikvs84.game15.model.GameObject;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameController implements EventListener, View.OnClickListener {
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

    @Override
    public void onClick(View v) {
        chipOnclick(v);
    }

    private void chipOnclick(View v) {
        for (Chip chip : gameModel.getGameChips()) {
            if (chip.getChip() == v) {
                move(chip);
                break;
            }
        }

        boolean isCurrentLevelComplete = gameModel.isLevelComplete();
        if (isCurrentLevelComplete) {
            onLevelComplete();
        }
    }


    private void onLevelComplete() {
        TextView info = (TextView) mainActivity.findViewById(R.id.info_view);

        info.setText("Поздравляю :-)");
//        info.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

}
