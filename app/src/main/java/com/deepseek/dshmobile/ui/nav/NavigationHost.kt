package com.deepseek.dshmobile.ui.nav

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deepseek.dshmobile.ui.screens.ChatScreen
import com.deepseek.dshmobile.ui.screens.SettingsScreen
import com.deepseek.dshmobile.ui.screens.SessionListScreen
import kotlinx.coroutines.flow.StateFlow

sealed class Screen(val route: String) {
    object Sessions : Screen("sessions")
    object Chat : Screen("chat/{sessionId}") {
        fun createRoute(sessionId: String) = "chat/$sessionId"
    }
    object Settings : Screen("settings")
}

@Composable
fun NavigationHost(
    viewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryFlow
        .map { it.destination.route ?: "sessions" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "sessions")

    NavHost(
        navController = navController,
        startDestination = Screen.Sessions.route
    ) {
        composable(Screen.Sessions.route) {
            val sessions by viewModel.sessions.collectAsState()
            SessionListScreen(
                sessions = sessions,
                onSessionClick = { sessionId ->
                    navController.navigate(Screen.Chat.createRoute(sessionId))
                },
                onNewSession = {
                    viewModel.createSession("New Session")
                },
                onDeleteSession = { sessionId ->
                    viewModel.deleteSession(sessionId)
                },
                onSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateUp = { navController.popBackStack() }
            )
        }
        composable(
            route = "chat/{sessionId}",
            arguments = listOf(navArgument("sessionId") { defaultValue = "" })
        ) { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId") ?: ""
            val messages by viewModel.getMessages(sessionId).collectAsState(initial = emptyList())
            val isLoading by viewModel.isLoading.collectAsState()

            ChatScreen(
                sessionId = sessionId,
                messages = messages,
                isLoading = isLoading,
                onSend = { text ->
                    viewModel.sendMessage(sessionId, text)
                },
                onNavigateUp = { navController.popBackStack() }
            )
        }
    }
}
