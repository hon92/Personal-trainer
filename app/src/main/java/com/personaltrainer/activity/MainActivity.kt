package com.personaltrainer.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.personaltrainer.Auth
import com.personaltrainer.R
import com.personaltrainer.adapter.ExerciseAdapter
import com.personaltrainer.adapter.ExtendedRecyclerViewAdapter
import com.personaltrainer.model.Exercise
import com.personaltrainer.model.ExerciseCategory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Honza on 11.02.2018.
 */
class MainActivity: AppCompatActivity() {

    private val authListener: Auth.AuthStateListener = Auth.AuthStateListener {
        isSigned -> if (!isSigned) redirectToLoginActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        extended_list_view.layoutManager = LinearLayoutManager(this)
        extended_list_view.emptyView = textView

        val exerciseAdapter = ExerciseAdapter(this)

        extended_list_view.adapter = exerciseAdapter
        for (i in 1..100) {
            exerciseAdapter.addItem(Exercise("test $i", "desc", ExerciseCategory.WEIGHT, null))
        }

        exerciseAdapter.itemClickListener = object : ExtendedRecyclerViewAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                val item = exerciseAdapter.getItem(position)
                println(item?.name)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Auth.authStateListener = authListener
    }

    override fun onStop() {
        super.onStop()
        Auth.authStateListener = null
    }

    private fun redirectToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}