package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //new method each time you generate your own fragment AS generates a binding per XML file
        val binding = FragmentTitleBinding.inflate(inflater,container,false)
        binding.playButton.setOnClickListener { view :View ->
        view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        //here it is important to note that the FragmentTitleBinding is the binding for fragment_title
        //so we do not need to pass explicitly each time.

        setHasOptionsMenu(true)

        return binding.root
        //return inflater.inflate(R.layout.fragment_title4, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflowmenu,menu)

    }

}
/*
TO pass data from one fragment to another you can make a bundle then put the data into the bundle FROM the fragment you want to pas
s and then you can finally set the arguments of the second fragment to the bundle.
 */