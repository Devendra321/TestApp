package app.my.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText height, weight;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        calculate = (Button) findViewById(R.id.calculate);




    }

    public void calculateArea(View view) {
        Intent i = new Intent(MainActivity.this,Result.class);
        i.putExtra("height",height.getText().toString());
        i.putExtra("width",weight.getText().toString());
        startActivity(i);
    }
}
