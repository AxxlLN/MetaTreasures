package com.metatreasures.metatreasures.auth

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.metatreasures.metatreasures.viewmodel.AuthViewModel

class GoogleAuthManager(
    private val activity: Activity,
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth,
    private val authViewModel: AuthViewModel
) {

    fun signInWithGoogle(launcher: ActivityResultLauncher<android.content.Intent>) {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    fun handleResult(data: android.content.Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w("GoogleSignIn", "Google sign in failed", e)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        authViewModel.setLoading(true)
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleSignIn", "signInWithCredential:success")
                    auth.currentUser?.getIdToken(true)?.addOnSuccessListener { result ->
                        val firebaseToken = result.token
                        authViewModel.onGoogleLoginSuccess(firebaseToken ?: "")
                    }
                } else {
                    Log.w("GoogleSignIn", "signInWithCredential:failure", task.exception)
                }
            }
    }
}
