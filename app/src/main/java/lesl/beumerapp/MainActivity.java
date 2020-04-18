package lesl.beumerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.ShareActionProvider;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Map;
import java.io.File;

import lesl.beumerapp.databinding.ElementViewBinding;
import lesl.beumerapp.databinding.MainLayoutBinding;
import lesl.beumerapp.models.Area;
import lesl.beumerapp.models.Element;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private MainLayoutBinding binding;
    private ShareActionProvider shareActionProvider;
    private final String dataFilename = "frequencies.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.main_layout);
        binding.setViewmodel(viewModel);
        binding.elementPager.init(viewModel, binding.tabLayout);
        setSupportActionBar(binding.appbar);
        getSupportFragmentManager().beginTransaction().add(R.id.element_pager, new ElementFragment()).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("on pause", "activity pause");
        viewModel.persistData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        Uri contentUri = FileProvider.getUriForFile(this, "lesl.beumerapp.FileProvider", new File(getFilesDir(), dataFilename));
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareActionProvider.setShareIntent(shareIntent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.action_reset:
                viewModel.resetFrequencies();
                handled = true;
                break;
            case R.id.action_share:
                // listener is called
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }
}
