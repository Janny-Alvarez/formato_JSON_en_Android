package com.example.formatojsonenandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Manejo del espacio para barra de estado/navegación
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de lugares desde strings.xml
        String[] lugaresArray = getResources().getStringArray(R.array.simplified_tourist_places);
        List<String> lugaresList = Arrays.asList(lugaresArray);

        // Asignar el adaptador personalizado
        LugaresAdapter adapter = new LugaresAdapter(lugaresList);
        recyclerView.setAdapter(adapter);
    }

    // Adaptador personalizado para RecyclerView
    private static class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolder> {

        private final List<String> lugares;

        public LugaresAdapter(List<String> lugares) {
            this.lugares = lugares;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflar el diseño personalizado item_lugar.xml
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_lugar, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(lugares.get(position));
        }

        @Override
        public int getItemCount() {
            return lugares.size();
        }

        // ViewHolder con referencia al TextView personalizado
        public static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textViewLugar);
            }
        }
    }
}
