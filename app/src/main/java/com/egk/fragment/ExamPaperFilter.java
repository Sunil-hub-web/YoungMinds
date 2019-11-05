package com.egk.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.egk.egk.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExamPaperFilter extends Fragment {
    Spinner year,category;

//year
    HashMap<String, String> hashyear = new HashMap<String, String>();
    List<String> yearcategory = new ArrayList<String>();
    ArrayAdapter<String> yeardataAdapter;
    //category
    HashMap<String, String> hashypaper = new HashMap<String, String>();
    List<String> papercategory = new ArrayList<String>();
    ArrayAdapter<String> paperdataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exam_paper_filter, container, false);
        year = (Spinner) v.findViewById(R.id.year);
        category = (Spinner) v.findViewById(R.id.category);


//year
        yearcategory.add("2010");
        yearcategory.add("2011");
        yearcategory.add("2012");
        yearcategory.add("2013");
        yearcategory.add("2014");
        yearcategory.add("2015");
        yearcategory.add("2016");
        yearcategory.add("2017");
        yearcategory.add("2018");
        yearcategory.add("2019");
        yearcategory.add("2020");

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentyear = year.getItemAtPosition(year.getSelectedItemPosition()).toString();
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearcategory.add(0, "Select Year");
        yeardataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, yearcategory);
        yeardataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(yeardataAdapter);

        //category

        papercategory.add("Banking");
        papercategory.add("UPSC");
        papercategory.add("RRB");
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String exampaper = category.getItemAtPosition(category.getSelectedItemPosition()).toString();
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        papercategory.add(0, "Select Category");
        paperdataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, papercategory);
        paperdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(paperdataAdapter);

        return v;
}




}
