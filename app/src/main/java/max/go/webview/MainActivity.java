package max.go.webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button googleButton;
    private boolean fabFlag;
    LinkedList<String> linkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //making WebView
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        googleButton = findViewById(R.id.button_google);
        fabFlag = true;
        FloatingActionButton fab = findViewById(R.id.fab);
        googleButton.setOnClickListener(googleClickListener);
        fab.setOnClickListener(googleClickListener);
    }

    View.OnClickListener googleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(fabFlag) {
                String url = "https://google.com";
                linkedList.add(url);
                webView.loadUrl(url);
                fabFlag = false;
            }else{
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                Bundle instance = new Bundle();
                ArrayList<String> arrayList = new ArrayList<>(linkedList.size());
                arrayList.addAll(linkedList);
                instance.putStringArrayList("historyList", arrayList);
                startActivity(intent, instance);
            }

//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        }
    };

    WebViewClient webViewClient = new WebViewClient(){
        @SuppressWarnings("deprecation") @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            linkedList.add(url);
            view.loadUrl(url);
            return true;
        }
        @TargetApi(Build.VERSION_CODES.N) @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            linkedList.add(url);
            view.loadUrl(url);
            return true;
        }
//        private void pageHistorySaving(String url, boolean isNext){
//            if(isNext) {
//                if (true) {
//
//                }
//            }else{
//
//            }
//        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(getApplicationContext(), "Страница загружена!", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Toast.makeText(getApplicationContext(), "Начата загрузка страницы", Toast.LENGTH_SHORT)
                    .show();
        }
    };

    @Override
    public void onBackPressed() {
        //TODO: go to previous page
        super.onBackPressed();
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
