package com.example.blackleaf.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView tvName, tvEmail;
    Button button;
    EditText digEditName, digEditEmail;
    TextView toastText;
    View dialogView, toastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        tvName = (TextView)findViewById(R.id.tvName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dig = new AlertDialog.Builder (MainActivity.this);
                dig.setTitle("사용자 정보 입력");
                dig.setIcon(R.drawable.star);
                dig.setView(dialogView);
                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        digEditName = (EditText) dialogView.findViewById(R.id.digEdit1);
                        digEditEmail = (EditText) dialogView.findViewById(R.id.digEdit2);
                        tvName.setText(digEditName.getText().toString());
                        tvEmail.setText(digEditEmail.getText().toString());
                    }
                });
                dig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1,
                                null);
                        toastText = (TextView) toastView.findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다.");
                        toast.setView(toastView);
                        toast.setGravity(0, (int)(Math.random()*1000)-500 , (int)(Math.random()*2000)-1000);
                        toast.show();
                    }
                });
                digEditName = (EditText) dialogView.findViewById(R.id.digEdit1);
                digEditEmail = (EditText) dialogView.findViewById(R.id.digEdit2);
                digEditName.setText(tvName.getText().toString());
                digEditEmail.setText(tvEmail.getText().toString());
                dig.show();
            }
        });
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
