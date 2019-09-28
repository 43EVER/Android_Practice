package com.example.cal24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    List<String> mData;

    public ResultAdapter(List<String> list) {
        this.mData = list;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.setResult(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class ResultViewHolder extends RecyclerView.ViewHolder {

    TextView tvResult;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        tvResult = itemView.findViewById(R.id.tv_result);
    }

    public void setResult(String str) {
        tvResult.setText(str);
    }

}
