package com.xgxny.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PositionalDataSource
import com.xgxny.bean.Books
import com.xgxny.bean.DemoRequest
import com.xgxny.repository.MainRepository

class BookDataSource : PositionalDataSource<Books.BooksBean>() {

    companion object {
        const val NUM_PER_PAGE = 10
        private const val PAGE_FIRST = 1
    }


    private var mPage = PAGE_FIRST
    private var list: MutableList<Books.BooksBean> = ArrayList()
    private var mRepository: MainRepository = MainRepository()
    @SuppressLint("CheckResult")
    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<Books.BooksBean>
    ) {
        mPage++
        val body = DemoRequest().apply {
            cat = -1
            pageSize = NUM_PER_PAGE
            page = mPage
        }
        mRepository.getBookList(body).subscribe {
            if (it.code == 0 && it.data != null) {
                list.addAll(it.data!!.books)
                Log.e("xyj", "分页了")
                callback.onResult(list)
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<Books.BooksBean>
    ) {

        val position = computeInitialLoadPosition(params, NUM_PER_PAGE)
        val body = DemoRequest().apply {
            cat = -1
            pageSize = NUM_PER_PAGE
            page = 1
        }
        mRepository.getBookList(body).subscribe {
            if (it.code == 0 && it.data != null) {
                Log.e("xyj", "重新开始加载了")
                list.clear()
                list.addAll(it.data!!.books)
                callback.onResult(list, position)
            }
        }
    }

}
