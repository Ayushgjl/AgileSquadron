package com.example.agilesquadron.ui.home;

import android.content.Intent;
import android.net.Uri;
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
import com.example.agilesquadron.adapter.ClotheAdapter;
import com.example.agilesquadron.adapter.ElectronicAdapter;
import com.example.agilesquadron.adapter.FoodAdapter;
import com.example.agilesquadron.adapter.GiftAdapter;
import com.example.agilesquadron.api.CApi;
import com.example.agilesquadron.api.EApi;
import com.example.agilesquadron.api.FoodApi;
import com.example.agilesquadron.api.GApi;
import com.example.agilesquadron.model.Clothe;
import com.example.agilesquadron.model.Electronic;
import com.example.agilesquadron.model.Food;
import com.example.agilesquadron.model.Gift;
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

    List<Clothe> cList;
    ClotheAdapter ClotheAdapter;

    List<Gift> gList;
    GiftAdapter GiftAdapter;



    private int[] mImages=new int[]{
            R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4
    };

    private String [] mImageTitle=new String[]{
            "food","electronic","clothes","gifts"
    };

    RecyclerView recyclerView_a, recyclerView_b, recyclerView_c, recyclerView_d;
    ImageView card1,card2,card3, card4, facebook,insta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_a=root.findViewById(R.id.recyclerView_a);
        recyclerView_b=root.findViewById(R.id.recyclerView_b);
        recyclerView_c=root.findViewById(R.id.recyclerView_c);
        recyclerView_d=root.findViewById(R.id.recyclerView_d);
        card1=root.findViewById(R.id.card1);
        card2=root.findViewById(R.id.card2);
        card3=root.findViewById(R.id.card3);
        card4=root.findViewById(R.id.card4);
        facebook=root.findViewById(R.id.facebook);
        insta=root.findViewById(R.id.insta);

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

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.facebook.com/");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.instagram.com/");
                Intent intent1= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent1);
            }
        });

        Food();
        Electronic();
//        Clothe();
//        Gift();

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

//    private void Clothe(){
//        cList = new ArrayList<>();
//
//        CApi F = Url.getInstance().create(CApi.class);
//        Call<List<Clothe>> listCall=F.getClothe();
//        listCall.enqueue(new Callback<List<Clothe>>() {
//            @Override
//            public void onResponse(Call<List<Clothe>> call, Response<List<Clothe>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
//                }
//
//                List<Clothe> cList1=response.body();
//                ClotheAdapter=new ClotheAdapter(getContext(),cList1);
//                recyclerView_c.setAdapter(ClotheAdapter);
//                recyclerView_c.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//                recyclerView_c.setHasFixedSize(true);
//            }
//
//            @Override
//            public void onFailure(Call<List<Clothe>> call, Throwable t) {
//
//                Log.d("Error Message", "Error" + t.getLocalizedMessage());
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

//    private void Gift(){
//        gList = new ArrayList<>();
//
//        GApi F = Url.getInstance().create(GApi.class);
//        Call<List<Gift>> listCall=F.getGift();
//        listCall.enqueue(new Callback<List<Gift>>() {
//            @Override
//            public void onResponse(Call<List<Gift>> call, Response<List<Gift>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
//                }
//
//                List<Gift> gList1=response.body();
//                GiftAdapter=new GiftAdapter(getContext(),gList1);
//                recyclerView_d.setAdapter(GiftAdapter);
//                recyclerView_d.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//                recyclerView_d.setHasFixedSize(true);
//            }
//
//            @Override
//            public void onFailure(Call<List<Gift>> call, Throwable t) {
//
//                Log.d("Error Message", "Error" + t.getLocalizedMessage());
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}