package com.example.blackleaf.webview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends Activity {
    EditText edtUrl;
    Button btnGo, btnBack;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.setTitle("간단 웹브라우저");

        edtUrl=(EditText)findViewById(R.id.edtUrl);
        btnGo=(Button)findViewById(R.id.btnGo);
        btnBack=(Button)findViewById(R.id.btnBack);
        webView=(WebView)findViewById(R.id.webView);

        webView.setWebViewClient(new CookWebViewClient());
        WebSettings webSet = webView.getSettings();

        btnGo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String output = edtUrl.getText().toString();
                if(!output.matches("^http://.*")){
                    output="http://"+output;
                }
                webView.loadUrl(output);
                hideSoftInputWindow(edtUrl, false);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                webView.goBack();
                hideSoftInputWindow(edtUrl, false);
            }
        });
    }
    class CookWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String Url){
            return false;
        }
        @Override
        public void onPageFinished(WebView view, String url){
            edtUrl.setText(url);
        }
    }
    public boolean hideSoftInputWindow(View v, boolean b){
        InputMethodManager keypad = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (b) {
            return keypad.showSoftInput(v ,0);
        }
        else {
            return keypad.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
