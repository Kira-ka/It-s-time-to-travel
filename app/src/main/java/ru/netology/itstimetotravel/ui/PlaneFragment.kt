package ru.netology.itstimetotravel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.netology.itstimetotravel.R

import ru.netology.itstimetotravel.databinding.FragmentPlaneBinding
import ru.netology.itstimetotravel.viewmodel.FeedViewModel


class PlaneFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels(ownerProducer = ::requireParentFragment)

    private var _binding: FragmentPlaneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaneBinding.inflate(inflater, container, false)

        viewModel.specificPlain.observe(viewLifecycleOwner) { plain ->
            binding.apply {
                from.text = plain.from
                to.text = plain.to
                like.isChecked = plain.likedByMe
                departureDate.text = plain.departureDate.toString()
                returnDate.text = plain.returnDate.toString()
                price.text = plain.price.toString() + context?.getString(R.string.ruble)
                like.setOnClickListener {
                    viewModel.likeById(plain.id)
                    viewModel.data.observe(viewLifecycleOwner) { plains ->
                        viewModel.onSpecificPlain(plains.find { it.id == plain.id }!!)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
