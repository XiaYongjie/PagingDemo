package com.xgxny.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xgxny.bean.Books
import com.xgxny.datasource.BookDataSource
import com.xgxny.datasource.BookDataSource.Companion.NUM_PER_PAGE
import com.xgxny.datasource.PageDataSourceFactory

class MainViewModel(app: Application) : AndroidViewModel(app) {


    private lateinit var mLiveData: LiveData<PagedList<Books.BooksBean>>
    private lateinit var mDataSource: BookDataSource
    fun getLiveData(): LiveData<PagedList<Books.BooksBean>>? {
        if (!::mLiveData.isInitialized) {
            initPagedList()//初始化PagedList
        }
        return mLiveData
    }


    @SuppressLint("CheckResult")
    private fun initPagedList() {
        mDataSource = BookDataSource()

        mLiveData = LivePagedListBuilder(
            PageDataSourceFactory(mDataSource),
            PagedList.Config.Builder().setPageSize(NUM_PER_PAGE).setPrefetchDistance(NUM_PER_PAGE)
                .setEnablePlaceholders(false).setInitialLoadSizeHint(NUM_PER_PAGE).build()
        ).build()
    }

    fun refresh(): LiveData<PagedList<Books.BooksBean>> {
        mLiveData = LivePagedListBuilder(
            PageDataSourceFactory(mDataSource),
            PagedList.Config.Builder().setPageSize(NUM_PER_PAGE).setPrefetchDistance(NUM_PER_PAGE)
                .setEnablePlaceholders(false).setInitialLoadSizeHint(NUM_PER_PAGE).build()
        ).build()
        return mLiveData
    }

}