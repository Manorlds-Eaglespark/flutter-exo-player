package com.manorlds.exoplayer_app

import android.content.Context
import android.view.View
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes

object PlayerControl {

    private lateinit var playerView: PlayerView
    private lateinit var player: SimpleExoPlayer
    private var playWhenReady = true

    fun getThePlayerView(): View {
        return playerView.rootView;
    }


    fun initializePlayer(context: Context, mediaLink: String, seekTo: String, isVideoStream: Boolean) {
        playerView = PlayerView(context)

        val trackSelector = DefaultTrackSelector(context)
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())

        val loadControl: DefaultLoadControl = DefaultLoadControl.Builder().build()
        player = SimpleExoPlayer.Builder(context)
                .setTrackSelector(trackSelector)
                .setLoadControl(loadControl)
                .build()

        playerView.player = player

        val mediaItem: MediaItem = MediaItem.Builder()
                .setUri(mediaLink)
                .setMimeType(if(isVideoStream) MimeTypes.APPLICATION_M3U8 else MimeTypes.VIDEO_UNKNOWN)
                .build()

        player.setMediaItem(mediaItem)


        if (seekTo.toLong() > 0) {
            player.seekTo(seekTo.toLong())
        }
        playerView.useController = false;
        player.playWhenReady = playWhenReady
        player.prepare()
    }


    fun pausePlayer() {
        player.pause()
    }

    fun stopPlayer() {
        player.stop()
    }

    fun seekTimePlay(seekTo: Long) {
        player.seekTo(seekTo)
    }

    fun releasePlayer() {
        player.stop()
    }

    fun getCurrentPosition(): Long {
        return player.currentPosition
    }
}
