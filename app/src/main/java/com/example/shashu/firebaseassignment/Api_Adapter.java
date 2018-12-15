package com.example.shashu.firebaseassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Api_Adapter extends RecyclerView.Adapter<Api_Adapter.Api_ViewHolder> {

    Context context;
    ArrayList<ApiData> apiData;

    public Api_Adapter(Context context, ArrayList<ApiData> apiData) {
        this.context = context;
        this.apiData = apiData;
    }

    @NonNull
    @Override
    public Api_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.api_item, viewGroup, false);
        return new Api_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Api_ViewHolder api_viewHolder, int i) {
        ApiData data = apiData.get(i);
        api_viewHolder.txtId.setText(data.getId());
        api_viewHolder.txtUserId.setText(data.getUserId());
        api_viewHolder.txtTitle.setText(data.getTitle());
        api_viewHolder.txtBodyJson.setText(data.getBody());
        api_viewHolder.txtIdHeading.setText("ID:");
        api_viewHolder.txtUserIdHeading.setText("USERID");
        api_viewHolder.txtTitleHeading.setText("TITLE:");
        api_viewHolder.txtBodyJsonHeading.setText("BODY");
    }

    @Override
    public int getItemCount() {
        return apiData.size();
    }

    public class Api_ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtUserId;
        TextView txtTitle;
        TextView txtBodyJson;
        TextView txtIdHeading;
        TextView txtUserIdHeading;
        TextView txtTitleHeading;
        TextView txtBodyJsonHeading;

        public Api_ViewHolder(View view) {
            super(view);
            txtId = view.findViewById(R.id.txtId);
            txtUserId = view.findViewById(R.id.txtUserId);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtBodyJson = view.findViewById(R.id.txtBodyJson);
            txtIdHeading = view.findViewById(R.id.txtIdHeading);
            txtUserIdHeading = view.findViewById(R.id.txtUserIdHeading);
            txtTitleHeading = view.findViewById(R.id.txtTitleHeading);
            txtBodyJsonHeading = view.findViewById(R.id.txtBodyHeading);
        }
    }
}
