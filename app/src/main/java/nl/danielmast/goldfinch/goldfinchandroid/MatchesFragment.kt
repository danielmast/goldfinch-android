package nl.danielmast.goldfinch.goldfinchandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class MatchesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_matches, container, false).apply {
        val myStringArray = arrayOf("John", "Mary", "Leo")
        val adapter = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_list_item_1, myStringArray)

        val listView: ListView = findViewById(R.id.matches_listview)
        listView.adapter = adapter
    }

}