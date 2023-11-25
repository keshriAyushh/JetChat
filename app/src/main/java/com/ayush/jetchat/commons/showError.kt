package com.ayush.jetchat.commons

import android.content.Context
import android.widget.Toast

fun showError(ctx: Context, msg: String) =
    Toast
        .makeText(ctx, msg, Toast.LENGTH_SHORT)
        .show()
