package id.or.codelabs.earthsavior.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.or.codelabs.earthsavior.Model.ModelBadges;
import id.or.codelabs.earthsavior.Model.ModelChart;
import id.or.codelabs.earthsavior.R;

/**
 * Created by codelabsunikom on 6/13/16.
 */
public class AdapterChart extends RecyclerView.Adapter<AdapterChart.ViewHolderChart>{


    private ArrayList<ModelChart> listChart;
    private Context context;

    public AdapterChart(ArrayList<ModelChart> listChart, Context context) {
        this.listChart = listChart;
        this.context = context;
    }

    @Override
    public ViewHolderChart onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart,parent,false);
        ViewHolderChart view = new ViewHolderChart(viewItem);
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolderChart holder, int position) {
        holder.txtNomor.setText(String.valueOf(position+1));
        holder.txtNama.setText(listChart.get(position).getNamaUser());
        holder.txtPoint.setText(String.valueOf(listChart.get(position).getPoint()));
        Picasso.with(context).load(listChart.get(position).getUrlAvatar()).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return listChart.size();
    }

    public class ViewHolderChart extends RecyclerView.ViewHolder {

        TextView txtNomor;
        TextView txtNama;
        TextView txtPoint;
        ImageView avatar;
        public ViewHolderChart(View itemView) {
            super(itemView);
            txtNomor= (TextView) itemView.findViewById(R.id.txt_item_urutan);
            txtNama = (TextView) itemView.findViewById(R.id.txt_item_namauser);
            txtPoint = (TextView) itemView.findViewById(R.id.txt_item_pointuser);
            avatar = (ImageView) itemView.findViewById(R.id.img_item_avatarChart);
        }
    }
}
