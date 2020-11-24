package com.aayushh.profilepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link categoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class categoriesFragment extends Fragment implements CategoryAdapter.ViewHolder.OnCategoryListener {

    CardView category;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String categoryHead[];
    List<CategoryModel> categories = new ArrayList<>();
    RecyclerView recyclerview;

    public categoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment categoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static categoriesFragment newInstance(String param1, String param2) {
        categoriesFragment fragment = new categoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment;


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categories, container, false);

       recyclerview =(RecyclerView) v.findViewById(R.id.categoryRecycler);

        categoryHead = getResources().getStringArray(R.array.categoryHead);

        for (int i = 0; i < categoryHead.length; i++) {
            categories.add(new CategoryModel(categoryHead[i]));
        }
        CategoryAdapter adapter = new CategoryAdapter(categories, getActivity(), this);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(adapter);


//        category = (CardView) v.findViewById(R.id.categoryView);
//        category.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new shopList();
//                loadFragment(fragment);
//            }
//        });

        return v;

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCategoryClick(int position) {
         Intent intent = new Intent(getActivity(), shopDetails.class);
         startActivity(intent);
    }
}