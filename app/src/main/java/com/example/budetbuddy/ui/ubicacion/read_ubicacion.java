package com.example.budetbuddy.ui.ubicacion;

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


public class read_ubicacion extends Fragment {

    EditText text_read_id_ubi, text_read_latitud, text_read_longitud, text_read_direcc, text_read_ciudad, text_read_pais;
    Button btn_read_ubi;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_ubicacion, container, false);

        text_read_id_ubi = view.findViewById(R.id.text_read_id_ubi);
        text_read_latitud = view.findViewById(R.id.text_read_latitud);
        text_read_longitud = view.findViewById(R.id.text_read_longitud);
        text_read_direcc = view.findViewById(R.id.text_read_direcc);
        text_read_ciudad = view.findViewById(R.id.text_read_ciudad);
        text_read_pais = view.findViewById(R.id.text_read_pais);
        btn_read_ubi = view.findViewById(R.id.btn_read_ubi);

        btn_read_ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUbicacionPorID();
            }
        });

        return view;
    }
    private void buscarUbicacionPorID() {
        String textIdBuscar = text_read_id_ubi.getText().toString();

        String url = "http://10.0.2.2:5000/buscarUbicacionPorID/" + textIdBuscar;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_read_latitud.setText(jsonObject.getString("latitud"));
                            text_read_longitud.setText(jsonObject.getString("longitud"));
                            text_read_direcc.setText(jsonObject.getString("direccion"));
                            text_read_ciudad.setText(jsonObject.getString("ciudad"));
                            text_read_pais.setText(jsonObject.getString("pais"));
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