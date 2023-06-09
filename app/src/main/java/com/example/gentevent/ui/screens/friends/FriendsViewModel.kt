package com.example.gentevent.ui.screens.friends

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FriendsViewModel {
    private val _uiState = MutableStateFlow(FriendsUistate())
    val uiState: StateFlow<FriendsUistate> = _uiState.asStateFlow()


    fun addFriend(friend: Friend) {
        _uiState.update { currentState ->
            currentState.copy(
                friends = currentState.friends + friend,
                friendRequests = currentState.friendRequests - friend
            )
        }
    }

    fun removeFriend(friend: Friend) {
        _uiState.update { currentState ->
            currentState.copy(
                friends = currentState.friends - friend
            )
        }
    }

    fun removeFriendRequest(friend: Friend) {
        _uiState.update { currentState ->
            currentState.copy(
                friendRequests = currentState.friendRequests - friend
            )
        }
    }

    fun searchFrieds(search: String) {
        if (search.isEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    searchedFriends = emptyList()
                )
            }
        }else _uiState.update { currentState ->
            currentState.copy(
                searchedFriends = currentState.friends.filter { it.name.contains(search) }
            )
        }
    }

    fun clickFriend(friend: Friend) {
        _uiState.update { currentState ->
            currentState.copy(
                clickedFriend = friend
            )
        }
    }

}