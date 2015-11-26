package com.example.blackleaf.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    Integer[] posterID = {R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4
            , R.drawable.movie5, R.drawable.movie6, R.drawable.movie7, R.drawable.movie8, R.drawable.movie9
            , R.drawable.movie10};
    String[] posterTitle = {"작업의 정석", "애자", "친절한 금자씨", "가발", "가문의 위기",
        "명량", "암살", "전우치", "무방비도시", "패션왕"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this, this);
        gv.setAdapter(gAdapter);
    }
    public class MyGridAdapter extends BaseAdapter {
        Context context;
        private Activity activity;

        public MyGridAdapter(Context c, Activity activity){
            context = c;
            this.activity = activity;
        }
        public int getCount(){
            return posterID.length;
        }
        public Object getItem(int a){
            return null;
        }
        public long getItemId(int a){
            return 0;
        }
        public class ViewHolder{
            public ImageView img;
            public TextView text;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder view;
            LayoutInflater inflator = activity.getLayoutInflater();
            if(convertView == null){
                view = new ViewHolder();
                convertView = inflator.inflate(R.layout.icon, null);

                view.text = (TextView) convertView.findViewById(R.id.iconTitle);
                view.img = (ImageView) convertView.findViewById(R.id.iconPoster);
                convertView.setTag(view);
            }
            else{
                view = (ViewHolder) convertView.getTag();
            }
            view.text.setText(posterTitle[position]);
            view.text.setTextSize(12);
            view.img.setImageResource(posterID[position]);
            convertView.setPadding(5, 5, 5, 5);
            final int pos = position;
            convertView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterTitle[pos]);
                    dlg.setIcon(R.drawable.ic_launch);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });
            return convertView;
            /*
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(250, 375));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(15, 15, 15, 0);
            imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterTitle[pos]);
                    dlg.setIcon(R.drawable.ic_launch);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageview;
            */
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
