package com.example.final_project.ui.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.final_project.R

class PicturesFragment : Fragment() {

    private lateinit var picturesViewModel: PicturesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        picturesViewModel =
                ViewModelProvider(this).get(PicturesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pictures, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        picturesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}