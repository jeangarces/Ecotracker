package com.example.budetbuddy.ui.reporte;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;

import com.example.budetbuddy.databinding.FragmentReporteBinding;

public class Reporte extends Fragment {
    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentReporteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReporteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateReporte;
        Read = binding.btnReadReporte;
        Update = binding.btnUpdateReporte;
        Delete = binding.btnDeleteReporte;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_reporte);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_reporte);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_reporte);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_reporte);
            }
        });



        return root;
    }
}