package com.manorlds.exoplayer_app

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.NonNull
import com.manorlds.exoplayer_app.PlayerControl.getCurrentPosition
import com.manorlds.exoplayer_app.PlayerControl.pausePlayer
import com.manorlds.exoplayer_app.PlayerControl.releasePlayer
import com.manorlds.exoplayer_app.PlayerControl.seekTimePlay
import com.manorlds.exoplayer_app.PlayerControl.stopPlayer
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import java.lang.Boolean

class MainActivity: FlutterActivity() {


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        super.configureFlutterEngine(flutterEngine)
        flutterEngine
                .platformViewsController
                .registry
                .registerViewFactory("view1", NativeViewFactory())


        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, Companion.PLAYERCHANNEL)
                .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "releasePlayer") {
                        releasePlayer()
                    }
                    if(call.method == "currentPosition"){
                        result.success(getCurrentPosition())
                    }
                    if(call.method == "stopPlayer"){
                        stopPlayer()
                    }
                    if(call.method == "pausePlayer"){
                        pausePlayer()
                    }
                    if(call.method == "playFromTime"){
                        val seekTo: Long? = call.argument<Long>("seekTo")
                        if (seekTo != null) {
                            seekTimePlay(seekTo)
                        }
                    }
                    else {
                        result.notImplemented()
                    }
                }
    }

    companion object {
        private const val PLAYERCHANNEL = "vision.flutter.dev/exoplayer"
    }
}
