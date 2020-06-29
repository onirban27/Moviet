package com.imaginers.moviet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imaginers.moviet.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_second.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {


    private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.button_second.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(view.name_field.text)) {
                activity?.setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = view.name_field.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                activity?.setResult(Activity.RESULT_OK, replyIntent)
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}