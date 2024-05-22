package com.example.budetbuddy.ui.notificacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;

import com.example.budetbuddy.databinding.FragmentNotificacionBinding;


public class Notificacion extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentNotificacionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateNotificacion;
        Read = binding.btnReadNotificacion;
        Update = binding.btnUpdateNotificacion;
        Delete = binding.btnDeleteNotificacion;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_notificacion);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_notificacion);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_notificacion);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_notificacion);
            }
        });



        return root;
    }
}