package com.zzs.wanandroidkt

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.zzs.wanandroidkt.Constant.Constant
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred

/**
 * @author: zzs
 * @date: 2019/3/13
 */

fun encodeCookie(cookies: List<String>): String {
    val sb = StringBuilder()
    val set = HashSet<String>()
    cookies.map { cookie ->
        cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }
        .forEach {
            it.filterNot { set.contains(it) }
                .forEach { set.add(it) }
        }
    val ite = set.iterator()
    while (ite.hasNext()) {
        val cookie = ite.next()
        sb.append(cookie).append(";")
    }

    val last = sb.lastIndexOf(";")
    if (sb.length - 1 == last) {
        sb.deleteCharAt(last)
    }
    return sb.toString()
}

fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: tag)
}

/**
 * show toast
 * @param content String
 */
@SuppressLint("ShowToast")
fun Context.toast(content: String) {
    Constant.showToast?.apply {
        setText(content)
        show()
    } ?: run {
        Toast.makeText(this.applicationContext, content, Toast.LENGTH_SHORT).apply {
            Constant.showToast = this
        }.show()
    }
}

inline fun tryCatch(catchBlock: (Throwable) -> Unit = {}, tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: CancellationException) {

    } catch (t: Throwable) {
        catchBlock(t)
    }
}

fun Deferred<Any>?.cancelByActivity() = this?.run {
    tryCatch { if (isActive) {
        cancel()
    } }
}