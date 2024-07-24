package com.example.transpomateadmin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRouteNumber, editTextFrom, editTextTo;
    private Button buttonAddRoute;
    private DatabaseReference databaseRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRouteNumber = findViewById(R.id.editTextRouteNumber);
        editTextFrom = findViewById(R.id.editTextFrom);
        editTextTo = findViewById(R.id.editTextTo);
        buttonAddRoute = findViewById(R.id.buttonAddRoute);

        databaseRoutes = FirebaseDatabase.getInstance().getReference("routes");

        buttonAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoute();
            }
        });
    }

    private void addRoute() {
        String routeNumber = editTextRouteNumber.getText().toString().trim();
        String from = editTextFrom.getText().toString().trim();
        String to = editTextTo.getText().toString().trim();

        if (!TextUtils.isEmpty(routeNumber) && !TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
            String routeName = from + " to " + to;
            databaseRoutes.child(routeNumber).setValue(routeName);
            Toast.makeText(this, "Route added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
        }
    }
}
