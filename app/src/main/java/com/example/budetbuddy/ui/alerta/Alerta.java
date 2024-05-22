package com.example.budetbuddy.ui.alerta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;
import com.example.budetbuddy.databinding.FragmentAlertaBinding;


public class Alerta extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentAlertaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAlertaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateAlerta;
        Read = binding.btnReadAlerta;
        Update = binding.btnUpdateAlerta;
        Delete = binding.btnDeleteAlerta;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_alerta);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_alerta);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_alerta);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_alerta);
            }
        });



        return root;
    }
}