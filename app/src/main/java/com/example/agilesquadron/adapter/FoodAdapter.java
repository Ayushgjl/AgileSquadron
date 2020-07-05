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
import com.example.agilesquadron.model.Food;
import com.example.agilesquadron.strictmode.StrictModeClass;
import com.example.agilesquadron.ui.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    Context mContext;
    List<Food> foodList;

    public FoodAdapter(Context mContext,List<Food> foodList){
        this.mContext=mContext;
        this.foodList=foodList;
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_food,parent,false);

        return new FoodAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {

        final Food food=foodList.get(position);
        String imgPath = Url.imagePath + food.getImage();
        Log.e("Image path is ", "this is image" + imgPath);

        holder.tvname.setText(food.getName());
        holder.tvquantity.setText(food.getQuantity());
        holder.tvprice.setText(food.getPrice());

        StrictModeClass.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.card1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }

        catch (IOException e)
        {
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
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        TextView tvname,tvquantity,tvprice;
        ImageView card1;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvquantity=itemView.findViewById(R.id.tvquantity);
            tvprice=itemView.findViewById(R.id.tvprice);
            card1=itemView.findViewById(R.id.card1);

        }
    }
}
