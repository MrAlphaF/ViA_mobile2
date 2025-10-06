package com.example.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGoToSecond, btnDialog;
    String[] members = {"Alice Johnson", "Bob Smith", "Charlie Brown"};
    boolean[] checkedMembers = new boolean[members.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToSecond = findViewById(R.id.btnGoToSecond);
        btnDialog = findViewById(R.id.btnDialog);

        // Go to second activity
        btnGoToSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Show dialog
        btnDialog.setOnClickListener(v -> showGroupDialog());
    }

    private void showGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("3 Group’s Dialog");

        builder.setMultiChoiceItems(members, checkedMembers, (dialog, which, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, members[which] + " checked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, members[which] + " unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show();
            // Dialog not closed automatically here
            // So we need to reopen it manually to keep it visible
            ((AlertDialog) dialog).show();
        });

        builder.setNegativeButton("Close", (dialog, which) -> {
            Toast.makeText(this, "You closed dialog", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        builder.create().show();
    }
}
