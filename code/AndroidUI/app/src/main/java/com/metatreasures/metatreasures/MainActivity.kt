package com.metatreasures.metatreasures

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.metatreasures.metatreasures.auth.GoogleAuthManager
import com.metatreasures.metatreasures.navigation.NavGraph
import com.metatreasures.metatreasures.ui.theme.MetaTreasuresTheme
import com.metatreasures.metatreasures.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.metatreasures.metatreasures.datastore.AppThemeEnum
import com.metatreasures.metatreasures.datastore.ThemeSettings

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var themeSettings: ThemeSettings
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var googleAuthManager: GoogleAuthManager

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        googleAuthManager.handleResult(result.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeSettings = ThemeSettings(this)
        authViewModel.checkLoggedIn()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleAuthManager = GoogleAuthManager(this, googleSignInClient, auth, authViewModel)

        setContent {
            var currentTheme by remember { mutableStateOf(AppThemeEnum.SYSTEM) }

            LaunchedEffect(Unit) {
                themeSettings.themeFlow.collect { savedTheme ->
                    currentTheme = savedTheme
                }
            }

            val darkTheme = when (currentTheme) {
                AppThemeEnum.LIGHT -> false
                AppThemeEnum.DARK -> true
                AppThemeEnum.SYSTEM -> isSystemInDarkTheme()
            }

            MetaTreasuresTheme(darkTheme = darkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController,
                        onGoogleSignIn = { googleAuthManager.signInWithGoogle(launcher) },
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}

