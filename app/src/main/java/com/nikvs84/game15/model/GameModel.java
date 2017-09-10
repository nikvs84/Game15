package com.nikvs84.game15.model;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikvs84.game15.MainActivity;
import com.nikvs84.game15.R;
import com.nikvs84.game15.controller.EventListener;
import com.nikvs84.game15.controller.RBListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 27.08.2017.
 */

public class GameModel {
    private int maxGameNumber;
    EventListener controller;
    RBListener rbListener;
    MainActivity view;
    Set<Chip> gameChips;
    Context context;
    RelativeLayout gameFieldLayout;
    RelativeLayout infoBar;
    private int orientation;
    private int rowCount = 4;
    private int colCount = 4;
    private int deltaMove = 5;

    private static Chip[][] gameField;

    // constructors
    public GameModel(MainActivity mainActivity) {
        this.view = mainActivity;
        gameField = new Chip[rowCount][colCount];
        gameChips = new HashSet<>();
        context = view.getApplicationContext();
        rbListener = new RBListener(this);
        setLayoutsByOrientation();
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

    /**
     * Заполняет игровое поле фишками.
     */
    public void fillGameField() {
        for (int i = 0; i < gameField.length; i++) {
            Chip[] chips = gameField[i];
            for (int j = 0; j < chips.length; j++) {
                if (i == gameField.length - 1 && j == chips.length - 1) {
                    break;
                }
                Chip chip = new Chip(j, i);
                chip.setModel(this);
                int chipNumber = getNextNumber();
                chip.setId(chipNumber);
                gameField[i][j] = chip;
            }
        }
    }

    /**
     * Добавляет в игровые фишки кнопки и устанавливает эти кнопки на слой <b><i>gameField</i></b>
     */
    public void addGameFieldToLayout() {
        for (int i = 0; i < gameField.length; i++) {
            Chip[] chips = gameField[i];
            for (int j = 0; j < chips.length; j++) {
                if (i == gameField.length - 1 && j == chips.length - 1) {
                    break;
                }
                Chip chip = gameField[i][j];
                chip.setModel(this);
                int chipNumber = chip.getId();
                Button button = new Button(context);
                setChipParams(button, chipNumber);
                chip.setChip(button);
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

    /**
     * Устанавливает атрибуты (параметры) для визуального представления игровой фишки.
     * @param view представление фишки (визуальный компонент)
     * @param number номер фишки
     */
    private void setChipParams(TextView view, int number) {
        view.setText("" + number);
        view.setId(number);
        view.setOnClickListener((View.OnClickListener) controller);
        gameFieldLayout.addView(view);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();

        params.width = context.getResources().getDimensionPixelSize(R.dimen.cell_width);
        params.height = context.getResources().getDimensionPixelSize(R.dimen.cell_height);
    }

    /**
     * Возвращает незадействованный номер игровой фишки.
     * @return int (свободный номер для игровой фишки)
     */
    private int getNextNumber() {
        int result = getRandomValue(1, maxGameNumber);
        for (Chip chip: gameChips) {
            if (chip.getChip().getText().equals("" + result)) {
                result = getNextNumber();
            }
        }

        return result;
    }

    /**
     * Возвращает случайное целое число в диапазоне [<b><i>leftBound</i></b>, <b><i>rightBound</i></b>].
     * @param leftBound левая граница диапазона
     * @param rightBound правая граница диапазона
     * @return int (случайное целое число)
     */
    private int getRandomValue(int leftBound, int rightBound) {
        int result = 0;
        int temp = rightBound - leftBound + 1;
        result = (int) (Math.random() * temp) + leftBound;

        return result;
    }

    /**
     * Возвращает <b><i>true</i></b>, если уровень завершен и <b><i>false</i></b>, если не завершен.
     * @return boolean
     */
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

    /**
     * Очистка игрового поля.
     * Удаляются все игровые объекты.
     */
    public void clearGameField() {
        gameFieldLayout.removeAllViews();
        for (Chip chip : this.getGameChips()) {
            chip = null;
        }
        this.getGameChips().clear();
    }

    public void startNewLevel() {
        setControlBar();
        int rows = getGameRowsCount();
        startNewLevel(rows);
    }

    public void startNewLevel(int rows) {
        startNewLewel(rows, rows);
    }

    /**
     * Старт нового уровня.
     * @param rowCount количество строк игрового поля
     * @param colCount количество столбцов игрового поля
     */
    public void startNewLewel(int rowCount, int colCount) {
        if (rowCount > 0 && colCount > 0) {
            clearGameField();

            this.rowCount = rowCount;
            this.colCount = colCount;

            this.setGameField(new Chip[rowCount][colCount]);

            this.maxGameNumber = this.rowCount * this.colCount - 1;

            TextView info = null;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                info = (TextView) view.findViewById(R.id.info_view);
            } else {
//                info = (TextView) view.findViewById(R.id.info_view_land);
            }
            info.setText(R.string.info_view);

            fillGameField();
            addGameFieldToLayout();
        }
    }

    /**
     * Возвращает количество строк игрового поля.
     * @return int (количество строк игрового поля)
     */
    public int getGameRowsCount() {
        int result = 4;

        RadioGroup radioGroup = null;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            radioGroup = (RadioGroup) view.findViewById(R.id.row_count);
        } else {
//            radioGroup = (RadioGroup) view.findViewById(R.id.row_count_land);
        }
        int checkedId = radioGroup.getCheckedRadioButtonId();

        if (checkedId > 0) {
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
            String rbLabel = (String) radioButton.getText();
            char c = rbLabel.charAt(0);

            switch (c) {
                case '3':
                    result = 3;
                    break;
                case '4':
                    result = 4;
                    break;
                default:
                    result = 4;
            }
        }

        return result;
    }

    /**
     * Устанавливает элементы панели управления.
     */
    private void setControlBar() {
        RadioGroup radioGroup = null;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            radioGroup = (RadioGroup) view.findViewById(R.id.row_count);
        } else {
//            radioGroup = (RadioGroup) view.findViewById(R.id.row_count_land);
        }
        radioGroup = (RadioGroup) view.findViewById(R.id.row_count);
        if (radioGroup == null) {
//            radioGroup = (RadioGroup) view.findViewById(R.id.row_count_land);
        }

        RadioButton rb_3 = new RadioButton(context);
        RadioButton rb_4 = new RadioButton(context);
        radioGroup.addView(rb_3);
        radioGroup.addView(rb_4);

        rb_3.setText(R.string.rb_3);
        rb_4.setText(R.string.rb_4);

        rb_3.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        rb_4.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

        rb_3.setBackgroundColor(context.getResources().getColor(R.color.rb_background));
        rb_4.setBackgroundColor(context.getResources().getColor(R.color.rb_background));

        RadioGroup.LayoutParams lp3 = (RadioGroup.LayoutParams) rb_3.getLayoutParams();
        RadioGroup.LayoutParams lp4 = (RadioGroup.LayoutParams) rb_4.getLayoutParams();
        lp3.weight = 1;
        lp4.weight = 1;

        setRBListener(radioGroup, rbListener);

        rb_3.setChecked(false);
        rb_4.setChecked(true);
    }

    /**
     * Устанавливает OnClickListener для группы радио-кнопок <b><i>radioGroup</i></b>
     * @param radioGroup группа радиокнопок
     * @param listener слушатель события <b><i>onClick</i></b> для ридио-кнопки.
     */
    private void setRBListener(RadioGroup radioGroup, View.OnClickListener listener) {
        int count = radioGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);

            rb.setOnClickListener(listener);
        }
    }

    public void setLayoutsByOrientation() {
        orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gameFieldLayout = (RelativeLayout) view.findViewById(R.id.gameField);
            infoBar = (RelativeLayout) view.findViewById(R.id.info_bar);
        } else {
//            gameFieldLayout = (RelativeLayout) view.findViewById(R.id.gameField_land);
//            infoBar = (RelativeLayout) view.findViewById(R.id.info_bar_land);
        }
    }
}
