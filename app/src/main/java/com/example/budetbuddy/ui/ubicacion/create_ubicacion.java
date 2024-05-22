package com.example.budetbuddy.ui.ubicacion;

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


public class create_ubicacion extends Fragment {

    EditText text_create_id_ubi, text_create_latitud, text_create_longitud, text_create_direcc, text_create_ciudad, text_create_pais;
    Button btn_create_ubi;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_ubicacion, container, false);

        text_create_id_ubi = view.findViewById(R.id.text_create_id_ubi);
        text_create_longitud = view.findViewById(R.id.text_create_longitud);
        text_create_latitud = view.findViewById(R.id.text_create_latitud);
        text_create_direcc = view.findViewById(R.id.text_create_direcc);
        text_create_ciudad = view.findViewById(R.id.text_create_ciudad);
        text_create_pais = view.findViewById(R.id.text_create_pais);
        btn_create_ubi = view.findViewById(R.id.btn_create_ubi);

        btn_create_ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUbicacion();
            }
        });

        return view;
    }

    private void  crearUbicacion(){
        String textIdUbi = text_create_id_ubi.getText().toString();
        String textLongitud = text_create_longitud.getText().toString();
        String textLatitud = text_create_latitud.getText().toString();
        String textDirecc = text_create_direcc.getText().toString();
        String textCiudad = text_create_ciudad.getText().toString();
        String textPais = text_create_pais.getText().toString();

        String url = "http://10.0.2.2:5000/crearUbicacion/" + textIdUbi + "/" + textLongitud + "/" + textLatitud + "/" + textDirecc + "/" + textCiudad + "/" + textPais;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Ubicacion creada con éxito", Toast.LENGTH_SHORT).show();
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
