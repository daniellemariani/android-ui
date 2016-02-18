package com.dmariani.androidui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dmariani.androidui.R;
import com.dmariani.androidui.activity.CollapsingToolbarActivity;
import com.dmariani.androidui.activity.FeatureActivity;
import com.dmariani.androidui.activity.IntroViewPagerActivity;
import com.dmariani.androidui.activity.QuickReturnActivity;
import com.dmariani.androidui.activity.TabLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Danielle Mariani on 08/02/15.
 */
public class FeatureListFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    /**
     * Constants
     */
    private static final String ITEM_NAME = "name";
    private static final String ITEM_DESCRIPTION = "description";

    /**
     * Views
     */
    private ListView listView;

    public static FeatureListFragment newInstance() {
        return new FeatureListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(R.string.android_feature_title);
        listView = (ListView) getView().findViewById(R.id.listview);

        ArrayList<Map<String, String>> list = buildData();
        String[] from = {ITEM_NAME, ITEM_DESCRIPTION};
        int[] to = {android.R.id.text1, android.R.id.text2};
        listView.setAdapter(new SimpleAdapter(getContext(), list,
                android.R.layout.simple_list_item_2, from, to));
        listView.setOnItemClickListener(this);
    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList();
        list.add(createRowData(R.string.feature_floating_label_title, R.string.feature_floating_label_description));
        list.add(createRowData(R.string.feature_floating_button_title, R.string.feature_floating_button_description));
        list.add(createRowData(R.string.feature_quick_return_title, R.string.feature_quick_return_description));
        list.add(createRowData(R.string.feature_collapsing_toolbar_title, R.string.feature_collapsing_toolbar_description));
        list.add(createRowData(R.string.feature_collapsing_image_toolbar_title, R.string.feature_collapsing_image_toolbar_description));
        list.add(createRowData(R.string.feature_tablayout_title, R.string.feature_tablayout_description));
        list.add(createRowData(R.string.feature_tablayout_image_toolbar_title, R.string.feature_tablayout_image_toolbar_description));
        list.add(createRowData(R.string.feature_intro_viewpager_title, R.string.feature_intro_viewpager_description));
        return list;
    }

    private HashMap<String, String> createRowData(int name, int description) {
        HashMap<String, String> item = new HashMap();
        item.put(ITEM_NAME, getString(name));
        item.put(ITEM_DESCRIPTION, getString(description));
        return item;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent;

        if (position == 2) {
            intent = new Intent(getContext(), QuickReturnActivity.class);
        } else if (position == 3) {
            Bundle bundle = new Bundle();
            bundle.putInt(CollapsingToolbarActivity.TOOLBAR_LAYOUT_TYPE, CollapsingToolbarActivity.ONLY_TOOLBAR);
            intent = new Intent(getContext(), CollapsingToolbarActivity.class);
            intent.putExtras(bundle);
        } else if (position == 4) {
            Bundle bundle = new Bundle();
            bundle.putInt(CollapsingToolbarActivity.TOOLBAR_LAYOUT_TYPE, CollapsingToolbarActivity.IMAGE_AND_TOOLBAR);
            intent = new Intent(getContext(), CollapsingToolbarActivity.class);
            intent.putExtras(bundle);
        } else if (position == 5) {
            Bundle bundle = new Bundle();
            bundle.putInt(TabLayoutActivity.TOOLBAR_LAYOUT_TYPE, TabLayoutActivity.ONLY_TOOLBAR);
            intent = new Intent(getContext(), TabLayoutActivity.class);
            intent.putExtras(bundle);
        } else if (position == 6) {
            Bundle bundle = new Bundle();
            bundle.putInt(TabLayoutActivity.TOOLBAR_LAYOUT_TYPE, TabLayoutActivity.IMAGE_AND_TOOLBAR);
            intent = new Intent(getContext(), TabLayoutActivity.class);
            intent.putExtras(bundle);
        } else if (position == 7) {
            intent = new Intent(getContext(), IntroViewPagerActivity.class);
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(FeatureActivity.FEATURE_TO_SHOW, position);
            intent = new Intent(getContext(), FeatureActivity.class);
            intent.putExtras(bundle);
        }
        startActivity(intent);

    }

}
