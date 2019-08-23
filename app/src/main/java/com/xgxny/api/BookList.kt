package com.xgxny.api

import com.xgxny.BaseResult
import com.xgxny.bean.Books
import com.xgxny.bean.DemoRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BookList {
    @Headers("Content-Type:application/json;charset=utf-8", "Accept:application/json;")
    @POST("book/list")
    fun getList(@Body body: DemoRequest): Observable<BaseResult<Books>>
}