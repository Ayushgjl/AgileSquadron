package com.example.agilesquadron.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesquadron.R;
import com.example.agilesquadron.api.CartApi;
import com.example.agilesquadron.model.CartDel;
import com.example.agilesquadron.ui.url.Url;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context mContext;
    List<CartDel> cartList;
    CartAdapter a = this;

    public CartAdapter(Context mContext,List<CartDel> cartList){
        this.mContext=mContext;
        this.cartList=cartList;
    }
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cart,parent,false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {

        final CartDel cart= cartList.get(position);
//        String imgPath= Url.imagePath + cart.getImage();
//        Log.e("Image path is","this is image" + imgPath);
        holder.Pname.setText(cart.getName());
        holder.price.setText(cart.getPrice());
        holder.Plocation.setText(cart.getLocation());

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CartApi api = Url.getInstance().create(CartApi.class);
                                final String aa = cart.get_id();
                                Call<CartDel> voidCall = api.deleteItems(Url.token, cart.get_id());

                                voidCall.enqueue(new Callback<CartDel>() {
                                    @Override
                                    public void onResponse(Call<CartDel> call, Response<CartDel> response) {
                                        Toast.makeText(mContext, "Item deleted", Toast.LENGTH_SHORT).show();
                                        cartList.remove(cart);
                                        a.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<CartDel> call, Throwable t) {
                                        Toast.makeText(mContext, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("cancel", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

//        StrictModeClass.StrictMode();
//        try{
//            URL url=new URL(imgPath);
//            holder.imageView2.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//        }
//
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        TextView Pname,price,Plocation;
        ImageView imageView2;
        Button Delete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView2=itemView.findViewById(R.id.imageView2);
            Pname=itemView.findViewById(R.id.Pname);
            price=itemView.findViewById(R.id.price);
            Plocation=itemView.findViewById(R.id.Plocation);
            Delete=itemView.findViewById(R.id.Delete);
        }
    }
}


