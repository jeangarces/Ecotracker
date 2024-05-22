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

public class create_reporte extends Fragment {

    EditText text_create_id_reporte, text_create_id_usuario, text_create_ubicacion_evento, text_create_tipo_evento, text_create_descripcion, text_create_fecha_hora, text_create_estado;
    Button btn_create_reporte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_reporte, container, false);

        text_create_id_reporte = view.findViewById(R.id.text_create_id_reporte);
        text_create_id_usuario = view.findViewById(R.id.text_create_id_usuario);
        text_create_ubicacion_evento = view.findViewById(R.id.text_create_ubicacion_evento);
        text_create_tipo_evento = view.findViewById(R.id.text_create_tipo_evento);
        text_create_descripcion = view.findViewById(R.id.text_create_descripcion);
        text_create_fecha_hora = view.findViewById(R.id.text_create_fecha_hora);
        text_create_estado = view.findViewById(R.id.text_create_estado);
        btn_create_reporte = view.findViewById(R.id.btn_create_reporte);

        btn_create_reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearReporteEventoAmbiental();
            }
        });

        return view;
    }

    private void crearReporteEventoAmbiental() {
        String idReporte = text_create_id_reporte.getText().toString();
        String idUsuario = text_create_id_usuario.getText().toString();
        String ubicacionEvento = text_create_ubicacion_evento.getText().toString();
        String tipoEvento = text_create_tipo_evento.getText().toString();
        String descripcion = text_create_descripcion.getText().toString();
        String fechaHora = text_create_fecha_hora.getText().toString();
        String estado = text_create_estado.getText().toString();

        if (idReporte.isEmpty() || idUsuario.isEmpty() || ubicacionEvento.isEmpty() || tipoEvento.isEmpty() || descripcion.isEmpty() || fechaHora.isEmpty() || estado.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/crearReporteEventoAmbiental/" + idReporte + "/" + idUsuario + "/" + ubicacionEvento + "/" + tipoEvento + "/" + descripcion + "/" + fechaHora + "/" + estado;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Reporte de evento ambiental creado con éxito", Toast.LENGTH_SHORT).show();
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
