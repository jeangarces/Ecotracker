package com.example.budetbuddy.ui.campana;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.budetbuddy.R;
import com.example.budetbuddy.databinding.FragmentCampanaBinding;


public class Campana extends Fragment {

    Button Create;
    Button Read;
    Button Update;
    Button Delete;

    private FragmentCampanaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCampanaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Create = binding.btnCreateCampana;
        Read = binding.btnReadCampana;
        Update = binding.btnUpdateCampana;
        Delete = binding.btnDeleteCampana;


        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.create_campana);
            }
        });

        Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.read_campana);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.update_campana);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.delete_campana);
            }
        });



        return root;
    }

}