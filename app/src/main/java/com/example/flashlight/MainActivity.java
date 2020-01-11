package com.example.flashlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private LinearLayout buttonLayout;
    //private  LinearLayout popupLayout;

   // private View toDisplayInDialog = getLayoutInflater().inflate(R.layout.radiogroup, null);


    private Button buttonBlack, buttonWhite, buttonRed, buttonGreen, buttonYellow;
    private Button hidden, visible, cancel;
    private Button setColor, cancelRadio;

    final Context context = this;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    private boolean isFirstStart = true;

    private boolean isDialogOpen = false;
    private boolean isColorDialogOpen = false;

    //private Menu menu;
    //private String versionInfo;

    //OnCreate
    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Make this activity, full screen
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        //init popup
        //popupLayout = (LinearLayout)findViewById(R.id.PopupLayout) ;

        //init all Buttons and tab on buttons
        initButtons();

        //pref = getSharedPreferences("hidden",
        //        Context.MODE_PRIVATE);
        if(isFirstStart == true){

            openColorDialog(context);
            openDialog(context);
            //editor.putInt("firstStart",1);
            //editor.commit();

            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);

            isFirstStart = false;
          //  editor.putBoolean("first", false);


           /*
            if(savedInstanceState.getBoolean("popup")==false && pref.contains("hidden")==true && pref.contains("visible")==true) {
            }
            else {
                openColorDialog((context));
                openDialog(context);
            }
            isFirstStart = false;*/
        }
        else{
            textView.setBackgroundColor(savedInstanceState.getInt("backgroundColor"));
            buttonLayout.setVisibility(savedInstanceState.getInt("visibilityButtons"));
            textView.setText(savedInstanceState.getCharSequence("visibilityText"));
            boolean bool = pref.getBoolean("hidden",false);
        }
/*
        //On first start of the app
        if (savedInstanceState == null) {
            //set initial values on first start
            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);
            //popupLayout.setVisibility(View.VISIBLE);

            //openColorDialog((context));
            //openDialog(context);

        } else {
            //read out all information stored
            textView.setBackgroundColor(savedInstanceState.getInt("backgroundColor"));
            buttonLayout.setVisibility(savedInstanceState.getInt("visibilityButtons"));
            textView.setText(savedInstanceState.getCharSequence("visibilityText"));
            boolean bool = pref.getBoolean("hidden",false);



            //popupLayout.setVisibility(savedInstanceState.getInt("popup"));
            if(savedInstanceState.getBoolean("popup")==false && pref.contains("hidden")==true && pref.contains("visible")==true) {
            }
            else {
                openColorDialog((context));
                openDialog(context);
            }
            */

       // }
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

        //get the popup window
        boolean visPopup = isDialogOpen;


        //save the values
        savedInstanceState.putInt("backgroundColor",color);
        savedInstanceState.putInt("visibilityButtons",visButtons);
        savedInstanceState.putCharSequence("visibilityText",visText);
        savedInstanceState.putBoolean("popup",visPopup);
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
        isDialogOpen = true;
        hidden = dialog.findViewById(R.id.hiddenButton);
        visible = dialog.findViewById(R.id.visibleButton);
        cancel = dialog.findViewById(R.id.cancelButton);
        //final SharedPreferences.Editor editor = pref.edit();
        hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.GONE);
               // editor.putBoolean("hidden",true);
                //editor.commit();
                isDialogOpen = false;
                //Toast.makeText(this, "Start Option has saved.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.VISIBLE);
               // editor.putBoolean("visible",true);
               // editor.commit();
                isDialogOpen = false;
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

    public void openColorDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View toDisplayDialog = getLayoutInflater().inflate(R.layout.radiogroup, null);
        dialog.setContentView(toDisplayDialog);

        setColor = dialog.findViewById(R.id.setColorButton);
        cancelRadio = dialog.findViewById((R.id.cancelRadioButton));

        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioGroup myRadioGroup = (RadioGroup) toDisplayDialog.findViewById(R.id.radiobuttons);
                final int radioGroupId = myRadioGroup.getCheckedRadioButtonId();
                final RadioButton myCheckedButton = (RadioButton) toDisplayDialog.findViewById(radioGroupId);
                int index = myRadioGroup.indexOfChild(myCheckedButton);

                //editor.putInt("startupColor",index);
                //editor.commit();


                //pref = getSharedPreferences("startupColor",Context.MODE_PRIVATE);
                //myRadioGroup.getChildAt(pref.getInt("startupColor",-1));
                //myCheckedButton.setChecked (true);
                //index = pref.getInt("startupColor",-1);
                switch (index) {
                    case 0:
                        textView.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        textView.setBackgroundColor(Color.BLACK);
                        break;
                    case 2:
                        textView.setBackgroundColor(Color.RED);
                        break;
                    case 3:
                        textView.setBackgroundColor(Color.YELLOW);
                        toggle(view);
                        break;
                    case 4:
                        textView.setBackgroundColor(Color.GREEN);
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
        cancelRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
/*
    public void safeSharedPreference(){
        pref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = pref.edit();
        myEditor.clear();
        int anzCodeLines = myStringArray.length;
        for(int i = 0; i < anzCodeLines; i++){
            myEditor.putString("myCode"+i, myStringArray[i]);
        }
        myEditor.putEditor.putInt("anzCodeLines", anzCodeLines);
        myEditor.commit();
        Toast.makeText(this, "Start Option has saved.", Toast.LENGTH_SHORT).show();
    }
    //Von Moritz
    public void deleteSharedPreferences(){
        mySavePref = getSharedPreferences("MYPreferences", MODE_PRIVATE);
        myEditor.mySavePref.edit();
        myEditor.clear();
        myEditor.commit();
        Toast.makeText(this, "The saved Start Option was deletet.", Toast.LENGTH_SHORT).show();
    }

    public void safeSharedPreference(){
        myString = myText.getText().toString();
        myStringArray = myString.split(",");
        mySavePref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        myEditor = mySavedPref.edit();
//        myEditor.clear();
        int anzCodeLines = myStringArray.length;
        for(int i = 0; i < anzCodeLines; i++){
            myEditor.putString("myCode"+i, myStringArray[i]);
        }
        myEditor.putEditor.putInt("anzCodeLines", anzCodeLines);
        myEditor.commit();
        Toast.makeText(this, "Start Option has saved.", Toast.LENGTH_SHORT).show();
    }*/
}
