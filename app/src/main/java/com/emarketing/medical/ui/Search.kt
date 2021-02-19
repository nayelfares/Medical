package com.emarketing.medical.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.emarketing.medical.R
import com.emarketing.medical.data.*
import com.emarketing.medical.mvvm.BaseActivity
import com.emarketing.medical.vm.ResultAdapter
import com.emarketing.medical.vm.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*



class Search : BaseActivity(),SearchView{
    lateinit var searchViewModel: SearchViewModel
    var cities=ArrayList<City>()
    var categories=ArrayList<Category>()
    var tags =ArrayList<Tag>()
    val allCities=ArrayList<String>()
    val allCategories=ArrayList<String>()
    val allTages=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        getMore.setOnRefreshListener {
            getMore.setRefreshing(false)
        }
        searchViewModel=SearchViewModel(this, this)
        val fromCategory= intent.getBooleanExtra("fromCategory",false)
        val categoryId=intent.getLongExtra("categoryId",0)
        if (fromCategory){
            searchViewModel.search(null, categoryId, null)
            userPanel.visibility=View.GONE
        }else {
            initLists()
            searchViewModel.getFilters()
            searchViewModel.search(null, null, null)
        }
        loading()
    }

    fun initLists(){
        allCities.add(resources.getString(R.string.select_city))
        allCategories.add(resources.getString(R.string.select_category))
        allTages.add(resources.getString(R.string.select_tag))
        val citiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allCities)
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        city.adapter = citiesAdapter
        city.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2!=0){
                    var categoryId:Long?=null
                    if (category.selectedItemPosition!=0) {
                        categoryId = categories[category.selectedItemPosition-1].id
                    }
                    var tagId:Long?=null
                    if (tag.selectedItemPosition!=0)
                        tagId=tags[tag.selectedItemPosition-1].id
                    searchViewModel.search(cities[p2-1].id,categoryId,tagId)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        val categoriesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allCategories)
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        category.adapter = categoriesAdapter
        category.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2!=0){
                    var cityId:Long?=null
                    if (city.selectedItemPosition!=0) {
                        cityId = cities[city.selectedItemPosition-1].id
                    }
                    var tagId:Long?=null
                    if (tag.selectedItemPosition!=0)
                        tagId=tags[tag.selectedItemPosition-1].id
                    searchViewModel.search(cityId,categories[p2-1].id,tagId)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        val tagAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allTages)
        tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tag.adapter = tagAdapter
        tag.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2!=0){
                    var cityId:Long?=null
                    if (city.selectedItemPosition!=0) {
                        cityId = cities[city.selectedItemPosition-1].id
                    }
                    var categoryId:Long?=null
                    if (category.selectedItemPosition!=0) {
                        categoryId = categories[category.selectedItemPosition-1].id
                    }
                    searchViewModel.search(cityId,categoryId,tags[p2-1].id)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
    }

    override fun getFiltersOnFailer(message: String) {
        stopLoading()
        showMessage(message)
        finish()
    }

    override fun getFiltersOnSuccess(filters: Filters) {
        stopLoading()
        tags=filters.tags
        for (tag in tags)
            allTages.add(tag.name)
        categories=filters.categories
        for (category in categories)
            allCategories.add(category.name)
        cities=filters.cities
        for (city in cities)
            allCities.add(city.name)
    }

    override fun searchOnSuccess(data: ArrayList<Site>) {
        stopLoading()
        if (data.isEmpty())
            noResults.visibility=View.VISIBLE
        else
            noResults.visibility=View.GONE
        content.adapter=ResultAdapter(this,data)
    }

    override fun searchOnFailer(message: String) {
        stopLoading()
    }

}