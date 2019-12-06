package org.guildsa.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class TextFragment : Fragment() {
    private var textView: TextView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.text_fragment, container, false)
        textView = view.findViewById<View?>(R.id.textView1) as TextView?
        return view
    }

    fun changeTextProperties(fontsize: Int, text: String?) {
        textView.setTextSize(fontsize.toFloat())
        textView.setText(text)
    }
}