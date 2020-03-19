package max.go.webview;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        scrollView = findViewById(R.id.scrollview_history);
        arrayList = savedInstanceState.getStringArrayList("historyList");   //TODO: fix NullPointerException
        setTextHistory();
    }

    private void setTextHistory(){
        for (String str: arrayList) {
            TextView textView = new TextView(this);
            //add settings
            textView.setText(str);
            scrollView.addView(textView);
        }
    }

}
