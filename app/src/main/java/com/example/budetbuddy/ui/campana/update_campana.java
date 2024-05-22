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

public class update_campana extends Fragment {

    EditText text_id_campana;
    EditText text_nuevo_nombre;
    EditText text_nueva_descripcion;
    EditText text_nuevo_tipo;
    EditText text_nuevo_estado;
    Button btn_update_campana;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_campana, container, false);

        text_id_campana = view.findViewById(R.id.text_id_campana);
        text_nuevo_nombre = view.findViewById(R.id.text_nuevo_nombre);
        text_nueva_descripcion = view.findViewById(R.id.text_nueva_descripcion);
        text_nuevo_tipo = view.findViewById(R.id.text_nuevo_tipo);
        text_nuevo_estado = view.findViewById(R.id.text_nuevo_estado);
        btn_update_campana = view.findViewById(R.id.btn_update_campana);

        btn_update_campana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarCampanaPorID();
            }
        });

        return view;
    }

    private void actualizarCampanaPorID() {
        String idCampana = text_id_campana.getText().toString();
        String nuevoNombre = text_nuevo_nombre.getText().toString();
        String nuevaDescripcion = text_nueva_descripcion.getText().toString();
        String nuevoTipo = text_nuevo_tipo.getText().toString();
        String nuevoEstado = text_nuevo_estado.getText().toString();

        if (idCampana.isEmpty() || nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty() || nuevoTipo.isEmpty() || nuevoEstado.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/actualizarCampanaPorID/" + idCampana + "/" + nuevoNombre + "/" + nuevaDescripcion + "/" + nuevoTipo + "/" + nuevoEstado;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Campaña actualizada con éxito", Toast.LENGTH_SHORT).show();
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
