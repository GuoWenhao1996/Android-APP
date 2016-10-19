package com.example.guowh.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import static com.example.guowh.myfirstapp.R.id.textView3;

public class MainActivity extends AppCompatActivity {

    //用于记录点了几次，偶数次为true，奇数次为false
    private boolean bool =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button btn = (Button) findViewById(R.id.button);//获取按钮资源
        btn.setOnClickListener(new View.OnClickListener(){//创建监听
            @Override
            public void onClick(View view) {
                if(bool==true) {
                    btn.setText("返回");
                    TextView tv=(TextView)findViewById(R.id.textView3);
                    tv.setVisibility(View.VISIBLE);
                    bool=false; //点了一次变成false
                }
                else {
                    btn.setText("点我有惊喜");
                    TextView tv=(TextView)findViewById(R.id.textView3);
                    tv.setVisibility(View.INVISIBLE);
                    bool=true;//点了一次变成true
                }
            }});

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
