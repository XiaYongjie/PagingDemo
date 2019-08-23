package com.xgxny

class BaseResult<T> {
    var code: Int = 0
    var data: T? = null
    var msg: String? = null
}
