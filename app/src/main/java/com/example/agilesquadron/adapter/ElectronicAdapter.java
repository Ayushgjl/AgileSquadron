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
import com.example.agilesquadron.model.Electronic;
import com.example.agilesquadron.strictmode.StrictModeClass;
import com.example.agilesquadron.ui.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ElectronicAdapter  extends RecyclerView.Adapter<ElectronicAdapter.ElectronicViewHolder> {

    Context mContext;
    List<Electronic> eList;

    public ElectronicAdapter(Context mContext, List<Electronic> eList) {
        this.mContext = mContext;
        this.eList = eList;
    }

    @NonNull
    @Override
    public ElectronicAdapter.ElectronicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_electronic, parent, false);

        return new ElectronicAdapter.ElectronicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectronicAdapter.ElectronicViewHolder holder, int position) {

        final Electronic electronic = eList.get(position);
        String imgPath = Url.imagePath + electronic.getImage();
        Log.e("Image path is ", "this is image" + imgPath);

        holder.tvname.setText(electronic.getName());
        holder.tvquantity.setText(electronic.getQuantity());
        holder.tvprice.setText(electronic.getPrice());

        StrictModeClass.StrictMode();
        try {
            URL url = new URL(imgPath);
            holder.card2.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
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
        return eList.size();
    }

    public class ElectronicViewHolder extends RecyclerView.ViewHolder {

        TextView tvname, tvquantity, tvprice;
        ImageView card2;

        public ElectronicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvquantity = itemView.findViewById(R.id.tvquantity);
            tvprice = itemView.findViewById(R.id.tvprice);
            card2 = itemView.findViewById(R.id.card2);

        }
    }

}