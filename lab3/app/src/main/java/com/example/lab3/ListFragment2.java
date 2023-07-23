package com.example.lab3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager fragmentManager;
    private SensorManager mSensorManager;
    public ListFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment2 newInstance(String param1, String param2) {
        ListFragment2 fragment = new ListFragment2();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list2, container, false);
        fragmentManager = getParentFragmentManager();
        
        ListView lista = view.findViewById(R.id.listView1);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        
        lista.setAdapter(itemsAdapter);
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> slist = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor sensor : slist) {
            itemsAdapter.add(sensor.getName() + " " + sensor.getVendor() + " " + sensor.getVersion());
        }

        Button button1 = view.findViewById(R.id.powrot);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lista", "sensorow");
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,
                                BlankFragment.class, null).commit();
            }
        });
        return view;
    }
}