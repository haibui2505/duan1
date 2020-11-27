package com.example.demo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

import com.example.demo.R;
=======
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.demo.Model.model_product;
import com.example.demo.adapter.adapter_product;

import java.util.ArrayList;
>>>>>>> hai

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_Shop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Shop extends Fragment {
<<<<<<< HEAD
=======
    private EditText edt_title, edt_address, edt_money, edt_kind, edt_ageof, edt_more;
    private Button btn;
    private DatabaseReference mReference;
    private ListView lv;
    private ArrayList<model_product> mList;
    private adapter_product adapter_product;
>>>>>>> hai

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_Shop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Shop.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Shop newInstance(String param1, String param2) {
        Frag_Shop fragment = new Frag_Shop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__shop, container, false);
    }
=======
        View view = inflater.inflate(R.layout.fragment_frag__shop, container, false);

//        Ánh xạ
        edt_title = view.findViewById(R.id.edt_title_pro);
        edt_address = view.findViewById(R.id.edt_address_pro);
        edt_ageof = view.findViewById(R.id.edt_ageofpet_pro);
        edt_money = view.findViewById(R.id.edt_money_pro);
        edt_more = view.findViewById(R.id.edt_more_pro);
        edt_kind = view.findViewById(R.id.edt_kind_pro);
        btn = view.findViewById(R.id.btn_add_pro);
        lv = view.findViewById(R.id.lv_product);

//        Adapter + listView
        mList = new ArrayList<>();
        adapter_product = new adapter_product(getActivity(), R.layout.list_item_prod, mList);
        lv.setAdapter(adapter_product);

//        Sự kiện onClick
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString().trim().toLowerCase();
                String address = edt_address.getText().toString().trim().toLowerCase();
                String ageofpet = edt_ageof.getText().toString().trim().toLowerCase();
                String money = edt_money.getText().toString().trim().toLowerCase();
                String more = edt_more.getText().toString().trim().toLowerCase();
                String kind = edt_kind.getText().toString().trim().toLowerCase();

                if (title.equals("") || address.equals("") || address.equals("") || ageofpet.equals("") || money.equals("") || more.equals("") || kind.equals("")) {
                    Toast.makeText(getActivity(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    mReference = FirebaseDatabase.getInstance().getReference();
                    model_product model_product = new model_product("", title, address, "baka", money, null, kind, ageofpet, more, "12/12/2012");
                    mReference.child("product").setValue(model_product);
                }
            }
        });
        return view;
    }

>>>>>>> hai
}