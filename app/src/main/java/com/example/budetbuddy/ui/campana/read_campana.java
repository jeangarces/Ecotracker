package com.example.budetbuddy.ui.campana;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.budetbuddy.R;

import org.json.JSONException;
import org.json.JSONObject;

public class read_campana extends Fragment {

    EditText text_id_campana;
    EditText text_nombre;
    EditText text_descripcion;
    EditText text_tipo_anuncio;
    EditText text_estado;
    Button btn_read_campana;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_campana, container, false);

        text_id_campana = view.findViewById(R.id.text_id_campana);
        text_nombre = view.findViewById(R.id.text_nombre);
        text_descripcion = view.findViewById(R.id.text_descripcion);
        text_tipo_anuncio = view.findViewById(R.id.text_tipo_anuncio);
        text_estado = view.findViewById(R.id.text_estado);
        btn_read_campana = view.findViewById(R.id.btn_read_campana);

        btn_read_campana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCampanaPorID();
            }
        });

        return view;
    }

    private void buscarCampanaPorID() {
        String idCampana = text_id_campana.getText().toString();

        if (idCampana.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID de la Campaña", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarCampanaPorID/" + idCampana;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_nombre.setText(jsonObject.getString("Nombre"));
                            text_descripcion.setText(jsonObject.getString("Descripcion"));
                            text_tipo_anuncio.setText(jsonObject.getString("Tipo_de_Anuncio"));
                            text_estado.setText(jsonObject.getString("Estado"));
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
