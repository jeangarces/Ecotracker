package com.example.budetbuddy.ui.usuario;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.budetbuddy.R;


public class create_usuario extends Fragment {

    EditText text_create_id, text_create_nombre, text_create_correo, text_create_contra, text_create_ubi;
    Button btn_create_usu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_usuario, container, false);

        text_create_id = view.findViewById(R.id.text_create_id);
        text_create_nombre = view.findViewById(R.id.text_create_nombre);
        text_create_correo = view.findViewById(R.id.text_create_correo);
        text_create_contra = view.findViewById(R.id.text_create_contra);
        text_create_ubi = view.findViewById(R.id.text_create_ubi);
        btn_create_usu = view.findViewById(R.id.btn_create_usu);

        btn_create_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuarios();
            }
        });

        return view;
    }

    private void crearUsuarios() {
        String textIdUsuario = text_create_id.getText().toString();
        String textNombreUsuario = text_create_nombre.getText().toString();
        String textCorreoUsuario = text_create_correo.getText().toString();
        String textContrasenaUsuario = text_create_contra.getText().toString();
        String textUbiUsuario = text_create_ubi.getText().toString();

        if (textIdUsuario.isEmpty() || textNombreUsuario.isEmpty() || textCorreoUsuario.isEmpty() || textContrasenaUsuario.isEmpty() || textUbiUsuario.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/crearUsuarios/" + textIdUsuario + "/" + textNombreUsuario + "/" + textCorreoUsuario + "/" + textContrasenaUsuario + "/" + textUbiUsuario;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
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

        Volley.newRequestQueue(requireContext()).add(postRequest);
    }
}