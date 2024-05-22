package com.example.budetbuddy.ui.reporte;

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
import org.json.JSONException;
import org.json.JSONObject;

public class read_reporte extends Fragment {

    EditText text_read_id_reporte, text_read_id_usuario, text_read_ubicacion_evento, text_read_tipo_evento, text_read_descripcion, text_read_fecha_hora, text_read_estado;
    Button btn_read_reporte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_reporte, container, false);

        text_read_id_reporte = view.findViewById(R.id.text_read_id_reporte);
        text_read_id_usuario = view.findViewById(R.id.text_read_id_usuario);
        text_read_ubicacion_evento = view.findViewById(R.id.text_read_ubicacion_evento);
        text_read_tipo_evento = view.findViewById(R.id.text_read_tipo_evento);
        text_read_descripcion = view.findViewById(R.id.text_read_descripcion);
        text_read_fecha_hora = view.findViewById(R.id.text_read_fecha_hora);
        text_read_estado = view.findViewById(R.id.text_read_estado);
        btn_read_reporte = view.findViewById(R.id.btn_read_reporte);

        btn_read_reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarReporteEventoAmbientalPorID();
            }
        });

        return view;
    }

    private void buscarReporteEventoAmbientalPorID() {
        String idReporte = text_read_id_reporte.getText().toString();

        if (idReporte.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID del reporte", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarReporteEventoAmbientalPorID/" + idReporte;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_read_id_usuario.setText(jsonObject.getString("id_usuario"));
                            text_read_ubicacion_evento.setText(jsonObject.getString("ubicacion_evento"));
                            text_read_tipo_evento.setText(jsonObject.getString("tipo_evento"));
                            text_read_descripcion.setText(jsonObject.getString("descripcion"));
                            text_read_fecha_hora.setText(jsonObject.getString("fecha_hora"));
                            text_read_estado.setText(jsonObject.getString("estado"));
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
