package com.example.flashlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Layout;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private LinearLayout buttonLayout;
    private LinearLayout layoutTest;

    private Button buttonBlack, buttonWhite, buttonRed, buttonGreen, buttonYellow;
    private Button hidden, visible, cancel;

    final Context context = this;

    //private Menu menu;
    //private String versionInfo;

    //OnCreate
    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //menu-points for drop down menu
        //super.onCreateOptionsMenu(menu);
        setContentView(R.layout.activity_main);

        //Init the Main Screen
        textView = findViewById(R.id.textView);
        //tap on background
        textView.setOnClickListener(this);

        //init buttonLayout
        buttonLayout =(LinearLayout)findViewById(R.id.LayoutButtons);

        //init all Buttons and tab on buttons
        initButtons();

        //open dialog
        openDialog(context);

        //On first start of the app
        if (savedInstanceState == null) {
            //set initial values on first start
            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);
        } else {
            //read out all information stored
            textView.setBackgroundColor(savedInstanceState.getInt("backgroundColor"));
            buttonLayout.setVisibility(savedInstanceState.getInt("visibilityButtons"));
            textView.setText(savedInstanceState.getCharSequence("visibilityText"));
        }


        //layoutTest=(LinearLayout)findViewById(R.id.layouttest);
    }



    public void initButtons(){
        buttonWhite = findViewById(R.id.buttonWhite);
        buttonBlack = findViewById(R.id.buttonBlack);
        buttonRed = findViewById(R.id.buttonRed);
        buttonYellow = findViewById(R.id.buttonYellow);
        buttonGreen = findViewById(R.id. buttonGreen);

        buttonWhite.setOnClickListener(this);
        buttonBlack.setOnClickListener(this);
        buttonRed.setOnClickListener(this);
        buttonYellow.setOnClickListener(this);
        buttonGreen.setOnClickListener(this);

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
*/
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
    /*
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

     */



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

    public void openDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.menu);
        hidden = dialog.findViewById(R.id.hiddenButton);
        visible = dialog.findViewById(R.id.visibleButton);
        cancel = dialog.findViewById(R.id.cancelButton);
        hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.GONE);
                //setButtonVisibility(view.GONE);
                dialog.dismiss();
            }
        });
        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.VISIBLE);
                //setButtonVisibility(view.VISIBLE);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
