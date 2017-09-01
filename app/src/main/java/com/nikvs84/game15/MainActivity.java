package com.nikvs84.game15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nikvs84.game15.controller.EventListener;
import com.nikvs84.game15.controller.GameController;
import com.nikvs84.game15.model.GameModel;

public class MainActivity extends AppCompatActivity {

    EventListener controller;
    GameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new GameModel(this);
        controller = new GameController(this, model);
        model.setController(controller);

        model.fillGameField();
//        model.setOneChip();
//        model.setTextForView();
    }

    public void updateView() {

    }
}
