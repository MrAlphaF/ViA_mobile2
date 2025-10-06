package com.example.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGoToSecond, btnDialog;
    String[] members = {"Jānis Petrovs", "Linda Brante", "Arturs Silins"};
    boolean[] checkedMembers = new boolean[members.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToSecond = findViewById(R.id.btnGoToSecond);
        btnDialog = findViewById(R.id.btnDialog);

        // Navigate to second activity
        btnGoToSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Show dialog with checkboxes
        btnDialog.setOnClickListener(v -> showGroupDialog());
    }

    private void showGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("3 Group’s Dialog");

        // Add multi-choice checkboxes
        builder.setMultiChoiceItems(members, checkedMembers, (dialog, which, isChecked) -> {
            checkedMembers[which] = isChecked; // update state
            if (isChecked) {
                Toast.makeText(this, members[which] + " checked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, members[which] + " unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set dummy positive button; override later to prevent auto-close
        builder.setPositiveButton("OK", null);

        // Close button dismisses dialog
        builder.setNegativeButton("Close", (dialog, which) -> {
            Toast.makeText(this, "You closed dialog", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Override OK button to prevent auto-dismiss
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show();
            // Dialog stays open
        });
    }
}
