import React, { useEffect, useState } from "react";
import './Mypage.css';
import Login from "./Login";
import { useCookies } from "react-cookie";
import axios from "axios";
import { Modal } from "react-bootstrap";

function Mypage(props) {
    let co = { fontSize: "20px" };
    const [cookies, setCookies, removeCookie] = useCookies(['info'],['nickC']);
    let [change, setChange] = useState(false);
    let [nickChange, setNickChange] = useState('');
    let [btn, setBtn] = useState(true);

    const onNickChangeHandler = (e) => {
        setNickChange(e.target.value);
    }

    return(
        <div className='mypage'>
            <div style={co}>Mypage<hr/></div>
            <div className="info">
                아이디 : {cookies.info.account}

                {/* 닉네임 변경 */}
                <p>닉네임 : {cookies.info.nickname}
                    {change? <div>
                        <input name="nick" type="text" value={nickChange} onChange={onNickChangeHandler} placeholder="변경하실 닉네임을 입력해주세요"/>

                        <button onClick={()=> {
                            const fetch = async() => {
                                try {
                                    const chan = {
                                        nickname : nickChange
                                    }
                                    await axios.put(`http://localhost:8080/api/users/${cookies.info.account}`, chan);
                                }
                                catch (e) {
                                    console.log(e);
                                }
                            }
                            fetch()
                            setChange(null);
                        }}>저장</button></div> : null}

                    {btn? <button onClick={()=>{
                        setChange(!nickChange);

                    }}>닉네임 변경</button>: null}</p>

                <p>email : {cookies.info.email} <button>이메일 변경</button></p>

                <p>전화번호 : {cookies.info.phoneNumber} <button>전화번호 변경</button></p>

            </div>
        </div>
    )
}

export default Mypage;