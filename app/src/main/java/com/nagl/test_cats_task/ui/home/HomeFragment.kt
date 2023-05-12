package com.nagl.test_cats_task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nagl.test_cats_task.data.repository.CatsRepository
import com.nagl.test_cats_task.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var catsRepository: CatsRepository

    private val catsViewModel: CatsViewModel by viewModels { CatsViewModelFactory(catsRepository) }

    //private val homeViewModel: HomeViewModel by viewModels()

    //todo: implement api https://api.thecatapi.com/v1/images/search?limit=30&page=2&order=ASC&api_key=live_r4PYFFHwfGbX5HizUiv0d9Vtg2E6psMJ5yISMlngaOev4llQ2JDHuNjKj7JagoXA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val catsViewModel =
            ViewModelProvider(this)[CatsViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}