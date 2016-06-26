package moizest89.com.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import moizest89.com.tipcalc.R;
import moizest89.com.tipcalc.model.TipRecord;

/**
 * Created by @moizest89 in SV on 6/26/16.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.Holder>{

    private List<TipRecord> dataSet = new ArrayList<>();
    private Context context;

    public TipAdapter(List<TipRecord> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        TipRecord elemenet = this.dataSet.get(position);
        String strTip = String.format(
                            this.context.getString(R.string.global_message_tip),
                            elemenet.getTip());

        holder.txtConent.setText(strTip);

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }


    public void add(TipRecord tipRecord){
        this.dataSet.add(0,tipRecord);
        this.notifyDataSetChanged();
    }

    public void clear(){
        this.dataSet.clear();
        this.notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{

        @Bind(R.id.txtContent)
        TextView txtConent;

        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
