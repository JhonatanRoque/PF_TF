package com.fjar.transporfast.ui.empresa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjar.transporfast.MainActivity;
import com.fjar.transporfast.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class rutas extends Fragment {

    TextView tvRutas;
    boolean [] selectedRuta;
    ArrayList<Integer> rutaList = new ArrayList<>();
    String[] rutaArray = {"3LP", "4LP", "5LP"};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rutas() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static rutas newInstance(String param1, String param2) {
        rutas fragment = new rutas();
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
        View root = inflater.inflate(R.layout.fragment_rutas, container, false);
        tvRutas = root.findViewById(R.id.tv_rutas);

        selectedRuta = new boolean[rutaArray.length];

        tvRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getContext()
                );

                builder.setTitle("Selected Ruta");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(rutaArray, selectedRuta, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            rutaList.add(i);

                            Collections.sort(rutaList);
                        }else {
                            rutaList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<rutaList.size(); j++) {
                            stringBuilder.append(rutaArray[rutaList.get(j)]);
                            if(j != rutaList.size()-1){
                                stringBuilder.append(",");
                            }
                        }
                        tvRutas.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j=0; j<selectedRuta.length; j++){
                            selectedRuta[j] = false;

                            rutaList.clear();

                            tvRutas.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        return root;
    }
}