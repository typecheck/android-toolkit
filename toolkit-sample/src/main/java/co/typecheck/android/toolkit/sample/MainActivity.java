package co.typecheck.android.toolkit.sample;

import android.os.Bundle;

import co.typecheck.android.toolkit.app.LifecycleLoggingActivity;


public class MainActivity extends LifecycleLoggingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
