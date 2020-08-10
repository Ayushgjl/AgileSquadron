package com.example.agilesquadron.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesquadron.Main2Activity;
import com.example.agilesquadron.ProfileActivity;
import com.example.agilesquadron.R;
import com.example.agilesquadron.adapter.CartAdapter;
import com.example.agilesquadron.api.CartApi;
import com.example.agilesquadron.model.CartDel;
import com.example.agilesquadron.ui.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    RecyclerView recycle;
    ImageView imageShow;
    Button checkout;

    List<CartDel> cartList;
    CartAdapter CartAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recycle = root.findViewById(R.id.recycle);
        checkout = root.findViewById(R.id.checkout);
        cart();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Item Proceeded to delivery", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void cart() {
        cartList = new ArrayList<>();

        CartApi C = Url.getInstance().create(CartApi.class);
        Call<List<CartDel>> listCall = C.getCart();
        listCall.enqueue(new Callback<List<CartDel>>() {
            @Override
            public void onResponse(Call<List<CartDel>> call, Response<List<CartDel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<CartDel> cartList1 = response.body();
                CartAdapter = new CartAdapter(getContext(), cartList1);
                recycle.setAdapter(CartAdapter);
                recycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recycle.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<CartDel>> call, Throwable t) {

                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}