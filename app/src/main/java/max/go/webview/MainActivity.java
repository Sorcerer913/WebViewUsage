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
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button googleButton;
    //PrintWriter fileWriter;
    //Scanner fileScanner;
    //private int currentHistoryLine = 0;
    //private int fabClickCounter = 0;
    //File fileHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*fileHistory = new File("./history/history.txt");
        try {
            fileWriter = new PrintWriter(fileHistory);
            fileScanner = new Scanner(fileHistory);
        } catch (FileNotFoundException e) {
            try {
                fileHistory.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
        //fileWriter.println("$" + System.currentTimeMillis());
        //making WebView
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        googleButton = findViewById(R.id.button_google);
        FloatingActionButton fab = findViewById(R.id.fab);
        googleButton.setOnClickListener(googleClickListener);
        fab.setOnClickListener(googleClickListener);
    }

    View.OnClickListener googleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            webView.loadUrl("https://google.com");
                /*if(fabClickCounter == 0) {
                    webView.loadUrl("https://google.com");
                }else{
                    Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                    startActivity(intent);
                }
                fabClickCounter++;

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        }
    };

    WebViewClient webViewClient = new WebViewClient(){
        @SuppressWarnings("deprecation") @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //fileWriter.append(url+"\n");        //ERROR
            //currentHistoryLine++;
            view.loadUrl(url);
            return true;
        }
        @TargetApi(Build.VERSION_CODES.N) @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            //fileWriter.append((url+"\n"));        //ERROR
            //currentHistoryLine++;
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
