package com.emarketing.medical.ui

import com.emarketing.medical.data.Category

interface GeneralBrowsingView {
    fun onFailer(toString: String)
    fun onSuccess(categories: ArrayList<Category>)
}