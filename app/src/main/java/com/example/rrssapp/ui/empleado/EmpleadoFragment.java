package com.example.rrssapp.ui.empleado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rrssapp.Entities.Empleado;
import com.example.rrssapp.OnItemClickListener;
import com.example.rrssapp.databinding.FragmentEmpleadoBinding;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoFragment extends Fragment implements OnItemClickListener<Empleado> {

    private FragmentEmpleadoBinding binding;
    private EmpleadoAdapter empleadoAdapter;

    EmpleadoViewModel empleadoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        empleadoViewModel =
                new ViewModelProvider(this).get(EmpleadoViewModel.class);

        binding = FragmentEmpleadoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        empleadoAdapter = new EmpleadoAdapter(new ArrayList<>(),this);

        empleadoViewModel.getDataset().observe(getViewLifecycleOwner(), empleados -> {
            empleadoAdapter.setItems(empleados);
        });

        setupRecyrcleView();

        return root;
    }

    private void setupRecyrcleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.rvEmpleados.setLayoutManager(linearLayoutManager);
        binding.rvEmpleados.setAdapter(empleadoAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Empleado data) {

    }
}