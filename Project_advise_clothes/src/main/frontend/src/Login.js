import React, { useState } from "react";
import { Link } from "react-router-dom";
import './Login.scss';


function Login() {

    const [id, setId] = useState('');
    const [password, setPassword] = useState('');

    const onIdHandler = (event) => {
        setId(event.currentTarget.value);
    }

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }

    const onSubmit = (event) => {
        event.preventDefault();
    }

    return(
        <div className='login'>
            <h4>회원 로그인</h4>
            <hr/>
            <form>
                <input name="id" type="text" value={id} onChange={onIdHandler} className="idInput" placeholder="아이디"/>
                <input name="password" type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호"/>
                <Link to='/signUp' className="signup">회원가입</Link>
                <button type="submit" onSubmit={onSubmit} className="login_btn">로그인</button>
            </form>
        </div>
    )
}

// 로그인 후 로그인 정보 헤더에 (회원 닉네임(로그아웃), 마이페이지 출력)
// 회원과 게스트 접근 제한 페이지

export default Login;