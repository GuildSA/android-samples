package org.guildsa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflating the layout for this fragment.
        return inflater.inflate(R.layout.text_fragment, container, false)
    }

    fun changeTextProperties(fontsize: Int, text: String?) {

        textView1.textSize = fontsize.toFloat()
        textView1.text = text
    }
}