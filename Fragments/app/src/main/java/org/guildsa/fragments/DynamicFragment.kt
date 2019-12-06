package org.guildsa.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dynamic_fragment.*

class DynamicFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflating the layout for this fragment.
        return inflater?.inflate(R.layout.dynamic_fragment, null)
    }

    fun setText(text: String?) {
        textView1.setText(text)
    }

    fun getText(): String? {
        return textView1.toString()
    }
}