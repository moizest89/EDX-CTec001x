package moizest89.com.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moizest89.com.tipcalc.R;
import moizest89.com.tipcalc.TipCalcApp;
import moizest89.com.tipcalc.fragments.TipHistoryListFragment;
import moizest89.com.tipcalc.fragments.TipHistoryListFragmentListener;
import moizest89.com.tipcalc.model.TipRecord;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Bind(R.id.edt_total)
    EditText edt_total;

    @Bind(R.id.edt_percentage)
    EditText edt_percentage;

    @Bind(R.id.txtTip)
    TextView txtTip;

    @Bind(R.id.btn_calculate)
    Button btn_calculate;
    @Bind(R.id.btn_increase)
    Button btn_increase;
    @Bind(R.id.btn_decrease)
    Button btn_decrease;





    private final static String TAG = MainActivity.class.getSimpleName();


    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE =1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        TipHistoryListFragment fragment =
                (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        this.fragmentListener = (TipHistoryListFragmentListener) fragment;

    }


    @OnClick(R.id.btn_calculate)
    public void handleClickSubmit(){
        hideKeyboard();

        String strInputTotal = edt_total.getText().toString().trim();

        if(! strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total*(tipPercentage/100d);

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strTip  = String.format(getString(R.string.global_message_tip), tipRecord.getTip());

            this.fragmentListener.addToList(tipRecord);
            this.txtTip.setText(strTip);
            this.txtTip.setVisibility(View.VISIBLE);

        }

    }


    @OnClick(R.id.btn_increase)
    public void handleClickIncrease(){
        hideKeyboard();

        handleTipChange(-(TIP_STEP_CHANGE));
    }

    @OnClick(R.id.btn_decrease)
    public void handleClickDecrease(){
        hideKeyboard();

        handleTipChange(TIP_STEP_CHANGE);
    }


    private void handleTipChange(int change){
        int tipPercentage = getTipPercentage();
        tipPercentage += change;

        if(tipPercentage > 0){
            this.edt_percentage.setText(String.valueOf(tipPercentage));
        }
    }

    public int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;

        String strInputTipPercentage = this.edt_percentage.getText().toString().trim();

        if(! strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            this.edt_percentage.setText(String.valueOf(tipPercentage));
        }

        return tipPercentage;
    }

    private void hideKeyboard(){
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        try{

            inputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }catch (NullPointerException e){
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }


    private void about(){
        TipCalcApp tipCalcApp = (TipCalcApp)getApplication();

        String strUrl = tipCalcApp.getAboutMe();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);

    }


}
