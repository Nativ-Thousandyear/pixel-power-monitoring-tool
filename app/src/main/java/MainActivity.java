import android.content.Context;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pixelpowermonitoringandanalysistool.R;

public class MainActivity extends AppCompatActivity {

    private BatteryManager batteryManager;
    private TextView powerConsumptionTextView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        powerConsumptionTextView = findViewById(R.id.powerConsumptionTextView);
        Button startButton = findViewById(R.id.startButton);
        handler = new Handler(Looper.getMainLooper());

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePowerConsumption();
            }
        });

        startMonitoring();
    }

    private void startMonitoring() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updatePowerConsumption();
                startMonitoring();
            }
        }, 1000); // Interval in milliseconds
    }

    private void updatePowerConsumption() {
        if (batteryManager != null) {
            int power = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
            powerConsumptionTextView.setText("Power Consumption: " + power);
        }
    }
}
