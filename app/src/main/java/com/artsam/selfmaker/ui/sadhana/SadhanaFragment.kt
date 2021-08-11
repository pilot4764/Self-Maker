package com.artsam.selfmaker.ui.sadhana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artsam.selfmaker.R

class SadhanaFragment : Fragment() {

    private lateinit var sadhanaViewModel: SadhanaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sadhanaViewModel =
            ViewModelProvider(this).get(SadhanaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sadhana, container, false)
        val textView: TextView = root.findViewById(R.id.text_sadhana)
        sadhanaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}