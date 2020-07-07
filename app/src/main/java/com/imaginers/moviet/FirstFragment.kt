package com.imaginers.moviet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.imaginers.moviet.adapter.WordListAdapter
import com.imaginers.moviet.data.Word
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        button_first.setOnClickListener {

        }

        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { WordListAdapter(it) }
        recyclerview.adapter = adapter

        //observer data
        wordViewModel.state.observe(viewLifecycleOwner, Observer {
            // Update the cached copy of the words in the adapter.
            it.data?.let { word ->
                adapter?.setWords(word as List<Word>)
            }
        })
    }

}