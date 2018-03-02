package com.personaltrainer

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Honza on 26.02.2018.
 */
public object Auth {

    var authStateListener: AuthStateListener? = null
        set(value) {
            if (value != null) {
                getFBInstance().addAuthStateListener(firebaseAuthStateListener)
            } else {
                getFBInstance().removeAuthStateListener(firebaseAuthStateListener)
            }
            field = value
        }

    private val firebase: FirebaseAuth by lazy<FirebaseAuth>({ FirebaseAuth.getInstance() })

    private fun getFBInstance() = firebase

    fun isSigned() : Boolean = getFBInstance().currentUser != null

    fun signInWithEmailAndPassword(email: String, password: String, action: (success: Boolean) -> Unit) {
        getFBInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                    action(task.isSuccessful)
            }
    }

    fun signOut() {
        getFBInstance().signOut()
    }

    private val firebaseAuthStateListener: FirebaseAuth.AuthStateListener = FirebaseAuth.AuthStateListener {
        authStateListener?.authStateChanged(it.currentUser != null)
    }

    interface AuthStateListener {
        fun authStateChanged(isUserSigned: Boolean)
    }

    fun AuthStateListener(function: (Boolean) -> Unit) : AuthStateListener {
        return object : AuthStateListener {
            override fun authStateChanged(isUserSigned: Boolean) {
                function(isUserSigned)
            }
        }
    }
}
