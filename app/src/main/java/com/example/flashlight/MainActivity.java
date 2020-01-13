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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


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

    //declare shared preference and editor
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    private boolean isFirstStart = true;

    //private Menu menu;
    //private String versionInfo;

    //OnCreate
    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        startFullscreen();
        super.onCreate(savedInstanceState);

        //set the View to the application
        setContentView(R.layout.activity_main);

        //init the layouts
        initLayouts();

        //tab on main screen
        textView.setOnClickListener(this);

        //init all Buttons and tab on buttons
        initButtons();

        //read shared preferences and save in editor
        pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        editor = pref.edit();

        if(pref.contains("firstStart")){
            //read out value if it was saved before
            isFirstStart =  pref.getBoolean("firstStart", true);
        }
        if(isFirstStart == true) {
            textView.setBackgroundColor(0);
            buttonLayout.setVisibility(View.VISIBLE);

            openColorDialog(context);
            openDialog(context);

            //safe in shared pref
            editor.putBoolean("firstStart",false);
            editor.commit();
            //reset the first start variable
            isFirstStart = false;
        }
        //textView.setBackgroundColor(Info aus shared pref);
        //buttonLayout.setVisibility(View.VISIBLE); //Info aus shared pref


        //On first start of the app
        if (savedInstanceState == null) {
            //set initial values on first start
            textView.setBackgroundColor(pref.getInt("background",0));
            buttonLayout.setVisibility(pref.getInt("visibility",0));
        } else {
            //read out all information stored
            textView.setBackgroundColor(savedInstanceState.getInt("backgroundColor"));      //get shared pref
            buttonLayout.setVisibility(savedInstanceState.getInt("visibilityButtons"));
            textView.setText(savedInstanceState.getCharSequence("visibilityText"));
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
    public void initLayouts(){
        //Init the Main Screen
        textView = findViewById(R.id.textView);
        //init buttonLayout
        buttonLayout =(LinearLayout)findViewById(R.id.LayoutButtons);
    }
    public void startFullscreen(){
        //start app without Title
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        //start app in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


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

        //save the values
        savedInstanceState.putInt("backgroundColor",color);
        editor.putInt("background",color);                              //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!save in open dialog function
        savedInstanceState.putInt("visibilityButtons",visButtons);
        //editor.putInt("visibility",visButtons);                         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!save in open dialog function
        savedInstanceState.putCharSequence("visibilityText",visText);
        editor.commit();
    }


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
                int visBu =  buttonLayout.getVisibility();
                editor.putInt("visibility", visBu);
                editor.commit();
                dialog.dismiss();
            }
        });
        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLayout.setVisibility(view.VISIBLE);
                int visBu =  buttonLayout.getVisibility();
                editor.putInt("visibility", visBu);
                editor.commit();
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
}
