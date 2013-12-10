package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AlertTypeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_type);
        RadioGroup rg = (RadioGroup) findViewById(R.id.alertRadioGroup);
        
        System.out.println("extra: "+getIntent().getStringExtra("alert"));
        
        if(getIntent().getStringExtra("alert").equals(getResources().getString(R.string.vibration)))
        	rg.check(R.id.vibration);
        else if(getIntent().getStringExtra("alert").equals(getResources().getString(R.string.sound)))
        	rg.check(R.id.sound);
        else if(getIntent().getStringExtra("alert").equals(getResources().getString(R.string.vibration_and_sound)))
        	rg.check(R.id.vibration_and_sound);
    }    
    
    public void onRadioButtonClick(View v){
    	RadioGroup rg = (RadioGroup) findViewById(R.id.alertRadioGroup);
    	RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
    	Intent resultIntent = new Intent();
    	
    	resultIntent.putExtra("ALERT_TYPE", rb.getText()); 
    	setResult(Activity.RESULT_OK, resultIntent);
    	finish();
    }
}