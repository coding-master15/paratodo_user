package com.adapter.files;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.os.SystemClock;
import android.transition.TransitionManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.general.files.AppFunctions;
import com.paratodo.user.R;
import com.squareup.picasso.Picasso;
import com.utils.Logger;
import com.utils.Utils;
import com.view.MTextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceSearchAdapter extends RecyclerView.Adapter<ServiceSearchAdapter.ViewHolder> {

    ArrayList<HashMap<String, String>> serviceSearchArrList;
    Context mContext;
    OnItemClickList onItemClickList;

    public ServiceSearchAdapter(Context context, ArrayList<HashMap<String, String>> mapArrayList) {
        this.mContext = context;
        this.serviceSearchArrList = mapArrayList;
    }

    @Override
    public ServiceSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_services_design, parent,
                false);
        ServiceSearchAdapter.ViewHolder viewHolder = new ServiceSearchAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(ServiceSearchAdapter.ViewHolder holder, int position) {

        HashMap<String, String> mapData = serviceSearchArrList.get(position);

        holder.serviceText.setText(mapData.get("vCategory"));
        holder.subServiceText.setText(mapData.get("eServiceCategory"));


//        holder.viewArea.setOnClickListener(v -> onItemClickList.onItemClick(position));
        holder.searchAdapterView.setOnClickListener(v -> onItemClickList.onItemClick(position));


        String searchServiceImage = mapData.get("vLogo_image");
        if (searchServiceImage != null && !searchServiceImage.trim().equals("")) {
            Picasso.get()
                    .load(searchServiceImage)
                    .placeholder(R.mipmap.ic_no_icon)
                    .error(mContext.getResources().getDrawable(R.mipmap.ic_no_icon))
                    .into(holder.searchServiceImage);
        } else {
            holder.searchServiceImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_no_icon));
        }
    }

    @Override
    public int getItemCount() {
        return serviceSearchArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView searchServiceImage;
        MTextView serviceText;
        MTextView subServiceText;
        LinearLayout searchAdapterView;



        public ViewHolder(View itemView) {
            super(itemView);

            searchServiceImage = itemView.findViewById(R.id.searchServiceImage);
            serviceText = itemView.findViewById(R.id.serviceText);
            subServiceText = itemView.findViewById(R.id.subServiceText);
            searchAdapterView = itemView.findViewById(R.id.searchAdapterView);
        }
    }


    public void setOnItemClickList(OnItemClickList onItemClickList) {
        this.onItemClickList = onItemClickList;
    }

    public interface OnItemClickList {
        void onItemClick(int position);
    }
}
