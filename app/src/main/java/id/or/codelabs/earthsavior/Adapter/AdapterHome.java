package id.or.codelabs.earthsavior.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Model.ModelHome;
import id.or.codelabs.earthsavior.R;

/**
 * Created by CodeLabs on 09/03/2016.
 */
public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome> {

    private ArrayList<ModelHome> listHome;
    private Context context;

    public AdapterHome(ArrayList<ModelHome> listHome,Context context) {
        this.listHome = listHome;
        this.context = context;

    }

    @Override
    public ViewHolderHome onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home,parent,false);
        ViewHolderHome view = new ViewHolderHome(viewItem);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolderHome holder, int position) {
        holder.icon.setImageResource(R.drawable.running);
        holder.txtTempat.setText(listHome.get(position).getTempat());
        holder.txtTanggal.setText(listHome.get(position).getTanggal());
        holder.txtPoint.setText("56");

    }

    @Override
    public int getItemCount() {
        return listHome.size();
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder {
        public  ImageView icon;
        public TextView txtTempat;
        public TextView txtTanggal;
        public TextView txtPoint;

        public ViewHolderHome(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon_activity);
            txtTempat = (TextView) itemView.findViewById(R.id.text_tempat);
            txtTanggal = (TextView) itemView.findViewById(R.id.text_tanggal);
            txtPoint = (TextView) itemView.findViewById(R.id.text_point);

        }
    }
}
