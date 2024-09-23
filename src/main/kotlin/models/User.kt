package models

data class User (
    var id: Int = -1,
    var name:String = "no name yet",
    var email:String = "no email yet",
    var weight:Double = 12.0,
    var height:Float = 3.123f,
    var gender:String = ""
    )
