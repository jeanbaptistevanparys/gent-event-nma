package com.example.gentevent.ui.screens.friends

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FriendsViewModelTest {
    private lateinit var viewModel: FriendsViewModel

    @Before
    fun setup() {
        viewModel = FriendsViewModel()
    }


    @Test
    fun addFriend_updatesUiState() = runBlockingTest {
        val friend = Friend(
            id = 4,
            name = "Alice",
            image = "https://example.com/alice.jpg",
            description = "Alice is a great friend"
        )

        val pastState = viewModel.uiState.value
        viewModel.addFriend(friend)
        val currentState = viewModel.uiState.value

        assertEquals(
            pastState.friends + friend,
            currentState.friends
        )
    }

    @Test
    fun removeFriend_updatesUiState() = runBlockingTest {

        for (friend in viewModel.uiState.value.friends) {
            viewModel.removeFriend(friend)
        }
        assertEquals(emptyList<Friend>(), viewModel.uiState.value.friends)
    }

    @Test
    fun searchFriends_updatesUiState() = runBlockingTest {
        for (friend in viewModel.uiState.value.friends) {
            viewModel.removeFriend(friend)
        }
        val friend1 = Friend(
            id = 1,
            name = "Bob",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy"
        )
        val friend2 = Friend(
            id = 2,
            name = "Jef",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy"
        )
        val friend3 = Friend(
            id = 3,
            name = "Dirk",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy"
        )
        viewModel.addFriend(friend1)
        viewModel.addFriend(friend2)
        viewModel.addFriend(friend3)

        viewModel.searchFrieds("Bob")
        var currentState = viewModel.uiState.value
        assertEquals(friend1, currentState.searchedFriends[0])

        viewModel.searchFrieds("Jef")
        currentState = viewModel.uiState.value
        assertEquals(friend2, currentState.searchedFriends[0])

        viewModel.searchFrieds("Dirk")
        currentState = viewModel.uiState.value
        assertEquals(friend3, currentState.searchedFriends[0])
    }

    @Test
    fun clickFriend_updatesUiState() = runBlockingTest {
        val friend = Friend(
            id = 1,
            name = "Bob",
            image = "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
            description = "Jef is a cool guy"
        )
        viewModel.clickFriend(friend)
        val currentState = viewModel.uiState.value

        assertEquals(friend, currentState.clickedFriend)
    }
}
