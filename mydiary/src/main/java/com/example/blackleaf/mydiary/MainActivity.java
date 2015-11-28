package com.example.blackleaf.mydiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {
    Button btnSave;
    TextView text;
    EditText edit;
    DatePicker datePicker;
    Calendar cal;
    RadioButton rbtn[];
    View dialogView, dialogView2;
    int cYear, cMonth, cDay, textSize;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.tv);
        edit = (EditText) findViewById(R.id.et);

        dialogView = (View) View.inflate(MainActivity.this, R.layout.date, null);
        dialogView2 = (View) View.inflate(MainActivity.this, R.layout.textsize, null);
        datePicker = (DatePicker) dialogView.findViewById(R.id.dp);
        RadioButton temp[] = {(RadioButton) dialogView2.findViewById(R.id.rbtn1),
                (RadioButton) dialogView2.findViewById(R.id.rbtn2), (RadioButton) dialogView2.findViewById(R.id.rbtn3)};
        rbtn = temp;
        rbtn[1].setChecked(true);
        textSize = 20;
        edit.setTextSize(textSize);
        text.setTextSize(textSize);
        btnSave.setTextSize(textSize);
        cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH)+1;
        cDay = cal.get(Calendar.DAY_OF_MONTH);
        text.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_"
                + Integer.toString(cDay) + ".txt";
        String read = readDiary(fileName);
        edit.setText(read);

        text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜 선택");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int w) {
                        String str = cYear + "년 " + cMonth + "월 " + cDay + "일";
                        text.setText(str);
                        Toast.makeText(MainActivity.this, str+"을 열었습니다.", Toast.LENGTH_SHORT).show();
                        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_"
                                + Integer.toString(cDay) + ".txt";
                        String read = readDiary(fileName);
                        edit.setText(read);
                    }
                });
                ViewGroup parent = (ViewGroup) dialogView.getParent();
                if(parent!=null)
                    parent.removeView(dialogView);
                dlg.show();

            }
        });
        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cYear = year;
                cMonth = monthOfYear;
                cDay = dayOfMonth;
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = edit.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장되었습니다.", Toast.LENGTH_LONG).show();
                } catch (IOException e) {

                }
            }
        });
    }
    void deleteDiary(String fName){
        Toast.makeText(MainActivity.this, fName+"이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        deleteFile(fName);
        edit.setText("");
    }
    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream infs;
        try {
            infs = openFileInput(fName);
            byte[] txt = new byte[1024];
            infs.read(txt);
            infs.close();
            diaryStr = (new String(txt)).trim();
        } catch (IOException e) {
            edit.setHint("일기 내용");
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "일기 삭제");
        menu.add(0, 1, 1, "글씨 크기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(item.getItemId()){
            case 0:
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
                dlg2.setTitle("일기 삭제 선택");
                dlg2.setMessage(cYear + "년 " + cMonth + "월 " + cDay + "일 일기를 삭제하시겠습니까?");
                dlg2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int w) {
                        deleteDiary(fileName);
                    }
                });
                dlg2.setNegativeButton("취소", null);
                dlg2.show();
                break;
            case 1:
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("글씨 크기 선택");
                dlg.setView(dialogView2);
                ViewGroup parent = (ViewGroup) dialogView2.getParent();
                if(parent!=null)
                    parent.removeView(dialogView2);
                dlg.setNegativeButton("닫기", null);
                dlg.show();
                for(int i=0 ; i<rbtn.length ; i++) {
                    rbtn[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (v == (View) rbtn[0]) {
                                textSize = 30;
                                rbtn[0].setChecked(true);
                            }
                            else if(v == (View) rbtn[1]) {
                                textSize = 20;
                                rbtn[1].setChecked(true);
                            }
                            else if(v == (View) rbtn[2]) {
                                textSize = 10;
                                rbtn[2].setChecked(true);
                            }
                            edit.setTextSize(textSize);
                            text.setTextSize(textSize);
                            btnSave.setTextSize(textSize);
                        }
                    });
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
