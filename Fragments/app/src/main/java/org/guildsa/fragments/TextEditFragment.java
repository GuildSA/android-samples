package org.guildsa.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class TextEditFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private static int seekProgess = 20;
    private static EditText editText;

    TextEditListener parentActivity;

    public interface TextEditListener {
        public void onTextEditButtonClick(int fontSize, String text);
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        // If our fragment is attached to an Activity, cache a reference to the Activity that is
        // using our TextEditFragment and make sure that it implements the TextEditListener
        // interface. If it doesn't implement TextEditListener, we have a problem!
        try {
            parentActivity = (TextEditListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.text_edit_fragment, container, false);

        editText = (EditText) view.findViewById(R.id.editText1);
        editText.setText("New Text");

        final SeekBar seekbar = (SeekBar) view.findViewById(R.id.seekBar1);
        seekbar.setProgress(seekProgess);
        seekbar.setOnSeekBarChangeListener(this);

        final Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    public void buttonClicked(View view) {

        // When the button on our fragment is clicked, tell the Activity that is using us
        // that the button was clicked and pass the required info.
        parentActivity.onTextEditButtonClick(seekProgess, editText.getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekProgess = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
