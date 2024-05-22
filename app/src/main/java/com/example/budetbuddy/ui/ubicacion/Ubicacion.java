package com.example.budetbuddy.ui.ubicacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;
import com.example.budetbuddy.databinding.FragmentUbicacionBinding;


public class Ubicacion extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentUbicacionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateUbicacion;
        Read = binding.btnReadUbicacion;
        Update = binding.btnUpdateUbicacion;
        Delete = binding.btnDeleteUbicacion;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_ubicacion);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_ubicacion);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_ubicacion);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_ubicacion);
            }
        });



        return root;
    }
}