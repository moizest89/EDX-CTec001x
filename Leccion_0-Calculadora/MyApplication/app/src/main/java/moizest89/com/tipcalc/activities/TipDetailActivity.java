package moizest89.com.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import moizest89.com.tipcalc.R;
import moizest89.com.tipcalc.model.TipRecord;

public class TipDetailActivity extends AppCompatActivity {


    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;

    @Bind(R.id.txtTip)
    TextView txtTip;

    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    public final static String TIP_KEY = "tip";
    public final static String DATA_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";

    private TipRecord tipRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();

        if(intent != null) {
            String strTotal = String.format(getString(R.string.tipdetail_message_bill),
                    intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));

            String strTip = String.format(getString(R.string.global_message_tip),
                    intent.getDoubleExtra(TIP_KEY, 0d));

            this.txtTimeStamp.setText(intent.getStringExtra(DATA_KEY));
            this.txtTip.setText(strTip);
            this.txtBillTotal.setText(strTotal);
        }

    }

}
