package com.itech.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itech.models.Appointement;
import com.itech.mybabygrowing.R;

import java.util.List;

/**
 * Created by oSunshine on 03/07/2015.
 */
public class RecyclerViewAppointementAdapter extends RecyclerView.Adapter<RecyclerViewAppointementAdapter.RecyclerViewAppointementItemHolder> {

    private final LayoutInflater inflater;
    private final List<Appointement> data;

    public RecyclerViewAppointementAdapter(Context context, List<Appointement> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAppointementItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.appointements_item, parent, false);
        RecyclerViewAppointementItemHolder recyclerViewAppointementItemHolder = new RecyclerViewAppointementItemHolder(view);

        return recyclerViewAppointementItemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAppointementItemHolder holder, int position) {
        Appointement appointement = data.get(position);
        holder.getDate().setText(appointement.getDate());
        holder.getHeure().setText(appointement.getHeure());
        holder.getIcon().setImageResource(appointement.getIcon());
        holder.getNom().setText(appointement.getNom());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewAppointementItemHolder extends RecyclerView.ViewHolder {
        public TextView getNom() {
            return nom;
        }

        public void setNom(TextView nom) {
            this.nom = nom;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        public TextView getHeure() {
            return heure;
        }

        public void setHeure(TextView heure) {
            this.heure = heure;
        }

        public ImageView getIcon() {
            return icon;
        }

        public void setIcon(ImageView icon) {
            this.icon = icon;
        }

        TextView nom, date, heure;
        ImageView icon;

        public RecyclerViewAppointementItemHolder(View itemView) {

            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            date = (TextView) itemView.findViewById(R.id.date);
            heure = (TextView) itemView.findViewById(R.id.heure);
            icon = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Appointement item) {
        data.add(item);
        notifyItemInserted(data.size()-1);
    }
}
