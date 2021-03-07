package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel3task2.databinding.FragmentPortalsBinding

class PortalsFragment : Fragment() {

    private var _binding: FragmentPortalsBinding? = null
    private val binding get() = _binding!!

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portal: Portal -> portalClicked(portal) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()
    }

    // Clean up references of the binding class instance of the fragment.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Open the URL in a browser when the corresponding portal is clicked.
    private fun portalClicked(portal : Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this.context, Uri.parse(portal.url))
    }

    // Specify the layout and adapter of the RecyclerView.
    private fun initViews() {
        binding.rvPortals.layoutManager = GridLayoutManager(context, 2)
        binding.rvPortals.adapter = portalAdapter
    }

    // Retrieve the title and URL of the added portal and add it to the RecyclerView.
    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = it
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalFragment", "Request triggered, but invalid portal!")
        }
    }
}