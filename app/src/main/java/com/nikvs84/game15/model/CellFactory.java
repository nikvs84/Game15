package com.nikvs84.game15.model;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.nikvs84.game15.R;
/**
 * Created by Admin on 27.08.2017.
 */

public class CellFactory {
    private static final CellFactory ourInstance = new CellFactory();
    public static final int DEFAULT_CELL_SIZE = 50;
    private Context context;
    public static CellFactory getInstance(Context context) {
        ourInstance.context = context;
        return ourInstance;
    }

    private CellFactory() {
    }

    public TextView getCell(ViewType type, int number) {
        TextView view;

        switch (type) {
            case BUTTON:
                view = new Button(context);
                break;
            case TEXT:
                view = new TextView(context);
                break;
            case DEFAULT:
                view = new Button(context);
                break;
            default:
                view = null;
                break;
        }

        if (view != null) {
            setAttributes(view, number, DEFAULT_CELL_SIZE);
        }

        return view;
    }

    public Button getButton(int number) {
        Button result = new Button(context);
        setAttributes(result, number, DEFAULT_CELL_SIZE);

        return result;
    }

    public TextView getTextView(int number) {
        TextView result = new TextView(context);
        setAttributes(result, number, DEFAULT_CELL_SIZE);

        return result;
    }

    private void setAttributes(TextView view, int number) {
        float size = context.getResources().getDimension(R.dimen.cell_size);
        int pixelsCount = Math.round(size);
        setAttributes(view, number, pixelsCount);
    }

    private void setAttributes(TextView view, int number, int size) {
        view.setId(number);
        String label = "" + number;
        view.setText(label);
//        view.setWidth(size);

    }
}
