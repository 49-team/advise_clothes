import React, { useState } from "react";
import { Link } from "react-router-dom";
import './Login.scss';
import axios from "axios";
import { useCookies } from "react-cookie";


function Login() {
    const [cookies, setCookies, removeCookie] = useCookies(['info']);

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');

    const [info, setInfo] = useState('');

    const [accountError,setAccountError] = useState(false);

    const onSubmit = (e) => {
        e.preventDefault();

        const fetch = async() => {
            try {

                const res = await axios.get(
                    `http://localhost:8080/api/users?account=${account}&password=${password}`);
                // setConfirmAccount(res.data.account);
                // setConfirmPasswords(res.data.password);
                setInfo(res.data);

                if (!res.data.account) {
                    return setAccountError(true);
                }

                setCookies('info', res.data);
                window.localStorage.setItem("account", JSON.stringify(res.data));

                return window.location.replace("/");
            } catch (e) //호출 실패시
            {
                console.log(e);
            }
        }
        return fetch();

        // if(account !== confirmAccount) {
        //     return setAccountError(true);
        // }
        // if(password !== confirmPasswords) {
        //     return setPasswordError(true);
        // }
        // window.location.replace("/")

    };

    const onAccountHandler = (e) => {
        setAccount(e.target.value);
    }

    const onPasswordHandler = (e) => {
        setPassword(e.target.value);
    }

    return(
        <div className='login'>
            <h4>회원 로그인</h4>
            <hr/>
            <div info={info}/>
            <form onSubmit={onSubmit}>
                <input name="account" type="text" value={account} onChange={onAccountHandler} className="accountInput" placeholder="아이디"/>
                <input name="password" type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호"/>

                <Link to='/signUp' className="signup">회원가입</Link>
                <button type="submit" className="login_btn">로그인</button>
                {accountError && <div style={{color : 'red'}}>계정이 일치하지 않습니다.</div>}

            </form>
        </div>
    )
}

export default Login;