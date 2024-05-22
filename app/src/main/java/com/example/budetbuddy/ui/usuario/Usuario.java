package com.example.budetbuddy.ui.usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;

import com.example.budetbuddy.databinding.FragmentUsuarioBinding;


public class Usuario extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentUsuarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUsuarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateUsuario;
        Read = binding.btnReadUsuario;
        Update = binding.btnUpdateUsuario;
        Delete = binding.btnDeleteUsuario;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_usuario);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_usuario);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_usuario);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_usuario);
            }
        });



        return root;
    }

}