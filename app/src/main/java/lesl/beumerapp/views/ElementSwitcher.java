package lesl.beumerapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import lesl.beumerapp.MainViewModel;
import lesl.beumerapp.models.Element;

public class ElementSwitcher extends FrameLayout {
    private MainViewModel viewModel;
    private TabLayout tab; // tab layout used to switch element views

    public ElementSwitcher(Context context) {
        super(context);
    }

    public ElementSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ElementSwitcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Initialises the view.
     * @param viewModel - view model reference
     * @param tab - tablayout used to switch element views
     */
    public void init(MainViewModel viewModel, TabLayout tab) {
        this.viewModel = viewModel;
        this.tab = tab;
        tab.addOnTabSelectedListener(tabListener);
        for (Element element : viewModel.getFrequencies()) {
            tab.addTab(tab.newTab().setText(element.name));
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return ElementSwitcher.class.getName();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        tab.removeOnTabSelectedListener(tabListener);
    }

    private TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewModel.setCurrentElement(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
