package com.nikvs84.game15.controller;

import com.nikvs84.game15.model.Direction;
import com.nikvs84.game15.model.GameObject;

/**
 * Created by Admin on 29.08.2017.
 */

public interface EventListener {
    void move(GameObject object);
    void move(GameObject object, Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted();
}
