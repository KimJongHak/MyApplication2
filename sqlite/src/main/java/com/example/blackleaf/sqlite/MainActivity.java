package com.example.blackleaf.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    myDBHelper myHelper;
    EditText edtName, edtNum, edtNameRes, edtNumRes;
    Button btnInit, btnInput, btnUpdate, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.et1);
        edtNum = (EditText) findViewById(R.id.et2);
        edtNameRes = (EditText) findViewById(R.id.et3);
        edtNumRes = (EditText) findViewById(R.id.et4);
        btnInit = (Button) findViewById(R.id.btn1);
        btnInput = (Button) findViewById(R.id.btn2);
        btnUpdate = (Button) findViewById(R.id.btn3);
        btnDelete = (Button) findViewById(R.id.btn4);
        btnSelect = (Button) findViewById(R.id.btn5);

        myHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                + edtName.getText().toString() + "' , "
                + edtNum.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력 완료", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber="
                        + edtNum.getText().toString() + " WHERE gName=\""
                        + edtName.getText().toString() + "\";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName=\""
                        + edtName.getText().toString() + "\";");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);
                String strNames = "그룹 이름" + "\r\n" + "--------------" + "\r\n";
                String strNumbers = "그룹 인원" + "\r\n" + "--------------" + "\r\n";
                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }
                edtNameRes.setText(strNames);
                edtNumRes.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });
        btnSelect.callOnClick();
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
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
