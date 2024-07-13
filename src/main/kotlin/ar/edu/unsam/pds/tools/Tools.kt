package ar.edu.unsam.pds.tools

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

fun clearCookies(request: HttpServletRequest, response: HttpServletResponse) {
    request.cookies?.let {it.forEach { cookie ->
        cookie.maxAge = 0
        cookie.value = null
        cookie.path = "/"
        response.addCookie(cookie)
    }}
}