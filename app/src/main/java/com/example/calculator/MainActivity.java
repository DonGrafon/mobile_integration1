package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText display;
    Button[] buttons = new Button[16];
    Calculator calculator;

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick (View view) {
            Button button = (Button) view;
            try {
                pushButton (button.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editText);
        buttons[0] = findViewById(R.id.button7); // 0
        buttons[1] = findViewById(R.id.button6); // 1
        buttons[2] = findViewById(R.id.button10); // 2
        buttons[3] = findViewById(R.id.button11); // 3
        buttons[4] = findViewById(R.id.button5); // 4
        buttons[5] = findViewById(R.id.button9); // 5
        buttons[6] = findViewById(R.id.button12); // 6
        buttons[7] = findViewById(R.id.button4); // 7
        buttons[8] = findViewById(R.id.button8); // 8
        buttons[9] = findViewById(R.id.button13); // 9
        buttons[10] = findViewById(R.id.button3); // C
        buttons[11] = findViewById(R.id.button16); // +
        buttons[12] = findViewById(R.id.button19); // -
        buttons[13] = findViewById(R.id.button17); // *
        buttons[14] = findViewById(R.id.button18); // /
        buttons[15] = findViewById(R.id.button14); // =

        for (Button button: buttons) {
            button.setOnClickListener(clickListener);
        }

        calculator = new Calculator();
    }

    private void pushButton(String sign) throws IOException {
        if (sign.equals("C")) {
            display.setText("0");
            calculator.first = 0;
            calculator.second = 0;
            calculator.operation = "";
        } else if ("0123456789".contains(sign)){
            if (calculator.second == Integer.parseInt(display.getText().toString())) {
                calculator.second = Integer.parseInt(display.getText().toString() + sign);
            } else {
                calculator.second = Integer.parseInt(sign);
            }
            display.setText(Integer.toString(calculator.second));
        } else {
            calculator.first = calculator.calculate();
            display.setText(Integer.toString(calculator.first));
            calculator.operation = sign;
            calculator.second = 0;
        }
    }
}
