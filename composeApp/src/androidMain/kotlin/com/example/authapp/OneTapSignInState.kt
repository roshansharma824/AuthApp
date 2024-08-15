package com.example.authapp

import AuthApp.composeApp.BuildConfig
import android.util.Log
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @param open Initial value of the state (whether [open] has been invoked)
 */
@Stable
class OneTapSignInState(open: Boolean = false) {
    var opened by mutableStateOf(open)
        private set

    fun open() {
        opened = true
    }

    internal fun close() {
        opened = false
    }
}
private const val TAG = "OneTapCompose"
fun signIn(onResponse: (message:String,token:String) -> Unit)  {
     val API_SECRET = BuildConfig.API_SECRET
    val credentialManager = CredentialManager.create(AppContext.get())
    // [START config_signin]
    val googleIdOption = GetGoogleIdOption
        .Builder()
        .setAutoSelectEnabled(true)
        .setFilterByAuthorizedAccounts(true)
        .setNonce(null)
        .setServerClientId(API_SECRET)
        .build()

    val request: GetCredentialRequest = GetCredentialRequest.Builder()
        .setCredentialOptions(listOf(googleIdOption))
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = credentialManager.getCredential(
                request = request,
                context = AppContext.get(),
            )
            handleSignIn(credentialResponse = result,
                onTokenIdReceived = {
                    Log.d(TAG, "token $it")
                    onResponse.invoke("LOGIN_SUCCESS",it)
                },
                onDialogDismissed = {

                    Log.e(TAG, it)
                    onResponse.invoke("LOGIN_FAILED",it)
                })
        } catch (e: GetCredentialException) {
            Log.e(TAG, "Exception:$e, ErrorMessage: ${e.errorMessage}")

        }catch (e: Exception) {
            Log.e(TAG, "Exception:$e, ErrorMessage: ${e.message}")

        }
    }

}