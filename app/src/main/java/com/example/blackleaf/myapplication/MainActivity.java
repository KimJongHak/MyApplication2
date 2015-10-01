package com.example.blackleaf.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView text2;
    RadioGroup radioG1;
    RadioButton radio1, radio2, radio3;
    Button button1, button2;
    Switch switch1;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");
        text2=(TextView)findViewById(R.id.text2);
        switch1=(Switch)findViewById(R.id.switch1);
        radioG1=(RadioGroup)findViewById(R.id.radioG1);
        radio1=(RadioButton)findViewById(R.id.radio1);
        radio2=(RadioButton)findViewById(R.id.radio2);
        radio3=(RadioButton)findViewById(R.id.radio3);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        img=(ImageView)findViewById(R.id.img);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()==true){
                    text2.setVisibility(android.view.View.VISIBLE);
                    radioG1.setVisibility(android.view.View.VISIBLE);
                    img.setVisibility(android.view.View.VISIBLE);
                    button1.setVisibility(android.view.View.VISIBLE);
                    button2.setVisibility(android.view.View.VISIBLE);
                }
                else{
                    text2.setVisibility(android.view.View.INVISIBLE);
                    radioG1.setVisibility(android.view.View.INVISIBLE);
                    img.setVisibility(android.view.View.INVISIBLE);
                    button1.setVisibility(android.view.View.INVISIBLE);
                    button2.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch1.setChecked(false);
            }
        });
    }
    public void onClicked(View v) {
        switch(radioG1.getCheckedRadioButtonId()){
            case R.id.radio1:
                img.setImageResource(R.drawable.jellyfish);
                break;
            case R.id.radio2:
                img.setImageResource(R.drawable.koala);
                break;
            case R.id.radio3:
                img.setImageResource(R.drawable.penguins);
                break;
            default:
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
