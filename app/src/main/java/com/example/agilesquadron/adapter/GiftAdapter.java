package com.example.agilesquadron.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesquadron.R;
import com.example.agilesquadron.model.Gift;
import com.example.agilesquadron.strictmode.StrictModeClass;
import com.example.agilesquadron.ui.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {

    Context mContext;
    List<Gift> gList;

    public GiftAdapter(Context mContext, List<Gift> gList) {
        this.mContext = mContext;
        this.gList = gList;
    }
    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_gift, parent, false);

        return new GiftAdapter.GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftViewHolder holder, int position) {
        final Gift gift = gList.get(position);
        String imgPath = Url.imagePath + gift.getImage();
        Log.e("Image path is ", "this is image" + imgPath);

        holder.tvname.setText(gift.getName());
        holder.tvquantity.setText(gift.getQuantity());
        holder.tvprice.setText(gift.getPrice());

        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.card4.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        holder.card1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mContext, BedCartActivity.class);
//                intent.putExtra("image",bed.getImage());
//                intent.putExtra("name",bed.getName());
//                intent.putExtra("location",bed.getLocation());
//                intent.putExtra("price",bed.getPrice());
//
//                mContext.startActivity(intent);
//            }
//        });
//


    }

    @Override
    public int getItemCount() {
        return gList.size();
    }

    public class GiftViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvquantity, tvprice;
        ImageView card4;

        public GiftViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvquantity = itemView.findViewById(R.id.tvquantity);
            tvprice = itemView.findViewById(R.id.tvprice);
            card4 = itemView.findViewById(R.id.card4);

        }

    }
}