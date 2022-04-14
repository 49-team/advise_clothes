import React, { useEffect, useState } from "react";
import './Mypage.css';
import Login from "../Login";
import { useCookies } from "react-cookie";
import axios from "axios";
import { Modal } from "react-bootstrap";

function Mypage() {
    let co = { fontSize: "20px" };
    const [cookies, setCookies, removeCookie] = useCookies(['info']);
    let [change, setChange] = useState(false);
    let [nickChange, setNickChange] = useState('');
    let [emailChange, setEmailChange] = useState('');
    let [phoneChange, setPhoneChange] = useState('');

    let [btn, setBtn] = useState(false);
    let [removeBtn, setRemoveBtn] = useState(false);
    let [remove, setRemove] = useState(false);
    let [removeInput, setRemoveInput] = useState('');

    const [phoneError, setPhoneError] = useState(false);
    const [removeInputError, setRemoveInputError] = useState(false);

    const URL = `/api/users/${cookies.info.account}`;

    const onNickChangeHandler = (e) => {
        setNickChange(e.target.value);
    }
    const onEmailChangeHandler = (e) => {
        setEmailChange(e.target.value);
    }
    const onPhoneChangeHandler = (e) => {
        const phone = /^[0-9\b -]{0,13}$/;
        if(phone.test(e.target.value)) {
            setPhoneError(e.target.value.length < 11);
            setPhoneChange(e.target.value);
        }
    }

    useEffect(()=> {
        if (phoneChange.length === 11) {
            setPhoneChange(phoneChange.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3'));
        }
    },[phoneChange]);

    const onRemoveInputHandler = (e) => {
        setRemoveInput(e.target.value);
    }

    return(
        <div className='mypage'>
            <div style={co}>Mypage<hr/></div>
            <div className="info">
                아이디 : {cookies.info.account}

                {/* 정보 변경 */}
                <p>닉네임 : {cookies.info.nickname}</p>

                <p>email : {cookies.info.email}</p>

                <p>전화번호 : {cookies.info.phoneNumber}</p>

                {btn? null : <button onClick={()=>{
                    setChange(!change);
                    setBtn(!btn)

                }}>정보 변경</button>}

                {change? <div>
                    <input name="nick" type="text" value={nickChange} onChange={onNickChangeHandler} placeholder="변경하실 닉네임을 입력해주세요"/>
                    <p/><input name="email" type="email" value={emailChange} onChange={onEmailChangeHandler} placeholder="변경하실 이메일을 입력해주세요"/>

                    <p/><input name="phone" type="text" value={phoneChange} onChange={onPhoneChangeHandler} placeholder="변경하실 전화번호를 입력해주세요"/>
                    {phoneError && <div style={{color : 'red'}}>정확한 전화번호를 입력해주세요!!</div>}


                    <p/><button onClick={()=> {
                    setBtn(!btn)

                    const fetch = async() => {
                        try {

                            const chan = {
                                nickname : (!nickChange)? cookies.info.nickname : nickChange,
                                email : (!emailChange)? cookies.info.email : emailChange,
                                phoneNumber : (!phoneChange)? cookies.info.phoneNumber : phoneChange
                            }
                            const res = await axios.put(URL, chan);

                            if (res.status === 200) {
                                setCookies('info', res.data);
                            }
                        }
                        catch (e) {
                            console.log(e);
                        }
                    }
                    fetch()
                    setChange(null);
                }}>저장</button></div> : null}
                <p/>

                {/* 탈퇴 */}
                {removeBtn ? null : <button className="remove" onClick={()=> {
                    setRemove(!remove);
                }}>⚠ 회원 탈퇴</button>}

                {remove ? <div><input name="remove" type="text" value={removeInput} onChange={onRemoveInputHandler} placeholder="아이디를 동일하게 입력해주세요!" />

                    <button onClick={() => {
                        const fetch = async() => {
                            try {
                                if(cookies.info.account !== removeInput) {
                                    return setRemoveInputError(true);
                                }
                                await axios.delete(URL);

                                window.location.replace("/");
                                localStorage.clear();
                                removeCookie('info');

                            } catch(e){
                                console.log(e);
                            }
                        }
                        fetch();


                    }}>탈퇴하기</button></div>: null}
                {removeInputError && <div style={{color : 'red'}}>아이디가 동일하지 않습니다!</div>}
            </div>
        </div>
    )
}

export default Mypage;