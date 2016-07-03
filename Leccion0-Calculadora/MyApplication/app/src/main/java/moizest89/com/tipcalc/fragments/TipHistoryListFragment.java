package moizest89.com.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import moizest89.com.tipcalc.R;
import moizest89.com.tipcalc.activities.TipDetailActivity;
import moizest89.com.tipcalc.adapters.OnItemClickListener;
import moizest89.com.tipcalc.adapters.TipAdapter;
import moizest89.com.tipcalc.model.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener{


    public TipHistoryListFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;


    private TipAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_tip_history_list, container, false);

        ButterKnife.bind(this, view);

        this.initAdapter();
        this.initRecyclerView();

        return view;
    }


    private void initRecyclerView(){
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.adapter);
    }

    private void initAdapter(){
        if(this.adapter == null){
            this.adapter = new TipAdapter(this,getActivity().getApplicationContext());
        }
    }

    @Override
    public void addToList(TipRecord tipRecord) {
        this.adapter.add(tipRecord);
    }

    @Override
    public void clearList() {
        this.adapter.clear();
    }

    @Override
    public void onItemClickListener(TipRecord tipRecord) {

        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY,tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY,tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATA_KEY,tipRecord.getDateFormatted());
        startActivity(intent);

    }
}
