package com.emarketing.medical.ui

import com.emarketing.medical.data.Article

interface ArticalsView {
    fun onFailer(toString: String)
    fun onSuccess(categories: ArrayList<Article>)
}