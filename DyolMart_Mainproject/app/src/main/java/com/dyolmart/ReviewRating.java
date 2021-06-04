package com.dyolmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dyolmart.AdpterClass.ImageAdapter;
import com.dyolmart.FragmentPages.Fragment_Myorder;
import com.dyolmart.HelperClass.products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class ReviewRating extends AppCompatActivity {
    RatingBar ratingBar;
    float ratevalue;
    TextView textview, txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    long name,A1,A2,A3,A4,A5,a,b,c,d,e;
    String string,nxt_Bname;
    TextView total,avg;
    Button btn;
    DatabaseReference databaseReference,databaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_reviewrating);
        ratingBar= findViewById(R.id.rating);
        getSupportActionBar().setTitle(R.string.rating);
        nxt_Bname=getIntent().getStringExtra("bname");
        textview = findViewById (R.id.textview);
        txt1 = findViewById (R.id.rating1);
        txt2 = findViewById (R.id.rating2);
        txt3 = findViewById (R.id.rating3);
        txt4 = findViewById (R.id.rating4);
        txt5 = findViewById (R.id.rating5);
        txt6 = findViewById (R.id.textView4);
        txt7 = findViewById (R.id.textView5);
        txt8 = findViewById (R.id.textView6);
        txt9= findViewById (R.id.textView7);
        txt10 = findViewById (R.id.textView8);
        btn= findViewById (R.id.btn_finish);
        total=findViewById (R.id.total_ratings);
        avg = findViewById (R.id.average_rating);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("All_Products");
        databaseref= FirebaseDatabase.getInstance().getReference().child("All_Products");
        databaseref.orderByChild( "Brand_Name" ).equalTo(nxt_Bname ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    com.dyolmart.HelperClass.products prdct=dataSnapshot.getValue( com.dyolmart.HelperClass.products.class);
                    String r1=prdct.getRating_1 ();
                    String r2=prdct.getRating_2 ();
                    String r3=prdct.getRating_3 ();
                    String r4=prdct.getRating_4 ();
                    String r5=prdct.getRating_5 ();
                    String Total=prdct.getTot_Rating ();
                    String average=prdct.getAverage ();
                    txt10.setText (r1);
                    System.out.println ("Ratingvalue"+r1 );
                    txt9.setText (r2);
                    System.out.println ("Ratingvalue"+r2);
                    txt8.setText (r3);
                    System.out.println ("Ratingvalue"+r3 );
                    txt7.setText (r4);
                    System.out.println ("Ratingvalue"+r4 );
                    txt6.setText (r5);
                    System.out.println ("Ratingvalue"+r5 );
                    total.setText (Total);
                    avg.setText (average);
                    A1= Long.parseLong (txt6.getText ().toString ());
                    A2= Long.parseLong (txt7.getText ().toString ());
                    A3= Long.parseLong (txt8.getText ().toString ());
                    A4= Long.parseLong (txt9.getText ().toString ());
                    A5= Long.parseLong (txt10.getText ().toString ());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        String sum= String.valueOf (Long.valueOf (A1)+Long.valueOf (A2)+Long.valueOf (A3)+Long.valueOf (A4)+Long.valueOf (A5));
        System.out.println ("sum"+sum );
        total.setText (sum+" Ratings");
        String avarage=String.valueOf (((Float.valueOf (A1)/(Integer.valueOf (sum))))*5+((Float.valueOf (A2)/(Integer.valueOf (sum))))*4+((Float.valueOf (A3)/(Integer.valueOf (sum))))*3+((Float.valueOf (A4)/(Integer.valueOf (sum))))*2+((Float.valueOf (A5)/(Integer.valueOf (sum))))*1);
        System.out.println ("avarage"+avarage);
        Float f=Float.parseFloat (avarage);
        avg.setText (new DecimalFormat("##.#").format(f));


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratevalue = ratingBar.getRating();
                name = Math.round(ratevalue);
                System.out.println ("print"+name );
                textview.setText( name+"");
                string = textview.getText ().toString ();
                switch (string){
                    case "5":
                             a=A1+1;
                            System.out.println ("added"+a);
                            txt6.setText (String.valueOf (a));
                            txt7.setText (String.valueOf (A2));
                            txt8.setText (String.valueOf (A3));
                            txt9.setText (String.valueOf (A4));
                            txt10.setText (String.valueOf (A5));
                        String sum5= String.valueOf (Long.valueOf (a)+Long.valueOf (A2)+Long.valueOf (A3)+Long.valueOf (A4)+Long.valueOf (A5));
                        System.out.println ("sum"+sum5 );
                        total.setText (sum5+" Ratings");
                        String avarage5=String.valueOf (((Float.valueOf (a)/(Integer.valueOf (sum5))))*5+((Float.valueOf (A2)/(Integer.valueOf (sum5))))*4+((Float.valueOf (A3)/(Integer.valueOf (sum5))))*3+((Float.valueOf (A4)/(Integer.valueOf (sum5))))*2+((Float.valueOf (A5)/(Integer.valueOf (sum5))))*1);
                        System.out.println ("avarage"+avarage5);
                        Float f5=Float.parseFloat (avarage5);
                        avg.setText (new DecimalFormat("##.#").format(f5));

                    break;
                    case "4":
                            b=A2+1;
                            System.out.println ("added"+b);
                            txt7.setText (String.valueOf (b));
                            txt6.setText (String.valueOf (A1));
                            txt8.setText (String.valueOf (A3));
                            txt9.setText (String.valueOf (A4));
                            txt10.setText (String.valueOf (A5));
                        String sum4= String.valueOf (Long.valueOf (A1)+Long.valueOf (b)+Long.valueOf (A3)+Long.valueOf (A4)+Long.valueOf (A5));
                        System.out.println ("sum"+sum4 );
                        total.setText (sum4+" Ratings");
                        String avarage4=String.valueOf (((Float.valueOf (A1)/(Integer.valueOf (sum4))))*5+((Float.valueOf (b)/(Integer.valueOf (sum4))))*4+((Float.valueOf (A3)/(Integer.valueOf (sum4))))*3+((Float.valueOf (A4)/(Integer.valueOf (sum4))))*2+((Float.valueOf (A5)/(Integer.valueOf (sum4))))*1);
                        System.out.println ("avarage"+avarage4);
                        Float f4=Float.parseFloat (avarage4);
                        avg.setText (new DecimalFormat("##.#").format(f4));
                        break;
                    case "3":
                        c=A3+1;
                        System.out.println ("added"+c);
                        txt7.setText (String.valueOf (A2));
                        txt6.setText (String.valueOf (A1));
                        txt8.setText (String.valueOf (c));
                        txt9.setText (String.valueOf (A4));
                        txt10.setText (String.valueOf (A5));
                        String sum3= String.valueOf (Long.valueOf (A1)+Long.valueOf (A2)+Long.valueOf (c)+Long.valueOf (A4)+Long.valueOf (A5));
                        System.out.println ("sum"+sum3 );
                        total.setText (sum3+" Ratings");
                        String avarage3=String.valueOf (((Float.valueOf (A1)/(Integer.valueOf (sum3))))*5+((Float.valueOf (A2)/(Integer.valueOf (sum3))))*4+((Float.valueOf (c)/(Integer.valueOf (sum3))))*3+((Float.valueOf (A4)/(Integer.valueOf (sum3))))*2+((Float.valueOf (A5)/(Integer.valueOf (sum3))))*1);
                        System.out.println ("avarage"+avarage3);
                        Float f3=Float.parseFloat (avarage3);
                        avg.setText (new DecimalFormat("##.#").format(f3));
                        break;
                    case "2":
                        d=A4+1;
                        System.out.println ("added"+d);
                        txt7.setText (String.valueOf (A2));
                        txt6.setText (String.valueOf (A1));
                        txt8.setText (String.valueOf (A3));
                        txt9.setText (String.valueOf (d));
                        txt10.setText (String.valueOf (A5));
                        String sum2= String.valueOf (Long.valueOf (A1)+Long.valueOf (A2)+Long.valueOf (A3)+Long.valueOf (d)+Long.valueOf (A5));
                        System.out.println ("sum"+sum2 );
                        total.setText (sum2+" Ratings");
                        String avarage2=String.valueOf (((Float.valueOf (A1)/(Integer.valueOf (sum2))))*5+((Float.valueOf (A2)/(Integer.valueOf (sum2))))*4+((Float.valueOf (A3)/(Integer.valueOf (sum2))))*3+((Float.valueOf (d)/(Integer.valueOf (sum2))))*2+((Float.valueOf (A5)/(Integer.valueOf (sum2))))*1);
                        System.out.println ("avarage"+avarage2);
                        Float f2=Float.parseFloat (avarage2);
                        avg.setText (new DecimalFormat("##.#").format(f2));
                        break;
                    case "1":
                        e=A5+1;
                        System.out.println ("added"+e);
                        txt7.setText (String.valueOf (A2));
                        txt6.setText (String.valueOf (A1));
                        txt8.setText (String.valueOf (A3));
                        txt9.setText (String.valueOf (A4));
                        txt10.setText (String.valueOf (e));
                        String sum1= String.valueOf (Long.valueOf (A1)+Long.valueOf (A2)+Long.valueOf (A3)+Long.valueOf (A4)+Long.valueOf (e));
                        System.out.println ("sum"+sum1 );
                        total.setText (sum1+" Ratings");
                        String avarage1=String.valueOf (((Float.valueOf (A1)/(Integer.valueOf (sum1))))*5+((Float.valueOf (A2)/(Integer.valueOf (sum1))))*4+((Float.valueOf (A3)/(Integer.valueOf (sum1))))*3+((Float.valueOf (A4)/(Integer.valueOf (sum1))))*2+((Float.valueOf (e)/(Integer.valueOf (sum1))))*1);
                        System.out.println ("avarage"+avarage1);
                        Float f1=Float.parseFloat (avarage1);
                        avg.setText (new DecimalFormat("##.#").format(f1));
                        break;
                }
                Toast.makeText (ReviewRating.this, "Thanks for your Rating", Toast.LENGTH_LONG).show ( );
             }
        });


        btn.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                databaseReference.child(nxt_Bname).child ("Rating_1").setValue (txt10.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Rating_2").setValue (txt9.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Rating_3").setValue (txt8.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Rating_4").setValue (txt7.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Rating_5").setValue (txt6.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Tot_Rating").setValue (total.getText ().toString ());
                databaseReference.child(nxt_Bname).child ("Average").setValue (avg.getText ().toString ());
                Intent intent=new Intent ( getApplicationContext (),com.dyolmart.FragmentPages.Fragment_Myorder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();

            }
        });

    }

}