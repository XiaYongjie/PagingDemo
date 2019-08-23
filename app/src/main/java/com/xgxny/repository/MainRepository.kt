package com.xgxny.repository

import android.annotation.SuppressLint
import com.xgxny.BaseResult
import com.xgxny.api.BookList
import com.xgxny.bean.Books
import com.xgxny.bean.DemoRequest
import com.xgxny.net.RetrofitServiceManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository {
    @SuppressLint("CheckResult")
    fun getBookList(body: DemoRequest): Observable<BaseResult<Books>> {
        return RetrofitServiceManager.getInstance().create(BookList::class.java).getList(body)
            .subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            )
    }
}