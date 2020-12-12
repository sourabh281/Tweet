package com.example.tweet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class customSuccessDialog extends AppCompatActivity {


    public void ShowSuccessDialog(Activity activity , int msg){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_custom_success_dialog);

        TextView textView = dialog.findViewById(R.id.txtSuccessDialogMsg);
        textView.setText(msg);

        Button button = dialog.findViewById(R.id.btnSuccessDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }
}