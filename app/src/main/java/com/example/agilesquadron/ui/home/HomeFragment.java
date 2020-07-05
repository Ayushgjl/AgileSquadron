package com.example.agilesquadron.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.agilesquadron.R;
import com.example.agilesquadron.adapter.ElectronicAdapter;
import com.example.agilesquadron.adapter.FoodAdapter;
import com.example.agilesquadron.api.EApi;
import com.example.agilesquadron.api.FoodApi;
import com.example.agilesquadron.model.Electronic;
import com.example.agilesquadron.model.Food;
import com.example.agilesquadron.ui.url.Url;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<Food> foodList;
    FoodAdapter FoodAdapter;

    List<Electronic> eList;
    ElectronicAdapter ElectronicAdapter;

    private int[] mImages=new int[]{
            R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4
    };

    private String [] mImageTitle=new String[]{
            "food","electronic","clothes","gifts"
    };

    RecyclerView recyclerView_a, recyclerView_b;
    ImageView card1,card2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_a=root.findViewById(R.id.recyclerView_a);
        recyclerView_b=root.findViewById(R.id.recyclerView_b);
        card1=root.findViewById(R.id.card1);
        card2=root.findViewById(R.id.card2);

        CarouselView carouselView;
        carouselView=root.findViewById(R.id.caral);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), mImageTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        Food();
        Electronic();
        return root;
    }

    private void Food(){
        foodList = new ArrayList<>();

        FoodApi F = Url.getInstance().create(FoodApi.class);
        Call<List<Food>> listCall=F.getFood();
        listCall.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Food> foodList1=response.body();
                FoodAdapter=new FoodAdapter(getContext(),foodList1);
                recyclerView_a.setAdapter(FoodAdapter);
                recyclerView_a.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView_a.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private  void Electronic(){
        eList = new ArrayList<>();

        EApi E = Url.getInstance().create(EApi.class);
        Call<List<Electronic>> listCall=E.getElectronic();
        listCall.enqueue(new Callback<List<Electronic>>() {
            @Override
            public void onResponse(Call<List<Electronic>> call, Response<List<Electronic>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Electronic> eList1=response.body();
                ElectronicAdapter=new ElectronicAdapter(getContext(),eList1);
                recyclerView_b.setAdapter(ElectronicAdapter);
                recyclerView_b.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView_b.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Electronic>> call, Throwable t) {

                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}