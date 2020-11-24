package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import nl.danielmast.goldfinch.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {

    private lateinit var binding: FragmentMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root.apply {
            val myStringArray = arrayOf("John", "Mary", "Leo")
            val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, myStringArray)
            binding.matchesListview.adapter = adapter
        }
    }

}