package com.manorlds.exoplayer_app

import android.content.Context
import android.view.View
import com.manorlds.exoplayer_app.PlayerControl.getThePlayerView
import com.manorlds.exoplayer_app.PlayerControl.initializePlayer
import io.flutter.plugin.platform.PlatformView


internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) : PlatformView {

    override fun getView(): View {
        return getThePlayerView()
    }

    override fun dispose() {}

    init {
        initializePlayer(context, creationParams?.get("link").toString(), creationParams?.get("seekTo").toString(),
                creationParams?.get("isStream").toString() == "true")
    }
}