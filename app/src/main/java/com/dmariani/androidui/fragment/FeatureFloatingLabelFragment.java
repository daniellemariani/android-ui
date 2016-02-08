package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dmariani.androidui.R;

/**
 * @author Danielle Mariani on 08/02/16.
 */
public class FeatureFloatingLabelFragment extends BaseFragment {

    /**
     * Views
     */
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonSubmit;

    public static FeatureFloatingLabelFragment newInstance() {
        return new FeatureFloatingLabelFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature_floating_label, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(R.string.feature_floating_label_title);
        editTextFirstName = (EditText) getView().findViewById(R.id.edit_text_first_name);
        editTextLastName = (EditText) getView().findViewById(R.id.edit_text_last_name);
        buttonSubmit = (Button) getView().findViewById(R.id.button_submit);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean enabled = !TextUtils.isEmpty(editTextFirstName.getText().toString()) && !TextUtils.isEmpty(editTextLastName.getText().toString());
                buttonSubmit.setEnabled(enabled);
            }
        };

        editTextFirstName.addTextChangedListener(textWatcher);
        editTextLastName.addTextChangedListener(textWatcher);
        buttonSubmit.setEnabled(false);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmitButton();
            }
        });
    }

    private void onClickSubmitButton() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String message = getString(R.string.feature_floating_success_message, firstName, lastName);
        showSnackbarMessage(message, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextFirstName.setText("");
                editTextLastName.setText("");
                editTextFirstName.requestFocus();
            }
        });
    }
}
