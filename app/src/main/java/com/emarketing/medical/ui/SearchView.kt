package com.emarketing.medical.ui

import com.emarketing.medical.data.Filters
import com.emarketing.medical.data.Site

interface SearchView {
    fun getFiltersOnFailer(message: String)
    fun getFiltersOnSuccess(filters: Filters)
    fun searchOnSuccess(data: ArrayList<Site>)
    fun searchOnFailer(message: String)
}