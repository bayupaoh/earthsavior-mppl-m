package id.or.codelabs.earthsavior.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Model.ModelBadges;
import id.or.codelabs.earthsavior.Model.ModelHome;
import id.or.codelabs.earthsavior.R;

/**
 * Created by CodeLabs on 10/03/2016.
 */
public class AdapterBadges extends RecyclerView.Adapter<AdapterBadges.ViewHolderBadges> {

    private ArrayList<ModelBadges> listBadges;
    private Context context;

    public AdapterBadges(ArrayList<ModelBadges> listBadges, Context context) {
        this.listBadges = listBadges;
        this.context = context;
    }

    @Override
    public ViewHolderBadges onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profil,parent,false);
        ViewHolderBadges view = new ViewHolderBadges(viewItem);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolderBadges holder, int position) {
        holder.imgBadges.setImageResource(listBadges.get(position).getImgBadges());
        holder.txtBadges.setText(listBadges.get(position).getNameReward());
    }

    @Override
    public int getItemCount() {
        return listBadges.size();
    }

    public class ViewHolderBadges extends RecyclerView.ViewHolder {

        ImageView imgBadges;
        TextView txtBadges;

        public ViewHolderBadges(View itemView) {
            super(itemView);
            imgBadges = (ImageView) itemView.findViewById(R.id.img_badges);
            txtBadges = (TextView) itemView.findViewById(R.id.text_badges);
        }

    }
}
