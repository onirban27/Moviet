package com.imaginers.moviet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.imaginers.moviet.data.Word
import com.imaginers.moviet.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        button_second.setOnClickListener {

            if (name_field.text.toString().isNotEmpty() && description_field.text.toString()
                    .isNotEmpty() && concept_field.text.toString().isNotEmpty()
            ) {
                wordViewModel.insertWord(
                    Word(
                        null,
                        name_field.text.toString(),
                        description_field.text.toString(),
                        concept_field.text.toString()
                    )
                )
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            } else {
                Toast.makeText(
                    context,
                    "Bro, you have to insert all the fields lol!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}