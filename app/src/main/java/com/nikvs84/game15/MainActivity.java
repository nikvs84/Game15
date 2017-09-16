package com.nikvs84.game15;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nikvs84.game15.controller.EventListener;
import com.nikvs84.game15.controller.GameController;
import com.nikvs84.game15.model.GameModel;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EventListener controller;
    GameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        model = new GameModel(this);
        controller = new GameController(this, model);
        model.setController(controller);

        model.startNewLevel();
//        model.setOneChip();
//        model.setTextForView();
    }

    @Override
    protected void onDestroy() {
        try {
            model.saveGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            model.saveGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

    public void updateView() {

    }
}
