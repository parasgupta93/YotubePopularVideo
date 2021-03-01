package com.paras.yotubepopularvideo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
    /**
     *  To check Network Connectivity
     */

    fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false
                }
            }
        }
        return result
    }

    fun showTitleWithView(str:String,views: String): String{
        val number: Long = views.toLong()
        val view = convertViews(number)
        return "$str   $view views"
    }

  private fun  convertViews(number:Long):String {

      return when {
          number >= 1000000000 -> String.format("%.2fB", number / 1000000000.0)
          number >= 1000000 -> String.format("%.2fM", number / 1000000.0)
          number >= 100000 -> String.format("%.2fL", number / 100000.0)
          number >= 1000 -> String.format("%.2fK", number / 1000.0)
          else -> String.format("%.2fB", number)

      }
  }


