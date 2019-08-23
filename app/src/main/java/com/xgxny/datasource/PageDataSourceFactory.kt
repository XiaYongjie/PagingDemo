package com.xgxny.datasource

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource


class PageDataSourceFactory<T>(positionalDataSource: PositionalDataSource<T>) :
    DataSource.Factory<Int, T>() {
    private var mPositionalDataSource: PositionalDataSource<T>? = null

    init {
        this.mPositionalDataSource = positionalDataSource
    }

    override fun create(): DataSource<Int, T> {
        return mPositionalDataSource as DataSource<Int, T>
    }
}