import React, { useEffect, useState } from "react";
import './Success.scss';

function Success() {
    var [seconds,setSeconds] = useState(5);

    useEffect(()=> {
        setTimeout(()=> {
            setSeconds(seconds-1)},1000);
    },[seconds])

    useEffect(()=> {
        setTimeout(()=> {
            window.location.replace("/login")},5000);
    },[]);

    return(
        <div className="success">
            <h2>회원가입 성공😊</h2>
            <p>{seconds}초 후 로그인 페이지로 이동합니다...</p></div>
    )
}

export default Success;