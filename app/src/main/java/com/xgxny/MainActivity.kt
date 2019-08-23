package com.xgxny

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.xgxny.adapter.BookListAdapter
import com.xgxny.utils.RecycleViewDivider
import com.xgxny.utils.Utils
import com.xgxny.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnRefreshListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: BookListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initAdapter()

    }

    private fun initAdapter() {
        mAdapter = BookListAdapter()
        rv_books.adapter = mAdapter
        rv_books.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        rv_books.addItemDecoration(
            RecycleViewDivider(
                this,
                3,
                Utils.dp2px(this, 5f),
                Color.parseColor("#ffffff")
            )
        )
        viewModel.getLiveData()?.observe(this, Observer {
            mAdapter.submitList(it)
        })
        srf_refresh.setOnRefreshListener(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.refresh().observe(this, Observer {
            mAdapter.submitList(it)
            srf_refresh.finishRefresh()
        })

    }


}
