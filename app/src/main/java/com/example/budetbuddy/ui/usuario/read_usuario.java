package com.example.budetbuddy.ui.usuario;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.budetbuddy.R;


public class read_usuario extends Fragment {

    EditText text_read_id, text_read_nombre, text_read_correo, text_read_contra, text_read_ubi;
    Button btn_read_usu;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_usuario, container, false);

        text_read_id = view.findViewById(R.id.text_read_id);
        text_read_nombre = view.findViewById(R.id.text_read_nombre);
        text_read_correo = view.findViewById(R.id.text_read_correo);
        text_read_contra = view.findViewById(R.id.text_read_contra);
        text_read_ubi = view.findViewById(R.id.text_read_ubi);
        btn_read_usu = view.findViewById(R.id.btn_read_usu);

        btn_read_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuarioPorID();
            }
        });

        return view;
    }
    private void buscarUsuarioPorID() {
        String textIdBuscar = text_read_id.getText().toString();

        if (textIdBuscar.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID del usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarUsuarioPorID/" + textIdBuscar;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_read_nombre.setText(jsonObject.getString("nombre"));
                            text_read_correo.setText(jsonObject.getString("correo_electronico"));
                            text_read_contra.setText(jsonObject.getString("contrasena"));
                            text_read_ubi.setText(jsonObject.getString("id_ubicacion"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error al analizar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(getRequest);
    }
    }