package com.creedeon.creed003

import com.google.firebase.database.IgnoreExtraProperties
@IgnoreExtraProperties
class student (
    var id:String? ="",
    var name:String? = "",
    var grp:String? = "",
    var std:Int = 11,
    var pass:String ="",
    var dates: ArrayList<Long>?=null
) {
    fun toMap():Map<String,Any?>{
            return mapOf(
                "id" to id,
                "name" to name,
                "grp" to grp,
                "std" to std,
                "dates" to dates
            )

    }
}

