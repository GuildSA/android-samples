package org.guildsa.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.text_edit_fragment.*

class TextEditFragment : Fragment(), OnSeekBarChangeListener {

    private var seekProgess = 20
    private var parentActivity: TextEditListener? = null

    interface TextEditListener {
        open fun onTextEditButtonClick(fontSize: Int, text: String?)
    }

    override fun onAttach(activity: Activity) {

        super.onAttach(activity)

        // If our fragment is attached to an Activity, cache a reference to the Activity that is
        // using our TextEditFragment and make sure that it implements the TextEditListener
        // interface. If it doesn't implement TextEditListener, we have a problem!
        parentActivity = try {
            activity as TextEditListener?
        } catch(e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement TextEditListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.text_edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        editText1.setText("New Text")

        seekBar1.progress = seekProgess
        seekBar1.setOnSeekBarChangeListener(this)

        button1.setOnClickListener { v -> buttonClicked(v) }
    }

    private fun buttonClicked(view: View?) {

        // When the button on our fragment is clicked, tell the Activity that is using us
        // that the button was clicked and pass the required info.
        parentActivity?.onTextEditButtonClick(seekProgess, editText1.text.toString())
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekProgess = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}