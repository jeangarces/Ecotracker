package com.example.budetbuddy.ui.alerta;

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

public class create_alerta extends Fragment {

    EditText text_id_alerta, text_ubicacion, text_fecha_hora, text_descripcion, text_nivel_gravedad, text_estado;
    Button btn_create_alerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_alerta, container, false);

        text_id_alerta = view.findViewById(R.id.text_id_alerta);
        text_ubicacion = view.findViewById(R.id.text_ubicacion);
        text_fecha_hora = view.findViewById(R.id.text_fecha_hora);
        text_descripcion = view.findViewById(R.id.text_descripcion);
        text_nivel_gravedad = view.findViewById(R.id.text_nivel_gravedad);
        text_estado = view.findViewById(R.id.text_estado);
        btn_create_alerta = view.findViewById(R.id.btn_create_alerta);

        btn_create_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearAlerta();
            }
        });

        return view;
    }

    private void crearAlerta() {
        String idAlerta = text_id_alerta.getText().toString();
        String ubicacion = text_ubicacion.getText().toString();
        String fechaHora = text_fecha_hora.getText().toString();
        String descripcion = text_descripcion.getText().toString();
        String nivelGravedad = text_nivel_gravedad.getText().toString();
        String estado = text_estado.getText().toString();

        if (idAlerta.isEmpty() || ubicacion.isEmpty() || fechaHora.isEmpty() || descripcion.isEmpty() || nivelGravedad.isEmpty() || estado.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/crearAlerta/" + idAlerta + "/" + ubicacion + "/" + fechaHora + "/" + descripcion + "/" + nivelGravedad + "/" + estado;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Alerta creada con éxito", Toast.LENGTH_SHORT).show();
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
