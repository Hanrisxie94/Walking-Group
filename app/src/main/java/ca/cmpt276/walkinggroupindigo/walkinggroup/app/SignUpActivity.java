package ca.cmpt276.walkinggroupindigo.walkinggroup.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.cmpt276.walkinggroupindigo.walkinggroup.R;

public class SignUpActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}