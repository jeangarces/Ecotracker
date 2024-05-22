package com.example.budetbuddy.ui.notificacion;

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
import com.example.budetbuddy.R;

public class delete_notificacion extends Fragment {

    EditText text_del_id_notificacion;
    Button btn_del_notificacion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_notificacion, container, false);

        text_del_id_notificacion = view.findViewById(R.id.text_del_id_notificacion);
        btn_del_notificacion = view.findViewById(R.id.btn_del_notificacion);

        btn_del_notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarNotificacionPorID();
            }
        });

        return view;
    }

    private void eliminarNotificacionPorID() {
        String idNotificacion = text_del_id_notificacion.getText().toString();

        if (idNotificacion.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID de la notificación", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/eliminarNotificacionPorID/" + idNotificacion;

        StringRequest deleteRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Notificación eliminada con éxito", Toast.LENGTH_SHORT).show();
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
