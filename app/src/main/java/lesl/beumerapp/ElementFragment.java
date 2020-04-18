package lesl.beumerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import lesl.beumerapp.databinding.ElementViewBinding;
import lesl.beumerapp.models.Area;
import lesl.beumerapp.models.Element;

public class ElementFragment extends Fragment {
    private static MainViewModel viewModel;
    private ElementViewBinding binding;
    private CounterListAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        Bundle args = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ElementViewBinding.inflate(inflater, container, false);
        listAdapter = new CounterListAdapter(getActivity(), viewModel.getCurrentAreas().getValue(), areaCallback);
        binding.counterList.setAdapter(listAdapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel.getCurrentAreas().observe(getViewLifecycleOwner(), new Observer<List<Area>>() {
            @Override
            public void onChanged(@Nullable final List<Area> areas) {
                listAdapter.setData(areas);
            }
        });
    }

    private final AreaEventHandler areaCallback = new AreaEventHandler() {
        @Override
        public void onIncrementClick(Area area) {
            viewModel.increment(area);
        }

        @Override
        public void onDecrementClick(Area area) {
            viewModel.decrement(area);
        }
    };
}
