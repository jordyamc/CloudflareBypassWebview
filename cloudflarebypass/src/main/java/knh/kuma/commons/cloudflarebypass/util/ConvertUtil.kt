package knh.kuma.commons.cloudflarebypass.util

import java.net.HttpCookie
import java.util.*

object ConvertUtil {
    /**
     * 转换list为 ; 符号链接的字符串
     * @param list 列表
     * @return string
     */
    @JvmStatic
    fun listToString(list: List<HttpCookie>): String {
        val separator = ";"
        val sb = StringBuilder()
        for (i in list.indices) {
            sb.append(list[i].name).append("=").append(list[i].value).append(separator)
        }
        return sb.toString().dropLastWhile { it == ' ' || it == ';' }
    }

    /**
     * 转换为jsoup可用的Hashmap
     * @param list  HttpCookie列表
     * @return Hashmap
     */
    fun List2Map(list: List<HttpCookie>?): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        try {
            if (list != null) {
                for (i in list.indices) {
                    val listStr = list[i].toString().split("=".toRegex()).toTypedArray()
                    map[listStr[0]] = listStr[1].replace("\"", "")
                }
            } else {
                return map
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
        return map
    }

    /**
     * 转换String为List
     * @param cookie httpCookie
     * @return List<HttpCookie>
    </HttpCookie> */
    @JvmStatic
    fun String2List(cookie: String): List<HttpCookie> {
        if (cookie.isBlank()) return emptyList()
        val list: MutableList<HttpCookie> = ArrayList()
        val listStr = cookie.split(";".toRegex()).toTypedArray()
        for (str in listStr) {
            val cookieStr = str.split("=".toRegex()).toTypedArray()
            list.add(HttpCookie(cookieStr[0], cookieStr[1]))
        }
        return list
    }
}