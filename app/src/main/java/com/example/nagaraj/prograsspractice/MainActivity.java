package com.example.nagaraj.prograsspractice;

import android.app.Dialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 Button btn;
    private ProgressDialog _prograssDialog;
    private int _prograss=0;
    private Handler _prograssHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(1);
                _prograss=1;
                _prograssDialog.setProgress(0);
                _prograssHandler.sendEmptyMessage(0);

            }
        });
        _prograssHandler=new Handler(){
            public void handleMessage(Message m){
                super.handleMessage(m);
                if(_prograss>=100){
                    _prograssDialog.dismiss();
                }else{
                    _prograss++;
                    _prograssDialog.incrementProgressBy(1);
                    _prograssHandler.sendEmptyMessageDelayed(0,100);
                }
            }
        };
    }
    protected Dialog onCreateDialog(int i){
        _prograssDialog=new ProgressDialog(this);
        _prograssDialog.setIcon(R.drawable.sathya);
        _prograssDialog.setTitle("Downloading Files");
        _prograssDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        _prograssDialog.setButton(DialogInterface.BUTTON_POSITIVE,"hide",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int whichButton){
                Toast.makeText(getBaseContext(),"Hide Clicked",Toast.LENGTH_SHORT).show();
            }
                }
        );
        _prograssDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"cancel",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                Toast.makeText(getBaseContext(),"cencel clicked",Toast.LENGTH_SHORT).show();
            }
        });
        return _prograssDialog;
    }
}
