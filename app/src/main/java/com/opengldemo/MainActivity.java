package com.opengldemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//NAME: AKINDE-PETERS JOSEPH DARE
//MATRIC NUMBER: 110408007
//DEPARTMENT:	COMPUTER ENGINEERING
//COURSE:	EEG517

public class MainActivity extends AppCompatActivity {

    Context context;
    GLSurfaceView mGLView;
    GLRenderer renderer;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mGLView = (GLSurfaceView)findViewById(R.id.gView);
        button = (Button)findViewById(R.id.bttn);


        if(hasGLES20()){
            Toast.makeText(MainActivity.this, "OpenGL is supported on this Android device.", Toast.LENGTH_SHORT).show();
            renderer = new GLRenderer();
            mGLView.setEGLContextClientVersion(1);      //Set OpenGL Renderer client version
            mGLView.setPreserveEGLContextOnPause(true);
            mGLView.setRenderer(renderer);              //Apply OpenGL Renderer
        }else{
            //OpenGL is not supported on the Android smart phone
            Toast.makeText(MainActivity.this, "Unfortunately, OpenGL is not supported on this Android device.", Toast.LENGTH_SHORT).show();
        }

        //Button to make cube rotate faster
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                renderer.rotateFaster();
                mGLView.requestRender();
                return true;
            }
        });

    }


    private boolean hasGLES20() {

        //Check if Android smart phone supports OpenGL
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return info.reqGlEsVersion >= 0x20000;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_credit){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Credits")
                    .setMessage("NAME:  AKINDE-PETERS JOSEPH 'DARE\nMATRIC NUMBER:  110408007\nDEPARTMENT:  COMPUTER ENGINEERING\nCOURSE:  EEG517\n")
                    .create().show();
        }

        return super.onOptionsItemSelected(item);
    }

        @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }
}
