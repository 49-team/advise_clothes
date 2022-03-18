import React, { useEffect } from "react";
import './Mypage.css';
import Login from "./Login";
import { useCookies } from "react-cookie";

function Mypage(props) {
    let co = { fontSize: "20px" };
    const [cookies, setCookies, removeCookie] = useCookies(['info']);

    return(
        <div className='mypage'>
            <div style={co}>Mypage<hr/></div>
            <div className="info">아이디 : {cookies.info.account}
                <p>닉네임 : {cookies.info.nickname}</p>
                <p>email : {cookies.info.email}</p>
                <p>전화번호 : {cookies.info.phoneNumber}</p>
            </div>
        </div>
    )
}

export default Mypage;