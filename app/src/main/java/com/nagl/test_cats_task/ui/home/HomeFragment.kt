package com.nagl.test_cats_task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nagl.test_cats_task.data.repository.CatsRepositoryImpl
import com.nagl.test_cats_task.databinding.FragmentHomeBinding
import com.nagl.test_cats_task.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), CatListAdapter.OnElementEndListener {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var catsRepositoryImpl: CatsRepositoryImpl

    private val catsViewModel: CatsViewModel by viewModels { CatsViewModelFactory(catsRepositoryImpl) }
    private val catListAdapter by lazy { CatListAdapter(this) }
    //private val homeViewModel: HomeViewModel by viewModels()

    //todo: implement api https://api.thecatapi.com/v1/images/search?limit=30&page=2&order=ASC&api_key=live_r4PYFFHwfGbX5HizUiv0d9Vtg2E6psMJ5yISMlngaOev4llQ2JDHuNjKj7JagoXA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initListeners()
        observeViewModel()
        catsViewModel.loadCats(0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.catsRecyclerView.adapter = catListAdapter
    }

    private fun initListeners() {
        binding.swipeRefreshCats.setOnRefreshListener {
            catsViewModel.loadCats(0)
            catsViewModel.adapterCatList.clear()
        }

        binding.catsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager?
                val lastVisiblePosition = layoutManager!!.findLastVisibleItemPosition()
                if (lastVisiblePosition >= catListAdapter.itemCount - 15 && !binding.swipeRefreshCats.isRefreshing) {
                    catsViewModel.loadCats(catListAdapter.itemCount / NetworkUtils.IMAGE_COUNT)
                }
            }
        })
    }

    private fun observeViewModel() {
        with(catsViewModel) {
            catsList.observe(viewLifecycleOwner) {
                if (it != null) {
                    catsViewModel.adapterCatList.addAll(it)
                    catListAdapter.submitList(catsViewModel.adapterCatList)
                    binding.catListEmpty.visibility = View.GONE
                }
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.swipeRefreshCats.isRefreshing = it
            }
        }
    }

    override fun loadMoreData(page: Int) {
        catsViewModel.loadCats(page)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}