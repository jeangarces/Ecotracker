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

public class delete_alerta extends Fragment {

    EditText text_id_alerta_eliminar;
    Button btn_eliminar_alerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_alerta, container, false);

        text_id_alerta_eliminar = view.findViewById(R.id.text_id_alerta_eliminar);
        btn_eliminar_alerta = view.findViewById(R.id.btn_eliminar_alerta);

        btn_eliminar_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarAlertaPorID();
            }
        });

        return view;
    }

    private void eliminarAlertaPorID() {
        String idAlertaEliminar = text_id_alerta_eliminar.getText().toString();

        if (idAlertaEliminar.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID de la alerta a eliminar", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/eliminarAlertaPorID/" + idAlertaEliminar;

        StringRequest deleteRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(deleteRequest);
    }
}
