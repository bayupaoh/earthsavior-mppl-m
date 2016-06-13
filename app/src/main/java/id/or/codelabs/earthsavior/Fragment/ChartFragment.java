package id.or.codelabs.earthsavior.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Adapter.AdapterChart;
import id.or.codelabs.earthsavior.Model.ModelChart;
import id.or.codelabs.earthsavior.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    ArrayList<ModelChart> listChart = new ArrayList<>();
    View parentView;
    RecyclerView recyclerView;
    Context context;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_chart, container, false);
        setDataDummy();

        Toast.makeText(getContext(),"hai",Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerview_chart);

        AdapterChart adapterChart = new AdapterChart(listChart,getActivity().getBaseContext());
        recyclerView.setAdapter(adapterChart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return parentView;
    }

    private void setDataDummy() {
        listChart.add(new ModelChart("Bayu","http://img2-ak.lst.fm/i/u/arO/07feebc4eec84cd9b8b7b96bf61fa280",100));
        listChart.add(new ModelChart("Bayu","http://img2-ak.lst.fm/i/u/arO/07feebc4eec84cd9b8b7b96bf61fa280",100));
        listChart.add(new ModelChart("Bayu","http://img2-ak.lst.fm/i/u/arO/07feebc4eec84cd9b8b7b96bf61fa280",100));
        listChart.add(new ModelChart("Bayu","http://img2-ak.lst.fm/i/u/arO/07feebc4eec84cd9b8b7b96bf61fa280",100));
    }

}
