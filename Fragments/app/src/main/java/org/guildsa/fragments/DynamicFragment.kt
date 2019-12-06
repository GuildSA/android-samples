package org.guildsa.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DynamicFragment : Fragment() {
    private var textView: TextView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? { // Inflating the layout for this fragment
        val view = inflater.inflate(R.layout.dynamic_fragment, null)
        textView = view.findViewById<View?>(R.id.textView1) as TextView?
        return view
    }

    fun setText(text: String?) {
        textView.setText(text)
    }

    fun getText(): String? {
        return textView.toString()
    }
}