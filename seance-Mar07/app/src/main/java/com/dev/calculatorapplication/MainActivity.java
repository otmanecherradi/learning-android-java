package com.dev.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView showOperation, resText, equalsView;

    String firstValue = "";
    String operation = "";
    String resultValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showOperation = findViewById(R.id.main_show_operation);
        resText = findViewById(R.id.main_res_text);
        equalsView = findViewById(R.id.main_equals_view);
    }

    public void handleClearClick(View view) {
        setCurrentValue("0");
        setCurrentOperation("");

        firstValue = "";
        operation = "";

        equalsView.setVisibility(View.GONE);
    }

    public void handleNumberClick(View view) {
        String currentValue = getCurrentValue();
        String value = getButtonText(view);

        if (currentValue.equals("0"))
            setCurrentValue(value);
        else {
            if (operation == null) {
                operation = "";
                setCurrentValue(value);
            } else {
                if (currentValue.length() > 10)
                    showToast(getString(R.string.main_numbers_error));
                else
                    setCurrentValue(String.format("%s%s", currentValue, value));
            }
        }
    }

    public void handleOperationClick(View view) {
        operation = getButtonText(view);
        firstValue = getCurrentValue();

        setCurrentValue("0");

        setCurrentOperation(firstValue + " " + operation);
    }

    public void handleSpecialOperationClick(View view) {
        String currentValue = getCurrentValue();
        String operation = getButtonText(view);

        if (!currentValue.isEmpty()) {
            final double currentNumber = Double.parseDouble(currentValue);

            switch (operation) {
                case "cos":
                    setCurrentOperation("cos(" + currentNumber + ")");
                    setCurrentValue(String.valueOf(Math.cos(currentNumber)));
                    break;
                case "sin":
                    setCurrentOperation("sin(" + currentNumber + ")");
                    setCurrentValue(String.valueOf(Math.sin(currentNumber)));
                    break;
                case "tan":
                    setCurrentOperation("tan(" + currentNumber + ")");
                    setCurrentValue(String.valueOf(Math.tan(currentNumber)));
                    break;
                case "√":
                    setCurrentOperation("√" + currentNumber);
                    setCurrentValue(String.valueOf(Math.sqrt(currentNumber)));
                    break;
            }
        } else
            showToast(getString(R.string.main_special_operator_error));
    }

    public void handleEqualsClick(View view) {
        equalsView.setVisibility(View.VISIBLE);

        String currentValue = getCurrentValue();

        if (operation.isEmpty()) {
            firstValue = currentValue;
            setCurrentOperation(firstValue);
            return;
        }

        Double result = (double) 0;

        final double firstNumber = Double.parseDouble(firstValue);
        final double currentNumber = Double.parseDouble(currentValue);

        switch (operation) {
            case "+":
                result = firstNumber + currentNumber;
                break;
            case "-":
                result = firstNumber - currentNumber;
                break;
            case "x":
                result = firstNumber * currentNumber;
                break;
            case "/":
                if (currentNumber == 0) {
                    showToast(getString(R.string.main_division_error));
                    return;
                }
                result = firstNumber / currentNumber;
                break;
        }

        resultValue = String.valueOf(result);
        setCurrentValue(resultValue);

        setCurrentOperation(firstNumber + " " + operation + " " + currentNumber);

        firstValue = "";
        operation = null;
    }

    public void handleCommaClick(View view) {
        String v = getCurrentValue();
        if (v.isEmpty())
            setCurrentValue("0.");
        if (!v.contains("."))
            setCurrentValue(String.format("%s.", v));
    }

    String getButtonText(View view) {
        return ((Button) view).getText().toString();
    }

    String getCurrentValue() {
        return resText.getText().toString();
    }

    void setCurrentValue(String v) {
        resText.setText(v);
    }

    void setCurrentOperation(String v) {
        showOperation.setText(v);
    }

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}