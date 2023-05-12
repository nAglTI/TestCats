package com.nagl.test_cats_task

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    // TODO: solve warning
    companion object {
        private lateinit var mContext: Context

        @JvmStatic
        fun getContext(): Context {
            return mContext
        }
    }
}