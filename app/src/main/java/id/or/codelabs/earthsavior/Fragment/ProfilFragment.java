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

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Adapter.AdapterBadges;
import id.or.codelabs.earthsavior.Adapter.AdapterHome;
import id.or.codelabs.earthsavior.Model.ModelBadges;
import id.or.codelabs.earthsavior.Model.ModelHome;
import id.or.codelabs.earthsavior.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelBadges> listBadges = new ArrayList<>();
    Context context;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_badges);

        setupBadges();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        AdapterBadges adapterHome = new AdapterBadges(listBadges,context);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void setupBadges() {
        listBadges.add(new ModelBadges(R.drawable.medal1,"Point 1000","Reward untuk point diatas 1000"));
        listBadges.add(new ModelBadges(R.drawable.medal2,"Jarak 50 KM","Total jarak lebih dari 50 KM"));
        listBadges.add(new ModelBadges(R.drawable.medal3,"Posisi 1","Reward posisi pertama"));
        listBadges.add(new ModelBadges(R.drawable.medal_black,"Reward Terkunci","Reward masih terkunci"));
        listBadges.add(new ModelBadges(R.drawable.medal_black,"Reward Terkunci","Reward masih terkunci"));
        listBadges.add(new ModelBadges(R.drawable.medal_black,"Reward Terkunci","Reward masih terkunci"));
        listBadges.add(new ModelBadges(R.drawable.medal_black,"Reward Terkunci","Reward masih terkunci"));
    }

}
