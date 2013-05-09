package com.example.progressdialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	
CharSequence[] items ={
			
			"Google",
			"Microsoft",
			"Apple"		
	   };
	
	boolean[] itemschecked =new boolean[items.length];
	
	private ProgressDialog progressDialog;
	private int progress=0;
	private Handler progressHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn =(Button) findViewById(R.id.btn_dialog);
		progressDialog= new ProgressDialog(this);
		btn.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v) {
				showDialog(1);
				progress=0;
				progressDialog.setProgress(0);
				progressHandler.sendEmptyMessage(0);
				
			}
		});
		
		
	
 progressHandler = new Handler() {
	 
	 public void handleMessage(Message msg){
		 super.handleMessage(msg);
		 if(progress>=100) {
			 
			 progressDialog.dismiss();
		 }else{
			 progress++;
			 progressDialog.incrementProgressBy(1);
			 progressHandler.sendEmptyMessageDelayed(0, 100);
		 }
		 
	 }
 };
}	

	@Override
	protected Dialog onCreateDialog(int id){
		switch(id){
		case 0:
			return new AlertDialog.Builder(this)
		    .create();
		
		
		case 1:
			progressDialog= new ProgressDialog(this);
			progressDialog.setIcon(R.drawable.icon);
			progressDialog.setTitle("Downloading Files...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Hide",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getBaseContext(), "Hide Clicked", Toast.LENGTH_SHORT).show();
					
				}
			});
			
			
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
					
				}
			});
           return progressDialog;    
		}
	return null;
 }
	
}
