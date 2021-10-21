package com.example.cakes.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakes.adapter.CakeAdapter
import com.example.cakes.databinding.ActivityCakeBinding
import com.example.cakes.interfaces.ItemClickListener
import com.example.cakes.model.CakeListItem
import com.example.cakes.services.isOnline
import com.example.cakes.viewmodel.CakeListingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CakeListingActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var binding: ActivityCakeBinding
    private lateinit var context: Context
    private lateinit var adapter: CakeAdapter
    lateinit var viewModel : CakeListingViewModel
    private var cakeData: List<CakeListItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CakeListingViewModel::class.java)
        binding = ActivityCakeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@CakeListingActivity
        supportActionBar!!.hide()
        loadData()

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun loadData() {
        if (isOnline(context)) {
            callCakeList()

        } else {
            binding.progressBar.visibility = View.GONE
            val snackbar =
                Snackbar.make(binding.constraint, "No Network", Snackbar.LENGTH_LONG)
            snackbar.setAction(
                "RETRY"
            ) { callCakeList() }
            snackbar.show()
        }
    }

    private fun callCakeList() {
        viewModel.getCakes()
            .observe(context as CakeListingActivity, { cakeList ->

                if (cakeList != null) {
                    cakeData=removeDuplicates(cakeList)
                    cakeData = viewModel.sortAlphabetically(cakeData!!)
                    binding.cakeRecycler.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter =
                        CakeAdapter(
                            context,
                            cakeData!!, context as CakeListingActivity
                        )
                    binding.cakeRecycler.adapter = adapter
                    adapter.setCakeList(cakeData!!)
                    binding.progressBar.visibility = View.GONE

                } else {
                    val snackbar =
                        Snackbar.make(
                            binding.constraint,
                            "Something went wrong!",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.setAction(
                        "RETRY"
                    ) { callCakeList() }
                    snackbar.show()
                }

            })
    }

    private fun removeDuplicates(cakeList: List<CakeListItem>): List<CakeListItem> {
       val cakeList= cakeList.toSet().toList()
        return cakeList
    }

    override fun onClick(title: String) {
        displayPopUp(title)
    }

    private fun displayPopUp(title: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()


    }


}