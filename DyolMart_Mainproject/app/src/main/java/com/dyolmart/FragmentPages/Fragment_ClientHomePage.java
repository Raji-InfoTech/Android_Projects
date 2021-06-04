package com.dyolmart.FragmentPages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dyolmart.R;

public class Fragment_ClientHomePage extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_clienthomepage, container, false);
        Button button=root.findViewById (R.id.btn_Clienthome);
        button.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                myweb (v);
            }
        });

        return root;
    }

    public void myweb(View view){
        openUrl("https://www.techsoftsoln.tech/");
    }
    public void openUrl(String url){
        Uri uri=Uri.parse (url);
        Intent intent=new Intent ( Intent.ACTION_VIEW,uri );
        startActivity (intent);
    }




}