package com.example.blackleaf.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecActivity extends Activity {
    int select;
    double value;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        setTitle("두번째 액티비티");

        Intent inIntent = getIntent();
        select = inIntent.getIntExtra("Sel", 0);
        switch(select) {
            case 0 : value = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0); break;
            case 1 : value = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0); break;
            case 2 : value = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0); break;
            case 3 : value = (double) inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0); break;
        }

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Res", value);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sec, menu);
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
