package max.go.webview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HistoryActivity extends AppCompatActivity {
    TextView textView;
    Scanner fileScanner;
    File fileHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        textView = findViewById(R.id.textView_history);
        fileHistory = new File("./history/history.txt");
        try {
            fileScanner = new Scanner(fileHistory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setTextHistory();
    }

    private void setTextHistory(){
        while (fileScanner.hasNextLine()){
            textView.append(fileScanner.nextLine());
        }
    }

}
