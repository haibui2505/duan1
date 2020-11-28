package com.example.myapplication.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.MainAdapter;
import com.example.myapplication.MainAdapter_Web;
import com.example.myapplication.MainModel;
import com.example.myapplication.MainModel_web;
import com.example.myapplication.R;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    SliderView sliderView;
    View view;
    private DashboardViewModel dashboardViewModel;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;
    RecyclerView recyclerView, recyclerView_Web;
    MainAdapter_Web mainAdapter_web;
    ArrayList<MainModel_web> mainModel_webs;

    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        abc03();
        abc04();
        ImageSlider imageSlider=view.findViewById(R.id.slider);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://mb.dkn.tv/wp-content/uploads/2019/07/bn-0-countryside_960_720-e1564450649488-700x366.jpg","1 Image"));
        slideModels.add(new SlideModel("https://kenh14cdn.com/thumb_w/640/2019/3/21/photo-1-1553155372658221231913-crop-15531565428491316949222.jpg","2 Image"));
        slideModels.add(new SlideModel("https://zshop.vn/images/link/63/1045.jpg?t=1474958283","3 Image"));
        slideModels.add(new SlideModel("https://nld.mediacdn.vn/2020/5/29/doi-hoa-tim-5-1590731334546464136746.jpg","4 Image"));
        imageSlider.setImageList(slideModels,true);
        imageSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


//        myRef.child("hai").child("thi").setValue("12345");
        return view;


    }

    private void abc04() {
        recyclerView_Web = view.findViewById(R.id.recyclerview_web);
        Integer[] langLogo = {
                R.drawable.anh1,
                R.drawable.anh2,
                R.drawable.anh3,
                R.drawable.anh4,
                R.drawable.anh5,
        };
        String[] langname = {
                "Sự phát triển thần kì",
                "Lịch sử phát triển công",
                "BẮT KỊP XU HƯỚNG VỚI",
                "SNEAKER LÀ GÌ? LIỆT KÊ",
                "GIÀY ADIDAS ALPHABOUNCE XENO",
                "1000 ĐÔI GIÀY ADIDAS EQT"
        };
        mainModel_webs = new ArrayList<>();
        for (int i = 0; i < langLogo.length; i++) {
            MainModel_web model_web = new MainModel_web(langLogo[i], langname[i] + "...");
            mainModel_webs.add(model_web);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Web.setLayoutManager(layoutManager);
        recyclerView_Web.setItemAnimator(new DefaultItemAnimator());


        mainAdapter_web = new MainAdapter_Web(getActivity(), mainModel_webs);
        recyclerView_Web.setAdapter(mainAdapter_web);
    }


    private void abc03() {
        recyclerView = view.findViewById(R.id.recyclerview);
        Integer[] langLogo = {
                R.drawable.anh1,
                R.drawable.anh2,
                R.drawable.anh3,
                R.drawable.anh4,
                R.drawable.anh5,

        };
        String[] langname = {
                "Bag", "Bear", "Shoe", "Bag", "Bear"
        };
        mainModels = new ArrayList<>();
        for (int i = 0; i < langLogo.length; i++) {
            MainModel model = new MainModel(langLogo[i], langname[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter = new MainAdapter(getActivity(), mainModels);
        recyclerView.setAdapter(mainAdapter);

    }
}