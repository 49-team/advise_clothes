import React from "react";
import { Link, Route, Switch } from 'react-router-dom';
import { useCookies } from "react-cookie";

function isLogin() {
    // return !!localStorage.getItem("account");    cookie 활용을 위해 제거
    return document.cookie.split(';').filter(x => x.split("=")[0].trim() === 'auth').length != 0;
}
export default isLogin;