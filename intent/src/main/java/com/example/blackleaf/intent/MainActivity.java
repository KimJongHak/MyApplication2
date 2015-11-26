package com.example.blackleaf.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    int select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        Button btnNewActivity = (Button) findViewById(R.id.btnNewActivity);
        final RadioButton radioButton[] = {
                (RadioButton) findViewById(R.id.add),
                (RadioButton) findViewById(R.id.sub),
                (RadioButton) findViewById(R.id.mul),
                (RadioButton) findViewById(R.id.div)
        };
        radioButton[0].setChecked(true);

        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);

                for(int i=0 ; i<radioButton.length ; i++){
                    if(radioButton[i].isChecked()){
                        select=i;
                        break;
                    }
                }
                Intent intent = new Intent(getApplicationContext(), SecActivity.class);
                intent.putExtra("Sel",  select);
                intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                if(Integer.parseInt(edtNum2.getText().toString()) != 0) {
                    intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                    startActivityForResult(intent, 0);
                }
                else{
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            double result = data.getDoubleExtra("Res", 0);
            Toast.makeText(getApplicationContext(), "결과 :"+result, Toast.LENGTH_SHORT).show();
        }
    }
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
