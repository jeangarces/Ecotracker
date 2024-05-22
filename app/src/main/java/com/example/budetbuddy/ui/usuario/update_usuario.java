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
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.budetbuddy.R;


public class update_usuario extends Fragment {
    EditText text_update_id, text_update_nuevo_id, text_update_nombre, text_update_correo, text_update_contra, text_update_ubi;
    Button btn_update_usu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_usuario, container, false);

        text_update_id = view.findViewById(R.id.text_update_id);
        text_update_nombre = view.findViewById(R.id.text_update_nombre);
        text_update_correo = view.findViewById(R.id.text_update_correo);
        text_update_contra = view.findViewById(R.id.text_update_contra);
        text_update_ubi = view.findViewById(R.id.text_update_ubi);
        btn_update_usu = view.findViewById(R.id.btn_update_usu);

        btn_update_usu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuarioPorID();
            }
        });

        return view;
    }

    private void actualizarUsuarioPorID() {
        String textIdUsuario = text_update_id.getText().toString();
        String textNombreUsuario = text_update_nombre.getText().toString();
        String textCorreoUsuario = text_update_correo.getText().toString();
        String textContrasenaUsuario = text_update_contra.getText().toString();
        String textUbiUsuario = text_update_ubi.getText().toString();

        if (textIdUsuario.isEmpty() || textNombreUsuario.isEmpty() || textCorreoUsuario.isEmpty() || textContrasenaUsuario.isEmpty() || textUbiUsuario.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/actualizarUsuarioPorID/" + textIdUsuario + "/" + textNombreUsuario + "/" + textCorreoUsuario + "/" + textContrasenaUsuario + "/" + textUbiUsuario;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
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