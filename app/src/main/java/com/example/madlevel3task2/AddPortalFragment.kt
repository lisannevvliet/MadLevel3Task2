package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.madlevel3task2.databinding.FragmentAddPortalBinding

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

class AddPortalFragment : Fragment() {

    private var _binding: FragmentAddPortalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPortalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Start the onAddPortal function when the "Add portal" button is clicked.
        binding.btnAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    // Clean up references of the binding class instance of the fragment.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Save the title and URL of the added portal.
    private fun onAddPortal() {
        val title = binding.etTitle.text.toString()
        val url = binding.etUrl.text.toString()
        val portal = Portal(title, url)

        if (title.isNotBlank() && url.isNotBlank() && url != "http://") {
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))
            // Clear the back stack, so the user can not navigate back to the AddPortalFragment.
            findNavController().popBackStack()
        } else {
            // Show the message "Invalid portal" if the title and URL are not properly filled in.
            Toast.makeText(activity, R.string.invalid_portal, Toast.LENGTH_SHORT).show()
        }
    }
}