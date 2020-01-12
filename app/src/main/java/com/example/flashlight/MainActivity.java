package com.example.flashlight;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private LinearLayout buttonLayout;
    //private  LinearLayout popupLayout;


    private Button buttonBlack, buttonWhite, buttonRed, buttonGreen, buttonYellow;
    private Button hidden, visible, cancel;

    final Context context = this;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    private boolean isDialogOpen = false;

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

        //init popup
        //popupLayout = (LinearLayout)findViewById(R.id.PopupLayout) ;

        //init all Buttons and tab on buttons
        initButtons();

        //open dialog
        //openDialog(context);
        //pref = getSharedPreferences("hidden",
          //      Context.MODE_PRIVATE);

        //On first start of the app
        if (savedInstanceState == null) {
            //set initial values on first start
            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);
            //popupLayout.setVisibility(View.VISIBLE);
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
                //openDialog(context);
            }


        }

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
/*
    public void openDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.menu);
        isDialogOpen = true;
        hidden = dialog.findViewById(R.id.hiddenButton);
        visible = dialog.findViewById(R.id.visibleButton);
        cancel = dialog.findViewById(R.id.cancelButton);
        final SharedPreferences.Editor editor = pref.edit();
        hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.GONE);
                editor.putBoolean("hidden",true);
                editor.commit();
                isDialogOpen = false;
                Toast.makeText(this, "Start Option has saved.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.VISIBLE);
                editor.putBoolean("visible",true);
                editor.commit();
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
