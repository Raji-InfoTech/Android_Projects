package com.dyolmart.FragmentPages;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dyolmart.R;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_finddescription extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_finddescription, container, false);
        TextView textView=rootView.findViewById( R.id.Description );
        SharedPreferences sharedPreferences =getContext(). getSharedPreferences("Desc", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        textView.setText( value );
        return rootView;
    }

}

