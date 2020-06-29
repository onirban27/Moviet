package com.imaginers.moviet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel
    private val newWordActivityRequestCode = 1


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

        view.findViewById<Button>(R.id.button_first).setOnClickListener {

        }

        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = context?.let { WordListAdapter(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        wordViewModel.allWords.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter?.setWords(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(SecondFragment.EXTRA_REPLY)?.let {
                val word = Word(null,it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                context,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}