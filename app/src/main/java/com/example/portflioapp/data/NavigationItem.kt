package com.example.portflioapp.data

data class NavigationItem(
    val title:String,
)

data class Experience(
    val title:String = "Title",
    val organization:String = "Organization",
    val fromTo:String = "2020-2021",
    val location:String = "Location",
)

data class Research(
    val title:String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
    val author:String = "You, Earth-616 you",
    val published:String = "The Playboy: 1942",
    val citation:Int = 300,
    val year:Int = 1920,
)
