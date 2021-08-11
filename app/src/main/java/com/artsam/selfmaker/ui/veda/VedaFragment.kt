package com.artsam.selfmaker.ui.veda

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.artsam.selfmaker.R

class VedaFragment : Fragment() {

    private lateinit var vedaViewModel: VedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vedaViewModel =
            ViewModelProvider(this).get(VedaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_veda, container, false)
        val textView: TextView = root.findViewById(R.id.text_veda)
        vedaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}