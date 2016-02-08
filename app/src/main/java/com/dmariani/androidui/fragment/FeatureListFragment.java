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
import com.dmariani.androidui.activity.FeatureActivity;

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
        Bundle bundle = new Bundle();
        bundle.putInt(FeatureActivity.FEATURE_TO_SHOW, position);
        Intent intent = new Intent(getContext(), FeatureActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
