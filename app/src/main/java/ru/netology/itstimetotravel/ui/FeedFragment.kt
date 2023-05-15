package ru.netology.itstimetotravel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.netology.itstimetotravel.R
import ru.netology.itstimetotravel.adapter.OnInteractionListener
import ru.netology.itstimetotravel.adapter.PlainAdapter
import ru.netology.itstimetotravel.databinding.FragmentFeedBinding
import ru.netology.itstimetotravel.dto.Plain
import ru.netology.itstimetotravel.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels(ownerProducer = ::requireParentFragment)

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)

        val adapter = PlainAdapter(object : OnInteractionListener {

            override fun onLike(plain: Plain) {
                viewModel.likeById(plain.id)
            }

            override fun onSpecificPlain(plain: Plain) {
                viewModel.onSpecificPlain(plain)
                findNavController().navigate(R.id.action_feedFragment_to_planeFragment)
            }

        })
        binding.list.adapter = adapter
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.progress.isVisible = state.loading
            if (state.error) {
                Snackbar.make(binding.root, R.string.error_loading, Snackbar.LENGTH_LONG)
                    .setAction(R.string.retry_loading) { viewModel.loadPlain() }
                    .show()
            }
        }
        viewModel.data.observe(viewLifecycleOwner) { plain ->
            adapter.submitList(plain)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
