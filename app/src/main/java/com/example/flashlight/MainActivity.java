package com.example.flashlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Layout;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private LinearLayout buttonLayout;
    private Menu menu;
    //private String versionInfo;

    //OnCreate
    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateOptionsMenu(menu);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        buttonLayout =(LinearLayout)findViewById(R.id.LayoutButtons);

        final Button buttonWhite = findViewById(R.id.buttonWhite);
        final Button buttonBlack = findViewById(R.id.buttonBlack);
        final Button buttonRed = findViewById(R.id.buttonRed);
        final Button buttonYellow = findViewById(R.id.buttonYellow);
        final Button buttonGreen = findViewById(R.id. buttonGreen);

        if(savedInstanceState == null){
            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);
        }else{
            textView.setBackgroundColor(savedInstanceState.getInt("backgroundColor"));
            buttonLayout.setVisibility(savedInstanceState.getInt("visibilityButtons"));
            textView.setText(savedInstanceState.getCharSequence("visibilityText"));
        }

        //tap on background
        textView.setOnClickListener(this);
        //tab on buttons
        buttonWhite.setOnClickListener(this);
        buttonBlack.setOnClickListener(this);
        buttonRed.setOnClickListener(this);
        buttonYellow.setOnClickListener(this);
        buttonGreen.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /*
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        switch (item.getItemId()){
            case R. id.hidden:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("Hidden", null).create().show();
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.hidden:
                buttonLayout.setVisibility(View.INVISIBLE);
                return true;
            case R.id.visible:
                buttonLayout.setVisibility(View.VISIBLE);
                return true;
            case R.id.cancel:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    /**
     *  This callback is called only when there is a saved instance that is previously saved by using
     *  onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
     *  other state here, possibly usable after onStart() has completed.
     *  The savedInstanceState Bundle is same as the one used in onCreate().
     * @param savedInstanceState
     */
    public void onSaveInstanceState(Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);

        //get BackgroundColor
        ColorDrawable col= (ColorDrawable) textView.getBackground();
        //Type cast to int
        int color=col.getColor();

        //get visibility buttons
        int visButtons =  buttonLayout.getVisibility();

        //get visibility text
        CharSequence visText = textView.getText();

        savedInstanceState.putInt("backgroundColor",color);
        savedInstanceState.putInt("visibilityButtons",visButtons);
        savedInstanceState.putCharSequence("visibilityText",visText);
    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view) { //Callback
        switch (view.getId()){
            case R.id.buttonWhite:
                textView.setBackgroundColor(Color.WHITE);
                toggle(view);
                break;
            case R.id. buttonBlack:
                textView.setBackgroundColor(Color.BLACK);
                toggle(view);
                break;
            case R.id. buttonRed:
                textView.setBackgroundColor(Color.RED);
                toggle(view);
                break;
            case R.id. buttonYellow:
                textView.setBackgroundColor(Color.YELLOW);
                toggle(view);
                break;
            case R.id. buttonGreen:
                textView.setBackgroundColor(Color.GREEN);
                toggle(view);
                break;
            case R.id. textView:
                if(buttonLayout.getVisibility()==view.GONE){
                    buttonLayout.setVisibility(view.VISIBLE);
                    textView.setText(" ");
                }else{
                    buttonLayout.setVisibility(view.GONE);
                    textView.setText(" ");
                }

                break;
        }
        
    }



    /**
     *
     * @param view
     */
    private void toggle(View view){
        if(buttonLayout.getVisibility()==view.GONE){
            buttonLayout.setVisibility(view.VISIBLE);
        }else{
            buttonLayout.setVisibility(view.GONE);
        }
    }
    
    private String myVersionInfo(){
        String versionInfo;
        try{
            versionInfo = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        }catch (PackageManager.NameNotFoundException e){
            versionInfo = "not available!";
        }
        return versionInfo;
    }

}
