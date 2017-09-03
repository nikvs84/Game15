package com.nikvs84.game15.model;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.R;
import com.nikvs84.game15.controller.EventListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameModel {
    private int maxGameNumber = 15;
    EventListener controller;
    MainActivity view;
    Set<Chip> gameChips;
    Context context;
    RelativeLayout gameFieldLayout;
    RelativeLayout infoBar;
    private int rowCount = 4;
    private int colCount = 4;
    private int deltaMove = 5;

    private Chip[][] gameField;

    // constructors
    public GameModel(MainActivity mainActivity) {
        this.view = mainActivity;
        gameField = new Chip[rowCount][colCount];
        gameChips = new HashSet<>();
        context = view.getApplicationContext();
        this.gameFieldLayout = (RelativeLayout) view.findViewById(R.id.gameField);
        this.infoBar = (RelativeLayout) view.findViewById(R.id.info_bar);
    }


    //getters and setters

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int getDeltaMove() {
        return deltaMove;
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

    public RelativeLayout getInfoBar() {
        return infoBar;
    }
    // functional

    public void updateView() {
        view.updateView();
    }

    private int[] getCoordinates(int leftBound, int rightBound) {
        int coordX = (int) Math.random() * rowCount;
        int coordY = (int) Math.random() * colCount;

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
                if (i == gameField.length - 1 && j == chips.length - 1) {
                    break;
                }
                Chip chip = new Chip(i, j);
                chip.setModel(this);
                int chipNumber = getNextNumber();
                Button button = new Button(context);
                setChipParams(button, chipNumber);
                chip.setChip(button);
                gameField[i][j] = chip;
                gameChips.add(chip);
            }
        }
    }

//    public void setOneChip() {
//        Chip chip = new Chip(1, 3);
//        Button button = new Button(context);
//        button.setText("" + 123);
//        int id = 123;
//        button.setId(id);
//        gameFieldLayout.addView(button);
//
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) button.getLayoutParams();
//
//        params.width = context.getResources().getDimensionPixelSize(R.dimen.cell_width);
//        params.height = context.getResources().getDimensionPixelSize(R.dimen.cell_height);
//
//        chip.setChip(button);
//    }

    private void setChipParams(TextView view, int number) {
        view.setText("" + number);
        view.setId(number);
        view.setOnClickListener((View.OnClickListener) controller);
        gameFieldLayout.addView(view);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

        params.width = context.getResources().getDimensionPixelSize(R.dimen.cell_width);
        params.height = context.getResources().getDimensionPixelSize(R.dimen.cell_height);
    }

    private int getNextNumber() {
        int result = getRandomValue(1, maxGameNumber);
        for (Chip chip: gameChips) {
            if (chip.getChip().getText().equals("" + result)) {
                result = getNextNumber();
            }
        }

        return result;
    }

    public boolean isLevelComplete() {
        boolean result = true;
        int currentId = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                Chip chip = gameField[i][j];
                if (chip == null) {
                    if (i < gameField.length - 1 && j < gameField[i].length - 1) {
                        result = false;
                        return result;
                    }
                    break;
                }
                int chipId = chip.getId();
                if (chipId < currentId) {
                    result = false;
                    return result;
                } else {
                    currentId = chipId;
                }
            }
        }

        return result;
    }

    public void clearGameField() {
        gameFieldLayout.removeAllViews();
    }

    public void startNewLewel(int rowCount, int colCount) {
        if (rowCount > 0 && colCount > 0) {
            clearGameField();

            this.rowCount = rowCount;
            this.colCount = colCount;
            this.maxGameNumber = this.rowCount * this.colCount - 1;

            fillGameField();
        }
    }

}
