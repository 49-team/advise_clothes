import React from "react";

function isLogin() {
    return !!localStorage.getItem("account");
};

export default isLogin