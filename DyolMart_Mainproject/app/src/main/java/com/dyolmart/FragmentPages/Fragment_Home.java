
package com.dyolmart.FragmentPages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dyolmart.Searchbar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.dyolmart.AdpterClass.CustomAdapter;
import com.dyolmart.AdpterClass.ImageAdapter;
import com.dyolmart.AllCatalogue.Home_BannerShops;
import com.dyolmart.HelperClass.UserProfile;
import com.dyolmart.HelperClass.products;
import com.dyolmart.R;
import com.dyolmart.ViewHoler.Homescreenholder1;

public class Fragment_Home extends Fragment {

    ArrayList<UserProfile> arrayList;
    RecyclerView recyclerView;
    DotsIndicator dotsIndicator;
    SearchView searchView;

    int icons[] = {R.mipmap.accessories, R.mipmap.beauty, R.mipmap.books, R.mipmap.fashion, R.mipmap.electronic, R.mipmap.mobile, R.mipmap.footwear, R.mipmap.watches, R.mipmap.sports,R.mipmap.spareparts};
    String iconsName[] = {"Accessories", "Beauty", "Books", "Fashion", "Laptops", "Mobiles","Footwear","Watches","Sports","SpareParts"};

    //image banner
        ViewPager viewPager;
        DatabaseReference databaseReference;
        int currentPage = 0;
        int num = 0;
        Timer timer;
        final long DELAY_MS = 500;
        final long PERIOD_MS = 3000;
        private int NUM_PAGES=5;

       //home product
        Homescreenholder1 viewholder1,viewholder2,viewholder3;
        DatabaseReference productsref1,productsref2,productsref3;
        RecyclerView recyclerViewshop1,recyclerViewshop2,recyclerViewshop3;
        Button btnshop1,btnshop2,btnshop3;
        RecyclerView.LayoutManager layoutManager,layoutManager2,layoutManager3;
        String shopid1,shopid2,shopid3;


        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate( R.layout.fragment_home, container, false);


        //Recyclerview coding

            recyclerView = (RecyclerView) root.findViewById( R.id.Rcyview);
            arrayList = new ArrayList<>();
            recyclerView.setLayoutManager(new LinearLayoutManager (getActivity(), LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setItemAnimator(new DefaultItemAnimator ());

            for (int i = 0; i < icons.length; i++) {
                UserProfile itemModel = new UserProfile();
                itemModel.setImage(icons[i]);
                itemModel.setHtname(iconsName[i]);
                arrayList.add(itemModel);
        }

        CustomAdapter adapter = new CustomAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        // ViewFlipper coding

            viewPager =root.findViewById( R.id.vflipper);
            databaseReference= FirebaseDatabase.getInstance().getReference().child("Banner/Image");
            databaseReference.addValueEventListener( new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    com.dyolmart.HelperClass.Helper help =snapshot.getValue(com.dyolmart.HelperClass.Helper.class );
                    String Image1 = help.getImg1();
                    String Image2 = help.getImg2();
                    String Image3 = help.getImg3();
                    String Image4 = help.getImg4();
                    String images[]=new String[]{Image1,Image2,Image3,Image4};
                    System.out.println(Image1);
                    ImageAdapter imageAdapter=new ImageAdapter(getContext(), images );
                    viewPager.setAdapter(imageAdapter);

                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == NUM_PAGES-1) {
                                currentPage=0;
                            }
                            viewPager.setCurrentItem(currentPage++, true);
                        }
                    };

                    timer = new Timer(); // This will create a new Thread
                    timer.schedule(new TimerTask() { // task to be scheduled
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, DELAY_MS, PERIOD_MS);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            } );

        //Homescreen banner product

            recyclerViewshop1=root.findViewById (R.id.Recyshop1);
            productsref1 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Home_product/Table_1");
            productsref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        com.dyolmart.HelperClass.products prdct = dataSnapshot.getValue(com.dyolmart.HelperClass.products.class);
                        shopid1 = prdct.getShop_ID();
                        System.out.println("print shopid"+shopid1);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            layoutManager = new GridLayoutManager (getContext(),2);
            recyclerViewshop1.setLayoutManager(layoutManager);
            btnshop1=root.findViewById (R.id.viewallshop1) ;
            FirebaseRecyclerOptions<products> options =new FirebaseRecyclerOptions.Builder<products>().setQuery(productsref1,products.class).build();
            viewholder1 = new Homescreenholder1 (options);
            recyclerViewshop1.setAdapter(viewholder1);

            recyclerViewshop2=root.findViewById (R.id.Recyshop2);
            productsref2 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Home_product/Table_2");
            productsref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        com.dyolmart.HelperClass.products prdct = dataSnapshot.getValue(com.dyolmart.HelperClass.products.class);
                        shopid2 = prdct.getShop_ID();
                        System.out.println("print shopid"+shopid1);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            layoutManager2 = new GridLayoutManager (getContext(),2);
            recyclerViewshop2.setLayoutManager(layoutManager2);
            btnshop2=root.findViewById (R.id.viewallshop2);
            FirebaseRecyclerOptions<products> options1=new FirebaseRecyclerOptions.Builder<products>().setQuery(productsref2,products.class).build();
            viewholder2=new Homescreenholder1(options1);
            recyclerViewshop2.setAdapter(viewholder2);

            recyclerViewshop3=root.findViewById (R.id.Recyshop3);
            productsref3 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Home_product/Table_1");
            productsref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                        com.dyolmart.HelperClass.products prdct = dataSnapshot.getValue(com.dyolmart.HelperClass.products.class);
                        shopid3 = prdct.getShop_ID();
                        System.out.println("print shopid"+shopid1);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            layoutManager3 = new GridLayoutManager (getContext(),2);
            recyclerViewshop3.setLayoutManager(layoutManager3);
            btnshop3=root.findViewById (R.id.viewallshop3);
            FirebaseRecyclerOptions<products> options2=new FirebaseRecyclerOptions.Builder<products>().setQuery(productsref2,products.class).build();
            viewholder3=new Homescreenholder1(options2);
            recyclerViewshop3.setAdapter(viewholder3);

            btnshop1.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent ( getContext (), Home_BannerShops.class);
                    intent.putExtra("shopsid",shopid1);
                    startActivity (intent);
                }
            });
            btnshop2.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent ( getContext (), Home_BannerShops.class);
                    intent.putExtra("shopsid",shopid2);
                    startActivity (intent);
                }
            });
            btnshop3.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent ( getContext (), Home_BannerShops.class);
                    intent.putExtra("shopsid",shopid3);
                    startActivity (intent);
                }
            });


         // search Bar coding

            searchView=root.findViewById (R.id.search);
            searchView.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent ( getContext(),Searchbar.class );
                    startActivity (intent);
                }
            });



            return root;
    }

    @Override
    public void onStart(){
        super.onStart();
        viewholder1.startListening();
        viewholder2.startListening();
        viewholder3.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        viewholder1.stopListening();
        viewholder2.stopListening();
        viewholder3.stopListening();

    }

}






