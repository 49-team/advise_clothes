import React, { useState } from "react";
import { Link } from "react-router-dom";
import './Login.scss';
import axios from "axios";


function Login() {

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');

    const [accountError,setAccountError] = useState(false);

    const onSubmit = (e) => {
        e.preventDefault();

        const fetch = async() => {
            try {

                const res = await axios.get(
                    `http://localhost:8080/api/users?account=${account}&password=${password}`);
                // setConfirmAccount(res.data.account);
                // setConfirmPasswords(res.data.password);

                if (!res.data.account) {
                    return setAccountError(true);
                }

                return window.location.replace("/");
            } catch (e)
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
            <form onSubmit={onSubmit}>
                <input name="account" type="text" value={account} onChange={onAccountHandler} className="accountInput" placeholder="아이디"/>
                <input name="password" type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호"/>

                <Link to='/signUp' className="signup">회원가입</Link>
                <button type="submit" className="login_btn">로그인</button>
                {accountError && <div style={{color : 'red'}}>계정이 틀림</div>}

            </form>
        </div>
    )
}

// 기존 코드
//     const [account, setAccount] = useState('');
//     const [password, setPassword] = useState('');
//     const [confirmAccount, setConfirmAccount] = useState('');
//     const [confirmPassword, setConfirmPassword] = useState('');
//
//     const [accountError,setAccountError] = useState(false);
//     const [passwordError,setPasswordError] = useState(false);
//
//     const fetch = async() => {
//         try {
//             const req = await axios.get(
//                 // `http://localhost:8080/api/users?account=${account}&password=${password}`
//                 'http://localhost:8080/api/users?account='+{account}+'&password='+{password}
//             );
//             setConfirmAccount(req.data.account);
//             setConfirmPassword(req.data.password);
//         }catch (e) {
//
//         };
//         fetch();
//         console.log({
//             account,
//             password
//         });
//         window.location.replace("/")
//     };
//
//     const onSubmit = (e) => {
//         e.preventDefault();
//         if (account === confirmAccount) {
//             return setAccountError(true);
//         }
//         if (password === confirmPassword) {
//             return setPasswordError(true);
//         }
//
//
//     }
//
//     const onIdHandler = (event) => {
//         setAccount(event.currentTarget.value);
//     }
//
//     const onPasswordHandler = (event) => {
//         setPassword(event.currentTarget.value);
//     }
//
//
//     return(
//         <div className='login'>
//             <h4>회원 로그인</h4>
//             <hr/>
//             <form>
//                 <input name="account" type="text" value={account} onChange={onIdHandler} className="idInput" placeholder="아이디"/>
//                 <input name="password" type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호"/>
//
//                 <Link to='/signUp' className="signup">회원가입</Link>
//                 <button type="submit" onSubmit={onSubmit} className="login_btn">로그인</button>
//                 {accountError && <div style={{color : 'red'}}>id틀ㄹ림</div>}
//                 {passwordError && <div style={{color : 'red'}}>비번 틀ㄹ림</div>}
//
//             </form>
//         </div>
//     )
// }

export default Login;