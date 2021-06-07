package com.dyolmart.FindnextPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.dyolmart.AdpterClass.ImageAdapter;
import com.dyolmart.FragmentPages.Fragment_finddescription;
import com.dyolmart.FragmentPages.Fragment_findsepcification;
import com.dyolmart.HelperClass.products;
import com.dyolmart.Payment_Form;
import com.dyolmart.R;

public class Findnextpage_Accessories extends AppCompatActivity {
    ViewPager itemImage;
    TextView itemName,itemPrice,shopnumber,buynow,S1,S2,S3,S4,S5,S6,addcart,customernum;
    DatabaseReference databaseReference_accessories,databaseReference,databaseReference_wishlist;
    com.dyolmart.HelperClass.products products;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String branddesc1,brandfeature,Imageurl,Imageurl1,Imageurl2,Imageurl3,shopid,Rating1,Rating2,Rating3,Rating4,Rating5, value,item_size="null" ;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_details1 );
        itemImage =  findViewById( R.id.view_image);
        itemName = (TextView) findViewById( R.id.item_detail_name);
        itemPrice = (TextView) findViewById( R.id.item_detail_price);
        addcart=findViewById( R.id.text_addcart );
        customernum=findViewById( R.id.customermbl );
        shopnumber=findViewById( R.id.shopid );
        floatingActionButton=findViewById(R.id.add_to_wishlist_btn);
        S1 = findViewById( R.id.size1);
        S2 = findViewById( R.id.size2);
        S3 = findViewById( R.id.size3);
        S4 = findViewById( R.id.size4);
        S5= findViewById( R.id.size5);
        S6 = findViewById( R.id.size6);
        buynow=findViewById( R.id.text_buynow);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        addTabs(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabslyt);
        tabLayout.setupWithViewPager(viewPager);
        products= new products();
        databaseReference_accessories= FirebaseDatabase.getInstance().getReference().child("All_Products");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Customer_Details/Mycart");
        databaseReference_wishlist= FirebaseDatabase.getInstance().getReference().child("Customer_Details/Mywishlist");
        String name = getIntent().getStringExtra("");
        String price = getIntent().getStringExtra("price");
        String cient_shopnum=getIntent().getStringExtra( "Client_shopnumber" );
        String image=getIntent().getStringExtra( "imageurl" );
        itemName.setText( name);
        itemPrice.setText( price );
        shopnumber.setText( cient_shopnum );

        SharedPreferences sharedPreferences =getApplicationContext(). getSharedPreferences("myKey", MODE_PRIVATE);
        value = sharedPreferences.getString("value","");
        System.out.println("succfully printed"+value);

        getSupportActionBar().setTitle(R.string.Accessories);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#040f61"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customernum.setText( value );

        databaseReference_accessories.orderByChild( "Brand_Name" ).equalTo( name ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    com.dyolmart.HelperClass.products prdct=dataSnapshot.getValue(com.dyolmart.HelperClass.products.class);
                    shopid=prdct.getShop_ID();
                    branddesc1 = prdct.getProduct_Desc();
                    brandfeature = prdct.getProduct_feature();
                    Imageurl = prdct.getImg_url1();
                    Imageurl1 = prdct.getImg_url2();
                    Imageurl2 = prdct.getImg_url3();
                    Imageurl3 = prdct.getImg_url4();
                    Rating1 = prdct.getRating_1();
                    //System.out.println (Rating1 );
                    Rating2 = prdct.getRating_2();
                    Rating3 = prdct.getRating_3();
                    Rating4 = prdct.getRating_4();
                    Rating5 = prdct.getRating_2();
                    String image[]=new String[]{Imageurl,Imageurl1,Imageurl2,Imageurl3};
                    ImageAdapter imageAdapter=new ImageAdapter(getApplicationContext(),image);
                    itemImage.setAdapter(imageAdapter);
                    System.out.println("Succssfully brandfeature"+brandfeature );
                    SharedPreferences sharedPref = getSharedPreferences("Desc", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString( "value",branddesc1);
                    editor.putString( "value1",brandfeature);
                    editor.commit();

                    //Rating code

                    SharedPreferences Rating = getSharedPreferences("Ratingvalues", MODE_PRIVATE);
                    SharedPreferences.Editor rating = Rating.edit();
                    rating.putString( "Rating1",Rating1);
                    rating.putString( "Rating2",Rating2);
                    rating.putString( "Rating3",Rating3);
                    rating.putString( "Rating4",Rating4);
                    rating.putString( "Rating5",Rating5);
                    rating.commit();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                Intent intent = new Intent(getApplicationContext(), com.dyolmart.Payment_Form.class);
                                intent.putExtra( "name",name);
                                intent.putExtra("price", itemPrice.getText().toString());
                                intent.putExtra( "shopnumber",cient_shopnum);
                                intent.putExtra( "image",image );
                                intent.putExtra("itemSize",item_size);
                                startActivity(intent);
            }
        });
        addcart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getvalue();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(products.getShop_ID()+value+(products.getBrand_Name())).setValue(products);
                        Toast.makeText(Findnextpage_Accessories.this, "Add to Cart Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        } );
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
               floatingActionButton.setImageResource(R.drawable.ic_wishlist1);
                getvalue();
                databaseReference_wishlist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference_wishlist.child(products.getShop_ID()+(products.getBrand_Name())).setValue(products);
                        Toast.makeText(Findnextpage_Accessories.this, "Add to Wishlist", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    private void getvalue(){
        products.setBrand_Name(itemName.getText().toString());
        products.setCommon_price(itemPrice.getText().toString());
        products.setImg_url1(Imageurl);
        products.setImg_url2(Imageurl1);
        products.setImg_url3(Imageurl2);
        products.setImg_url4(Imageurl3);
        products.setProduct_Desc(branddesc1);
        products.setProduct_feature(brandfeature);
        products.setShop_ID(shopid);
        products.setCommonid(customernum.getText().toString());
    }

    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Fragment_finddescription(), "Description");
        adapter.addFrag(new Fragment_findsepcification(), "Specifications");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}