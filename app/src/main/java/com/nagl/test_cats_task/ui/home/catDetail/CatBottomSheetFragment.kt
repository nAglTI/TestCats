package com.nagl.test_cats_task.ui.home.catDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nagl.test_cats_task.R
import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.databinding.BottomFragmentCatDetailBinding

class CatBottomSheetFragment : BottomSheetDialogFragment() {

    private val cat by lazy { requireArguments().getParcelable<Cat>("cat")!! }

    // TODO: beautify fragment UI
    private var _binding: BottomFragmentCatDetailBinding? = null
    private val binding: BottomFragmentCatDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomFragmentCatDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
        setClickListeners()
    }

    private fun renderView() {
        with(cat) {
            binding.catName.text = breeds.name
            binding.catCountry.text = getString(R.string.cat_detail_country, breeds.country)
            binding.catLifeSpan.text = getString(R.string.cat_detail_life_span, breeds.lifeSpan)
            binding.catTemperament.text = getString(R.string.cat_detail_temperament, breeds.temperament)
            binding.catDescription.text = getString(R.string.cat_detail_description, breeds.description)
            binding.catWiki.text = getString(R.string.cat_detail_wiki_url, breeds.wikiUrl) // TODO: impl clickable url 
            binding.catImage.load(url) {
                crossfade(durationMillis = 1500)
                transformations(RoundedCornersTransformation(12.5f))
            }
        }
    }

    private fun setClickListeners() {
        binding.catInfoClose.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}