package com.metatreasures.metatreasures

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.metatreasures.metatreasures.data.AuthRepository
import com.metatreasures.metatreasures.data.TokenRepository
import com.metatreasures.metatreasures.data.model.AuthViewModelFactory
import com.metatreasures.metatreasures.data.model.TokenViewModelFactory
import com.metatreasures.metatreasures.navigation.NavGraph
import com.metatreasures.metatreasures.ui.theme.MetaTreasuresTheme
import com.metatreasures.metatreasures.viewmodel.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private lateinit var themeSettings: ThemeSettings
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private lateinit var authViewModel: AuthViewModel
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeSettings = ThemeSettings(this)

        // 🔹 AuthViewModel
        val authFactory = AuthViewModelFactory(AuthRepository(auth))
        authViewModel = ViewModelProvider(this, authFactory)[AuthViewModel::class.java]

        // 🔹 TokenViewModel
        val tokenFactory = TokenViewModelFactory(TokenRepository())
        tokenViewModel = ViewModelProvider(this, tokenFactory)[TokenViewModel::class.java]

        // 🔹 Настройка Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // из google-services.json
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            var currentTheme by remember { mutableStateOf(AppThemeEnum.SYSTEM) }

            LaunchedEffect(Unit) {
                themeSettings.themeFlow.collectLatest { savedTheme ->
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
                        darkTheme = darkTheme,
                        onGoogleSignIn = { signInWithGoogle() },
                        authViewModel = authViewModel,
                        tokenViewModel = tokenViewModel   // 🔹 теперь передаём сюда
                    )
                }
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w("GoogleSignIn", "Google sign in failed", e)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleSignIn", "signInWithCredential:success")
                    authViewModel.loginWithGoogle(idToken)
                } else {
                    Log.w("GoogleSignIn", "signInWithCredential:failure", task.exception)
                }
            }
    }
}
