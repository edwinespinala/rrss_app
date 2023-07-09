package com.example.rrssapp.ui.cargo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rrssapp.Entities.Cargo;
import com.example.rrssapp.OnItemClickListener;
import com.example.rrssapp.databinding.FragmentRvCargosBinding;
import com.example.rrssapp.ui.empleado.EmpleadoAdapter;
import com.example.rrssapp.ui.empleado.EmpleadoViewModel;

import java.util.ArrayList;

public class CargoFragment extends Fragment implements OnItemClickListener<Cargo> {

    private FragmentRvCargosBinding binding;
    private CargoAdapter cargoAdapter;
    private ActivityResultLauncher<Intent> launcher;
    private CargoViewModel cargoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cargoViewModel =
                new ViewModelProvider(this).get(CargoViewModel.class);

        binding = FragmentRvCargosBinding.inflate(inflater,container, false);
        View root = binding.getRoot();
        cargoAdapter = new CargoAdapter(new ArrayList<>(),this);


        cargoViewModel.getDataset().observe(getViewLifecycleOwner(), cargos -> {
            cargoAdapter.setItems(cargos);
        });

        setupRecyrcleView();



        return root;
    }

    private void setupRecyrcleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.rvCargos.setLayoutManager(linearLayoutManager);
        binding.rvCargos.setAdapter(cargoAdapter);
    }


    @Override
    public void onItemClick(Cargo data, String click) {

    }
}
