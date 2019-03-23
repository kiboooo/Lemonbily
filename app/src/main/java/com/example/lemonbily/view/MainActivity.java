package com.example.lemonbily.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lemonbily.R;
import com.example.lemonbily.view.ui.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
