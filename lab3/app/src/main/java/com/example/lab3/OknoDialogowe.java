package com.example.lab3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OknoDialogowe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OknoDialogowe extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager fragmentManager;
    private String opcja = "0";
    SharedPreferences sharedPref;
    Context context;
    public OknoDialogowe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnkoDialogowe.
     */
    // TODO: Rename and change types and number of parameters
    public static OknoDialogowe newInstance(String param1, String param2) {
        OknoDialogowe fragment = new OknoDialogowe();
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
        View view = inflater.inflate(R.layout.fragment_onko_dialogowe, container, false);

        sharedPref = this.getActivity().getSharedPreferences("SP1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        opcja = sharedPref.getString("opcja", "0");

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radio_akce:
                        opcja = "1";
                        break;
                    case R.id.radio_baro:
                        opcja = "2";
                        break;
                    case R.id.radio_pole:
                        opcja = "3";
                        break;
                    case R.id.radio_termo:
                        opcja = "4";
                        Log.w("wybor", "4");
                        break;
                }
            }
        });

        switch(opcja) {
            case "1":
                radioGroup.check(R.id.radio_akce);
                break;
            case "2":
                radioGroup.check(R.id.radio_baro);
                break;
            case "3":
                radioGroup.check(R.id.radio_pole);
                break;
            case "4":
                radioGroup.check(R.id.radio_termo);
                break;
        }

        fragmentManager = getParentFragmentManager();

        Button button1 = view.findViewById(R.id.button_anuluj);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,
                                BlankFragment.class, null).commit();
            }
        });

        Button button2 = view.findViewById(R.id.button_zatwierdz);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("opcja", opcja);
                editor.commit();
                Log.w("commit", String.valueOf(opcja));
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,
                                BlankFragment.class, null).commit();
            }
        });

        return view;
    }
}