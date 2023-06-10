package com.example.gentevent.ui.screens.friends

data class FriendsUistate (
    var friends: List<Friend> = listOf(
        Friend(
            id = 1,
            name = "Bob",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy",
        ),

    ),
    var friendRequests: List<Friend> = listOf(
        Friend(
            id = 2,
            name = "Jef",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy",
        ),
        Friend(
            id = 3,
            name = "Dirk",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy",
        ),
        ),

    var searchedFriends: List<Friend> = listOf(),
    var clickedFriend: Friend? = null,
)

class Friend(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    var events: Int = (0..10).random(),
)