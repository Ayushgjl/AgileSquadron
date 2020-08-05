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
import com.example.agilesquadron.model.Clothe;
import com.example.agilesquadron.strictmode.StrictModeClass;
import com.example.agilesquadron.ui.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ClotheAdapter extends RecyclerView.Adapter<ClotheAdapter.ClotheViewHolder> {
    Context mContext;
    List<Clothe> cList;

    public ClotheAdapter(Context mContext, List<Clothe> cList) {
        this.mContext = mContext;
        this.cList = cList;
    }
    @NonNull
    @Override
    public ClotheAdapter.ClotheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_clothe, parent, false);

        return new ClotheAdapter.ClotheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClotheAdapter.ClotheViewHolder holder, int position) {
        final Clothe clothe = cList.get(position);
        String imgPath = Url.imagePath + clothe.getImage();
        Log.e("Image path is ", "this is image" + imgPath);

        holder.tvname.setText(clothe.getName());
        holder.tvquantity.setText(clothe.getQuantity());
        holder.tvprice.setText(clothe.getPrice());

        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.card3.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
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
        return cList.size();

    }


    public class ClotheViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvquantity, tvprice;
        ImageView card3;

        public ClotheViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvquantity = itemView.findViewById(R.id.tvquantity);
            tvprice = itemView.findViewById(R.id.tvprice);
            card3 = itemView.findViewById(R.id.card3);

        }
    }
}

