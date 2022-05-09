import React from "react";
import { Cookies, useCookies } from "react-cookie";

// npm i js-cookie 모듈 설치

function isLogin() {
    // return !!localStorage.getItem("account");
    return !!Cookies.get('auth');
};

export default isLogin