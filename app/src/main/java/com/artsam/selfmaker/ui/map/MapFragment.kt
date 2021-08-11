package com.artsam.selfmaker.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artsam.selfmaker.R
import com.artsam.selfmaker.utils.log

class MapFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java) // standard init way
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val textView: TextView = root.findViewById(R.id.text_map)
        mapViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        log { "onCreateView() " + mapViewModel.javaClass.getName() + "@" + mapViewModel.hashCode().toString() }
        return root
    }

    override fun onPause() {
        log { "onPause()" }
        super.onPause()
    }

    override fun onStop() {
        log { "onStop()" }
        super.onStop()
    }

    override fun onDestroy() {
        log { "onDestroy()" }
        super.onDestroy()
    }
}