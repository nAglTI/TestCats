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
import com.nagl.test_cats_task.data.repository.CatsRepository
import com.nagl.test_cats_task.data.repository.CatsRepositoryImpl
import com.nagl.test_cats_task.databinding.FragmentHomeBinding
import com.nagl.test_cats_task.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var catsRepositoryImpl: CatsRepository

    private val catsViewModel: CatsViewModel by viewModels { CatsViewModelFactory(catsRepositoryImpl) }
    private val catListAdapter by lazy { CatListAdapter() }

    // TODO: implement favorites (new fragment, save in DB instance, add new logic to CatItem and CatListAdapter); on click BottomSheetDialogFragment with cat info.
    // Implement Paging

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
            //catsViewModel.adapterCatList.clear()
            catListAdapter.submitList(null)
            catListAdapter.notifyItemRangeRemoved(0, catListAdapter.itemCount)
            catsViewModel.loadCats(0)
        }

        // TODO: improve load condition
        binding.catsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager?
                val lastVisiblePosition = layoutManager!!.findLastVisibleItemPosition()
                if (lastVisiblePosition >= catListAdapter.itemCount - 30 && !binding.swipeRefreshCats.isRefreshing) {
                    catsViewModel.loadCats(catListAdapter.itemCount / NetworkUtils.IMAGE_COUNT)
                }
            }
        })
    }

    private fun observeViewModel() {
        with(catsViewModel) {
            catsList.observe(viewLifecycleOwner) {
                if (it != null) {
                    val count = catListAdapter.itemCount
                    //catsViewModel.adapterCatList.addAll(it)
                    if (count == 0) catListAdapter.submitList(it)
                    else catListAdapter.submitList(catListAdapter.currentList + it)
                    catListAdapter.notifyItemRangeInserted(count, NetworkUtils.IMAGE_COUNT)
                    binding.catListEmpty.visibility = View.GONE
                }
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.swipeRefreshCats.isRefreshing = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}