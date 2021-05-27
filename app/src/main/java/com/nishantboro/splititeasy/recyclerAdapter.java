package com.nishantboro.splititeasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.InvestmentPla;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<InvestmentPla> investmentList;
    private RecyclerViewClickListener listener;
    public recyclerAdapter(ArrayList<InvestmentPla> investmentList, RecyclerViewClickListener listener){
        this.investmentList = investmentList;
        this.listener = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView planName;
        private TextView planDesc;
        public MyViewHolder(final View view){
            super(view);
            planName = view.findViewById(R.id.title);
            planDesc = view.findViewById(R.id.description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            listener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = investmentList.get(position).getPlanName();
        String desc = investmentList.get(position).getDescription();
        holder.planName.setText(name);
        holder.planDesc.setText(desc);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
