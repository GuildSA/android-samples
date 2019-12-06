package org.guildsa.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment

class TextEditFragment : Fragment(), OnSeekBarChangeListener {
    private var seekProgess = 20
    private var editText: EditText? = null
    private var parentActivity: TextEditListener? = null

    interface TextEditListener {
        open fun onTextEditButtonClick(fontSize: Int, text: String?)
    }

    override fun onAttach(activity: Activity?) {
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.text_edit_fragment, container, false)
        editText = view.findViewById<View?>(R.id.editText1) as EditText?
        editText.setText("New Text")
        val seekbar = view.findViewById<View?>(R.id.seekBar1) as SeekBar?
        seekbar.setProgress(seekProgess)
        seekbar.setOnSeekBarChangeListener(this)
        val button = view.findViewById<View?>(R.id.button1) as Button?
        button.setOnClickListener(View.OnClickListener { v -> buttonClicked(v) })
        return view
    }

    fun buttonClicked(view: View?) { // When the button on our fragment is clicked, tell the Activity that is using us
// that the button was clicked and pass the required info.
        parentActivity.onTextEditButtonClick(seekProgess, editText.getText().toString())
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekProgess = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}