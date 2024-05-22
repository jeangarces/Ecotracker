package com.example.budetbuddy.ui.comentario;

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

public class delete_comentario extends Fragment {

    EditText text_delete_id_comentario;
    Button btn_delete_comentario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_comentario, container, false);

        text_delete_id_comentario = view.findViewById(R.id.text_delete_id_comentario);
        btn_delete_comentario = view.findViewById(R.id.btn_delete_comentario);

        btn_delete_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarComentarioPorID();
            }
        });

        return view;
    }

    private void eliminarComentarioPorID() {
        String idComentario = text_delete_id_comentario.getText().toString();

        if (idComentario.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID del comentario", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/eliminarComentarioPorID/" + idComentario;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Comentario eliminado con éxito", Toast.LENGTH_SHORT).show();
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
