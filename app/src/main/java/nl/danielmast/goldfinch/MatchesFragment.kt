package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class MatchesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_matches, container, false).apply {
        val myStringArray = arrayOf("John", "Mary", "Leo")
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, myStringArray)

        val listView: ListView = findViewById(R.id.matches_listview)
        listView.adapter = adapter
    }

}