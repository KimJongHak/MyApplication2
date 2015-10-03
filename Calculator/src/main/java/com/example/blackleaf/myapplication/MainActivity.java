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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        e1=(EditText)findViewById(R.id.et1);
        e2=(EditText)findViewById(R.id.et2);
        tv1=(TextView)findViewById(R.id.tv1);
        findViewById(R.id.b1).setOnClickListener(calc);
        findViewById(R.id.b2).setOnClickListener(calc);
        findViewById(R.id.b3).setOnClickListener(calc);
        findViewById(R.id.b4).setOnClickListener(calc);
    }

    Button.OnClickListener calc=new View.OnClickListener(){
        public void onClick(View v) {
            String input1 = e1.getText().toString();
            String input2 = e2.getText().toString();

            if (input1.equals("") || input2.equals("")) {
                Toast.makeText(getApplicationContext(), "값을 입력해 주세요", Toast.LENGTH_LONG).show();
            }
            else{
                Double n1 = Double.parseDouble(input1);
                Double n2 = Double.parseDouble(input2);

                switch(v.getId()){
                    case R.id.b1: result=n1+n2; break;
                    case R.id.b2: result=n1-n2; break;
                    case R.id.b3: result=n1*n2; break;
                    case R.id.b4:
                        if(n1==0 || n2==0)
                            Toast.makeText(getApplicationContext(),"나누기에 0은 사용할 수 없습니다", Toast.LENGTH_LONG).show();
                        else result=n1/n2; break;
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
