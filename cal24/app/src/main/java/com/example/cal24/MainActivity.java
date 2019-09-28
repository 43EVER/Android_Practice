package com.example.cal24;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> results;
    private ResultAdapter adapter;

    private Button btConfirm;
    private EditText etInput;
    private RecyclerView rvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConfirm = findViewById(R.id.bt_confirm);
        etInput = findViewById(R.id.et_input);
        rvResults = findViewById(R.id.rv_results);

        results = new ArrayList<>();
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultAdapter(results);
        rvResults.setAdapter(adapter);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] list = etInput.getText().toString().split(" ");
                if (list.length != 4) {
                    Toast.makeText(MainActivity.this, "输入错了呀", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Integer> nums = new ArrayList<>();
                for (int i = 0; i < 4; i++)
                    nums.add(Integer.valueOf(list[i]));

                new AsyncTask<Void, String, Void>() {

                    private List<String> outputString = new ArrayList<>();

                    private void cal(int n) {

                    }

                    @Override
                    protected void onProgressUpdate(String... values) {
                        super.onProgressUpdate(values);
                        results.add(values[0]);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        return null;
                    }

                }.execute();
            }
        });
    }
}
