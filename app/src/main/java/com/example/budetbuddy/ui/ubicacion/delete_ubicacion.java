package com.example.budetbuddy.ui.ubicacion;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.budetbuddy.R;


public class delete_ubicacion extends Fragment {

    EditText text_del_ubicacion;
    Button btn_del_ubi;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_ubicacion, container, false);

        text_del_ubicacion = view.findViewById(R.id.text_del_ubicacion);
        btn_del_ubi = view.findViewById(R.id.btn_del_ubi);

        btn_del_ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUbcacionPorID();
            }
        });

        return view;
    }

    private void eliminarUbcacionPorID() {
        String textidUbicacion = text_del_ubicacion.getText().toString();

        if (textidUbicacion.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID del usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/eliminarUbicacionPorID/" + textidUbicacion;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Ubicacion eliminada con éxito", Toast.LENGTH_SHORT).show();
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