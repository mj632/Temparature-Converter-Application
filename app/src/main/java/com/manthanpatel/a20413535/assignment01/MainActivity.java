package com.manthanpatel.a20413535.assignment01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AppTag";
    private EditText inputVal;
    private TextView outputVal, historyData, inputUnit, outputUnit;
    private RadioButton c2fBtn, f2cBtn;
    private String checkRadioBtn = "Fahrenheit to Celsius";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputVal = findViewById(R.id.inputText);
        outputVal = findViewById(R.id.outputText);
        historyData = findViewById(R.id.historyView);
        inputUnit = findViewById(R.id.inputUnit);
        outputUnit = findViewById(R.id.outputUnit);
        f2cBtn = findViewById(R.id.f2cRadioBtn);
        c2fBtn = findViewById(R.id.c2fRadioBtn);
        historyData.setMovementMethod(new ScrollingMovementMethod());
    }

    public void selectUnit(View v) {
        checkRadioBtn = ((RadioButton) v).getText().toString();
        if(checkRadioBtn.trim().equals("Fahrenheit to Celsius")) {
            inputUnit.setText("°F");
            outputUnit.setText("°C");
        }
        else {
            inputUnit.setText("°C");
            outputUnit.setText("°F");
        }
        inputVal.setText("");
        outputVal.setText("");
    }

    public void makeConversion(View v) {
        String inputV = inputVal.getText().toString();
        String historyBlock = "\n" + historyData.getText();
        double f,c;
        if(inputV.isEmpty()) {
            Toast.makeText(this, "Warning!! Please provide a Temperature value.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkRadioBtn.trim().equals("Fahrenheit to Celsius")) {
            f = Double.parseDouble(inputV);
            //F to C
            c = (f-32.0)/1.8;
            outputVal.setText(String.format("%.1f",c));
            historyData.setText(String.format("F to C: %.1f -> %.1f",f,c) + historyBlock);
        }
        else {
            //C to F
            c = Double.parseDouble(inputV);
            f = c * 1.8 + 32;
            outputVal.setText(String.format("%.1f",f));
            historyData.setText(String.format("C to F: %.1f -> %.1f",c,f) + historyBlock);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", historyData.getText().toString());
        outState.putString("OUTPUTVAL", outputVal.getText().toString());
        outState.putString("OUTPUTUNIT", outputUnit.getText().toString());
        outState.putString("INPUTUNIT", inputUnit.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        historyData.setText(savedInstanceState.getString("HISTORY"));
        outputVal.setText(savedInstanceState.getString("OUTPUTVAL"));
        inputUnit.setText(savedInstanceState.getString("INPUTUNIT"));
        outputUnit.setText(savedInstanceState.getString("OUTPUTUNIT"));
    }

}
