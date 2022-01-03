/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        val args = GameWonFragmentArgs.fromBundle(arguments!!) //this is from the action class of game fragment

        Toast.makeText(context, "Number of questions : ${args.numQ} Correct : ${args.numC}",Toast.LENGTH_LONG).show()

        binding.nextMatchButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    private fun getShareIntent() : Intent
    {
        //Now before we make the winner menu mei share ka feature we must have an intent ready to invoke
//        val args = GameWonFragmentArgs.fromBundle(arguments!!)
//        val shareIntent = Intent(Intent.ACTION_SEND) //SO what kind of intent do you want to make SO that would be Intent.blahblah
//        //In order to properly process an intent, we must firstly make an intent. that can be done as
//        //Intent(Intent.ACTION_Whatever) this will make an intent available but what type. So specify the data u wanna send
//        //may be text or image etc. Now we must putExtra and add the precise values. Refer to the docs if you wish
//        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numC, args.numQ))
//        return shareIntent
        val args = GameWonFragmentArgs.fromBundle(arguments!!)

        return ShareCompat.IntentBuilder.from(activity!!).setText(getString(R.string.share_success_text, args.numC, args.numQ)).setType("text/plain").intent
    }
    fun startIntentsharing()
    {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) //itemId comes from getItemId()
        {
            R.id.share -> getShareIntent()
        }
        return super.onOptionsItemSelected(item)
    }
}
