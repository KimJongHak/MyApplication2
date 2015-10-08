package com.example.blackleaf.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText e1, e2;
    TextView tv1;
    Double result;
    String num1, num2;
    Button[] num=new Button[10];
    Integer[] numId={R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7,
            R.id.b8, R.id.b9};
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");

        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        tv1=(TextView)findViewById(R.id.tv);
        findViewById(R.id.add).setOnClickListener(calc);
        findViewById(R.id.sub).setOnClickListener(calc);
        findViewById(R.id.mul).setOnClickListener(calc);
        findViewById(R.id.div).setOnClickListener(calc);
        for(i=0 ; i<numId.length ; i++) {
            final int index;
            index=i;
            num[index]=(Button)findViewById(numId[index]);
            num[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(e1.isFocused()) {
                        num1=e1.getText().toString() + num[index].getText().toString();
                        e1.setText(num1);
                    }
                    else if(e2.isFocused()) {
                        num2=e2.getText().toString() + num[index].getText().toString();
                        e2.setText(num2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"입력할 칸을 선택하세요", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    Button.OnClickListener calc=new View.OnClickListener(){
        public void onClick(View v) {
            num1 = e1.getText().toString();
            num2 = e2.getText().toString();

            if (num1.equals("") || num2.equals("")) {
                Toast.makeText(getApplicationContext(), "값을 입력해 주세요", Toast.LENGTH_LONG).show();
            }
            else{
                Double n1 = Double.parseDouble(num1);
                Double n2 = Double.parseDouble(num2);

                switch(v.getId()) {
                    case R.id.add:
                        result = n1 + n2;
                        break;
                    case R.id.sub:
                        result = n1 - n2;
                        break;
                    case R.id.mul:
                        result = n1 * n2;
                        break;
                    case R.id.div:
                        if (n1 == 0 || n2 == 0)
                            Toast.makeText(getApplicationContext(), "나누기에 0은 사용할 수 없습니다", Toast.LENGTH_LONG).show();
                        else result = n1 / n2;
                        break;
                }
                tv1.setText("계산 결과 : "+ result.toString());
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
