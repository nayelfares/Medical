package com.emarketing.medical.data

import java.util.ArrayList

data class City(val id:Long,val name:String)

data class Filters(val cities:ArrayList<City>,val categories:ArrayList<Category>,val tags:ArrayList<Tag>)