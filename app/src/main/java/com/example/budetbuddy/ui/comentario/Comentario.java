package com.example.budetbuddy.ui.comentario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;
import com.example.budetbuddy.databinding.FragmentComentarioBinding;


public class Comentario extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentComentarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentComentarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateComentario;
        Read = binding.btnReadComentario;
        Update = binding.btnUpdateComentario;
        Delete = binding.btnDeleteComentario;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_comentario);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_comentario);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_comentario);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_comentario);
            }
        });



        return root;
    }

}