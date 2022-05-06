import React, { useState } from "react";
import { Link } from "react-router-dom";
import './Login.scss';
import axios from "axios";
import { useCookies } from "react-cookie";


function Login() {
    const [cookies, setCookies, removeCookie] = useCookies(['info', 'auth']);

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');

    const [info, setInfo] = useState('');

    const [accountError,setAccountError] = useState(false);

    const onSubmit = (e) => {
        e.preventDefault();
        // const HOSTNAME = 'localhost:8080';
        const HOSTNAME = '52.79.195.60:8080';
        const PROTOCOL = 'http'

        const fetch = async() => {
            try {

                const resUser = await axios.get(
                    // `${PROTOCOL}://${HOSTNAME}/api/users?account=${account}&password=${password}`);
                    `/api/users?account=${account}&password=${password}`);
                // setConfirmAccount(resUser.data.account);
                // setConfirmPasswords(resUser.data.password);
                setInfo(resUser.data);

                if (!resUser.data.account) {
                    return setAccountError(true);
                }

                const reqSessionBody = {
                    "platform" : "BROWSER",
                    "user" : {
                        "id" : resUser.data.id
                    }
                }

                const resSession = await axios.post(
                    // `${PROTOCOL}://${HOSTNAME}/api/session`, reqSessionBody
                    `/api/session`, reqSessionBody
                )

                setCookies('info', resUser.data);
                setCookies('auth', resSession.data.sessionKey);
                window.localStorage.setItem("account", JSON.stringify(resUser.data));

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