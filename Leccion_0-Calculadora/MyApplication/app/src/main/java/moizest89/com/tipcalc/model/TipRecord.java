package moizest89.com.tipcalc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by @moizest89 in SV on 6/26/16.
 */
public class TipRecord {

    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public double getTip(){
        return bill*(tipPercentage / 100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return  simpleDateFormat.format(timestamp);
    }
}
