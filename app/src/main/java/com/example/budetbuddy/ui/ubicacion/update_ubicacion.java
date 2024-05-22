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


public class update_ubicacion extends Fragment {

    EditText text_update_id_ubi, text_update_latitud, text_update_longitud, text_update_direcc, text_update_ciudad, text_update_pais;
    Button btn_update_ubi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_ubicacion, container, false);

        text_update_id_ubi = view.findViewById(R.id.text_update_id_ubi);
        text_update_latitud = view.findViewById(R.id.text_update_latitud);
        text_update_longitud = view.findViewById(R.id.text_update_longitud);
        text_update_direcc = view.findViewById(R.id.text_update_direcc);
        text_update_ciudad = view.findViewById(R.id.text_update_ciudad);
        text_update_pais = view.findViewById(R.id.text_update_pais);
        btn_update_ubi = view.findViewById(R.id.btn_update_ubi);

        btn_update_ubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUbicacionPorID();
            }
        });

        return view;
    }

    private void actualizarUbicacionPorID() {
        String textIdUbicacion = text_update_id_ubi.getText().toString();
        String textLatitud = text_update_latitud.getText().toString();
        String textLongitud = text_update_longitud.getText().toString();
        String textDireccion = text_update_direcc.getText().toString();
        String textCiudad = text_update_ciudad.getText().toString();
        String textPais = text_update_pais.getText().toString();

        String url = "http://10.0.2.2:5000/actualizarUbicacionPorID/" + textIdUbicacion + "/" + textLatitud + "/" + textLongitud + "/" + textDireccion + "/" + textCiudad + "/" + textPais;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Ubicacion actualizada con éxito", Toast.LENGTH_SHORT).show();
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