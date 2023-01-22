package com.zakiralekperov.android.tictactoe.constant

object GameActivityGonstant {
    const val STATUS_WIN_PLAYER = 1
    const val STATUS_WIN_BOT = 2
    const val STATUS_DRAW = 3
    const val POPUP_MENU = 235

    val scores = hashMapOf(
        Pair(STATUS_WIN_PLAYER, -1.0), Pair(STATUS_WIN_BOT, 1.0), Pair(STATUS_DRAW, 0.0)
    )

    const val PLAYER_SYMBOL = "X"
    const val BOT_SYMBOL = "0"
}