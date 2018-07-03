package ca.cmpt276.walkinggroupindigo.walkinggroup.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.cmpt276.walkinggroupindigo.walkinggroup.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setupMapButton();
        setupMonitorButton();
        setupGroupButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOutButton:
                // TODO: Implement log out
                Toast.makeText(DashboardActivity.this, "Log Out is not ready yet", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setupGroupButton() {
        Button groupButton = (Button) findViewById(R.id.manage_group);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add group
                Toast.makeText(DashboardActivity.this, "Groups is not ready yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupMonitorButton() {
        Button monitorButton = (Button) findViewById(R.id.manage_monitoring);
        monitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add monitoring
                Toast.makeText(DashboardActivity.this, "Monitoring is not ready yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupMapButton() {
        Button mapButton = (Button) findViewById(R.id.viewMapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
