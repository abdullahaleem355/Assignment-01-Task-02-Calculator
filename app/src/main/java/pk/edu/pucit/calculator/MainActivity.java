package pk.edu.pucit.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Stack;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButtonPanel();
    }

    private void calculatorButtonPanel(){
        findViewById(R.id.btnOne).setOnClickListener(calcHandler);
        findViewById(R.id.btnTwo).setOnClickListener(calcHandler);
        findViewById(R.id.btnThr).setOnClickListener(calcHandler);
        findViewById(R.id.btnFou).setOnClickListener(calcHandler);
        findViewById(R.id.btnFiv).setOnClickListener(calcHandler);
        findViewById(R.id.btnSix).setOnClickListener(calcHandler);
        findViewById(R.id.btnSev).setOnClickListener(calcHandler);
        findViewById(R.id.btnEig).setOnClickListener(calcHandler);
        findViewById(R.id.btnNin).setOnClickListener(calcHandler);
        findViewById(R.id.btnZer).setOnClickListener(calcHandler);
        findViewById(R.id.btnHun).setOnClickListener(calcHandler);
        findViewById(R.id.btnAdd).setOnClickListener(calcHandler);
        findViewById(R.id.btnSub).setOnClickListener(calcHandler);
        findViewById(R.id.btnDiv).setOnClickListener(calcHandler);
        findViewById(R.id.btnMul).setOnClickListener(calcHandler);
        findViewById(R.id.btnDel).setOnClickListener(calcHandler);
        findViewById(R.id.btnAC).setOnClickListener(calcHandler);
        findViewById(R.id.btnMod).setOnClickListener(calcHandler);
        findViewById(R.id.btnDot).setOnClickListener(calcHandler);
        findViewById(R.id.btnEqu).setOnClickListener(calcHandler);
    }

    private boolean checkOperators(String equ){
        char lastChar = equ.charAt(equ.length() - 1);
        return (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '%' && lastChar != '/' && lastChar != '.');
    }

    private boolean isDotPresent(String equ)
    {
        boolean decimalFlag = false;
        for(int i = 0; i < equ.length(); i++) {
            if (equ.charAt(i) == '.' && !decimalFlag)
                decimalFlag = true;
            if(equ.charAt(i) == '+' || equ.charAt(i) == '-' || equ.charAt(i) == '*' || equ.charAt(i) == '/' || equ.charAt(i) == '%')
                decimalFlag = false;
        }
        return decimalFlag;
    }


    private Float evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
        // Stack for numbers: 'values'
        Stack<Float> values = new Stack<Float>();
        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();
        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')
            {
                StringBuffer sbuf = new StringBuffer();
                sbuf.append(tokens[i]);
                // There may be more than one digits in number
                while ((i+1 < tokens.length) && ((tokens[i+1] >= '0' && tokens[i+1] <= '9')|| tokens[i+1] == '.')) {
                    sbuf.append(tokens[++i]);
                }
                values.push(Float.parseFloat(sbuf.toString()));
            }
            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '%')
            {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                //Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
        // apply remaining ops to remaining values
        while (!ops.empty()){
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    private boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '%')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    private float applyOp(char op, float b, float a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '%':
                return a % b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    private View.OnClickListener calcHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView equation = findViewById(R.id.tv_equation);
            TextView result = findViewById(R.id.tv_result);

            if(v.getId() == R.id.btnAC)
            {
                equation.setText("");
                result.setText("");
            }
            else if(v.getId() == R.id.btnZer)
            {
                if(!(equation.getText().toString().equals("0")))
                    equation.setText(equation.getText().toString() + "0");
            }
            else if(v.getId() == R.id.btnHun)
            {
                if(!(equation.getText().toString().equals("0")))
                    equation.setText(equation.getText().toString() + "00");
            }
            else if(v.getId() == R.id.btnOne)
            {
                equation.setText(equation.getText().toString() + "1");
            }
            else if(v.getId() == R.id.btnTwo)
            {
                equation.setText(equation.getText().toString() + "2");
            }
            else if(v.getId() == R.id.btnThr)
            {
                equation.setText(equation.getText().toString() + "3");
            }
            else if(v.getId() == R.id.btnFou)
            {
                equation.setText(equation.getText().toString() + "4");
            }
            else if(v.getId() == R.id.btnFiv)
            {
                equation.setText(equation.getText().toString() + "5");
            }
            else if(v.getId() == R.id.btnSix)
            {
                equation.setText(equation.getText().toString() + "6");
            }
            else if(v.getId() == R.id.btnSev)
            {
                equation.setText(equation.getText().toString() + "7");
            }
            else if(v.getId() == R.id.btnEig)
            {
                equation.setText(equation.getText().toString() + "8");
            }
            else if(v.getId() == R.id.btnNin)
            {
                equation.setText(equation.getText().toString() + "9");
            }
            else if(v.getId() == R.id.btnAdd)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have op already or decimal point too.
                    if (checkOperators(equ))
                    {
                        equation.setText(equ + "+");
                    }
                    else
                    {
                        // as the android calc do(override the previous op).
                        String newEqu = equ.substring(0, equ.length() - 1);
                        equation.setText(newEqu + "+");
                    }
                }
            }
            else if(v.getId() == R.id.btnSub)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have op already or decimal point too.
                    if(checkOperators(equ))
                    {
                        equation.setText(equ + "-");
                    }
                    else
                    {
                        // as the android calc do(override the previous op).
                        String newEqu = equ.substring(0, equ.length() - 1);
                        equation.setText(newEqu + "-");
                    }
                }
            }
            else if(v.getId() == R.id.btnDiv)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have op already or decimal point too.
                    if(checkOperators(equ))
                    {
                        equation.setText(equ + "/");
                    }
                    else
                    {
                        // as the android calc do(override the previous op).
                        String newEqu = equ.substring(0, equ.length() - 1);
                        equation.setText(newEqu + "/");
                    }
                }
            }
            else if(v.getId() == R.id.btnMul)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have op already or decimal point too.
                    if(checkOperators(equ))
                    {
                        equation.setText(equ + "*");
                    }
                    else
                    {
                        // as the android calc do(override the previous op).
                        String newEqu = equ.substring(0, equ.length() - 1);
                        equation.setText(newEqu + "*");
                    }
                }
            }
            else if(v.getId() == R.id.btnMod)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have op already or decimal point too.
                    if(checkOperators(equ))
                    {
                        equation.setText(equ + "%");
                    }
                    else
                    {
                        // as the android calc do(override the previous op).
                        String newEqu = equ.substring(0, equ.length() - 1);
                        equation.setText(newEqu + "%");
                    }
                }
            }
            else if(v.getId() == R.id.btnDot)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    String equ = equation.getText().toString();
                    // it should not have decimal point already.
                    if(checkOperators(equ) && !isDotPresent(equ))
                    {
                        equation.setText(equ + ".");
                    }
                }
                else {
                    equation.setText("0.");
                }
            }
            else if(v.getId() == R.id.btnDel)
            {
                if(!(equation.getText().toString().isEmpty()))
                {
                    if(equation.getText().toString().length() == 1)
                        equation.setText("");
                    else
                        equation.setText(equation.getText().toString().substring(0, equation.length() - 1));
                }
            }
            else if(v.getId() == R.id.btnEqu)
            {
                Float res = evaluate(equation.getText().toString());
                result.setText(res+"");
            }
        }
    };

}
