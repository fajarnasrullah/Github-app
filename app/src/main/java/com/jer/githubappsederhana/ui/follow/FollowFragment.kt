package com.jer.githubappsederhana.ui.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jer.githubappsederhana.databinding.FragmentFollowBinding
import com.jer.githubappsederhana.response.FollowUserResponseItem
import com.jer.githubappsederhana.response.ItemsItem
import com.jer.githubappsederhana.ui.detail.DetailViewModel
import com.jer.githubappsederhana.ui.main.UserAdapter


class FollowFragment : Fragment() {

//    private lateinit var followRecyclerView: RecyclerView
    private lateinit var viewModel2 : DetailViewModel
    private lateinit var binding : FragmentFollowBinding
    private lateinit var adapter : UserAdapter
    private var position: Int = 0
    private var username: String? = null


//    var type = 0


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        binding = FragmentFollowBinding.inflate(layoutInflater)
//
//
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_follow, container, false)
        binding = FragmentFollowBinding.inflate(inflater, container, false)



        val layoutManager2 = LinearLayoutManager(requireActivity())
        binding.recyclerView2.layoutManager = layoutManager2


        binding.recyclerView2.layoutManager = LinearLayoutManager(requireActivity())

        val itemDecoration2 = DividerItemDecoration(requireActivity(), layoutManager2.orientation)
        binding.recyclerView2.addItemDecoration(itemDecoration2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val tvLabel: TextView = view.findViewById(R.id.recyclerView2)
////        val tvLabel : RecyclerView = binding.recyclerView2
//        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
//        tvLabel.text = getString(R.string.content_tab_text, index)


        // Inisialisasi ViewModel
        viewModel2 = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

//        viewModel2.getFollowersUser()


        viewModel2.isLoading2.observe(viewLifecycleOwner) {
            showLoading3(it)
        }

//





//        binding?.recyclerView2?.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            setHasFixedSize(true)
//            adapter = this@FollowFragment.adapter
//        }
//
//        when (type) {
//            FOLLOWERS -> {
//                viewModel2.followers.observe(viewLifecycleOwner, this::setFollowersData)
//            }
//
//            FOLLOWING -> {
//                viewModel2.followers.observe(viewLifecycleOwner, this::setFollowersData)
//            }
//        }


//        binding.recyclerView2.adapter = adapter


//        viewModel = ViewModelProvider(requireActivity()).get(FollowViewModel::class.java)


//        arguments?.let {
//            val position = it.getInt(ARG_SECTION_NUMBER)
//            val username = it.getString(ARG_USERNAME) ?: ""
//
//            if (position == 1) {
//                // Fetch and display Followers
//                viewModel.getFollowers(username)
//
//            } else {
//                // Fetch and display Following
//                viewModel.getFollowing(username)
//            }
//        }



        arguments?.let {
            position = it.getInt(ARG_SECTION_NUMBER)
            username = it.getString(ARG_USERNAME)
        }

        if (position == 1){
            viewModel2.getFollowersUser(username.toString())
            viewModel2.followers.observe(viewLifecycleOwner) { followersUser ->
                setFollowersData(followersUser)
//            adapter.notifyDataSetChanged()
            }
//           binding.tescuy.text = "Get Followers $username"
//            viewModel2.getFollowersUser()
        } else {

            viewModel2.getFollowingUser(username.toString())
            viewModel2.following.observe(viewLifecycleOwner) { followingUser ->
                setFollowersData(followingUser)
//            adapter.notifyDataSetChanged()
            }
//            binding.tescuy.text = "Get Following $username"
        }

    }


    private fun showLoading3(isLoading3: Boolean) {
        if (isLoading3) {
            binding.progressBar3.visibility = View.VISIBLE
        } else {
            binding.progressBar3.visibility = View.GONE
        }
    }

    private fun setFollowersData(followersUser: List<ItemsItem>) {
        val adapter = UserAdapter(requireActivity())
        adapter.submitList(followersUser)
        binding.recyclerView2.adapter = adapter



//        binding.searchView.setText("")

    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "username"
//        const val FOLLOWING = 100
//        const val FOLLOWERS = 101

//        fun newInstance(type: Int) = FollowFragment()
//            .apply {
//                this.type = type
//            }
    }

}