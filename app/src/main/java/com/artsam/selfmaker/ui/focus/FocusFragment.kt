package com.artsam.selfmaker.ui.focus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artsam.selfmaker.R

class FocusFragment : Fragment() {

    private lateinit var focusViewModel: FocusViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        focusViewModel =
            ViewModelProvider(this).get(FocusViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_focus, container, false)
        val textView: TextView = root.findViewById(R.id.text_focus)
        focusViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}