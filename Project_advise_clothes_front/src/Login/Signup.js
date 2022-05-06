import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import './Signup.scss';
import {Form} from 'react-bootstrap';
import { useHistory } from "react-router-dom/cjs/react-router-dom.min";
import axios from "axios";

function Signup() {

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [nick, setNick] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber,setPhoneNumber] = useState('');

    // const [gender, setGender] = useState({
    //   none:'선택 안함',
    //   female:'여자',
    //   male:'남자'});
    // const [area, setArea] = useState('');

    const [passwordError,setPasswordError] = useState(false);
    const [passwordLengthError,setPasswordLengthError] = useState(false);

    const [phoneError,setPhoneError] = useState(false);
    const [idError,setIdError] = useState(false);

    let history = useHistory();


    // form말고 submit만 실행되게 하기 위해(새로고침 없이) preventDefault 사용
    const onSubmit = (e) => {
        e.preventDefault();
        if(password.length < 8) {
            return setPasswordLengthError(true);
        }
        if(phoneNumber.length < 13) {
            return setPhoneError(true);
        }
        if(password !== confirmPassword) {
            return setPasswordError(true);
        }
        if(account.length < 4) {
            return setIdError(true);
        }

        const fetch = async() => {
            try {
                const data = {
                    account: account,
                    password: password,
                    nickname: nick,
                    email: email,
                    phoneNumber: phoneNumber
                }

                // await axios.post('http://localhost:8080/api/users', data)
                await axios.post('/api/users', data)
            }
            catch (e)
            {}
        }
        fetch()

        // console.log({
        //     account,
        //     password,
        //     confirmPassword,
        //     nick,
        //     email,
        //     phoneNumber,
        //     // area
        // });
        // Link가 아닌 replace를 사용하여 이전 페이지의 기록이 남지 않음!
        window.location.replace("/success")
    };

    const onIdHandler = (e) => {
        const idType = /^[A-Za-z0-9+]{0,15}$/;
        if (idType.test(e.target.value)) {
            setIdError(e.target.value.length <4);
            setAccount(e.target.value);
        }
    }

    const onPasswordHandler = (e) => {
        setPasswordLengthError(e.target.value < 8);
        setPassword(e.target.value);
    }


    // 비밀번호 확인
    const onConfirmPasswordHandler = (e) => {
        setPasswordError(e.target.value !== password);
        setConfirmPassword(e.target.value);
    }

    const onNickHandler = (e) => {
        setNick(e.target.value);
    }

    const onEmailHandler = (e) => {
        setEmail(e.target.value);
    }

    const onPhoneHandler = (e) => {
        const phone = /^[0-9\b -]{0,13}$/;
        if (phone.test(e.target.value)) {
            setPhoneError(e.target.value.length < 11);
            setPhoneNumber(e.target.value);
        }
    }

    // const onGenderHandler = (e) => {
    //   setGender(e.target.value)
    // }

    // const onAreaHandler = (e) => {
    //   setArea(e.target.value);
    // }

    useEffect(()=> {
        // phoneNumber의 길이가 11일때, 3자리-4자리-4자리 구성, \d는 숫자인지 검사, {}은 자릿수 지정, $1 첫번째 조건 완성했을시 - $2 완성 - $3
        if (phoneNumber.length === 11) {
            setPhoneNumber(phoneNumber.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3'));
        }
    },[phoneNumber]);


    // useEffect가 unmount될 때 history.block실행해서 뒤로가기 방지
    useEffect(()=> {
        const back = history.block('이 페이지를 벗어나실 건가요!?');
        return() => {
            back();
        };
    },[Signup]);


    return (
        <div className='sign'>
            <h4>회원 가입</h4>
            <hr/>
            <form onSubmit={onSubmit}>
                {/* // className='signForm'> */}
                <input name="id" type="text" value={account} onChange={onIdHandler} className="idInput" placeholder="아이디"/>
                {idError && <div style={{color : 'red'}}>아이디를 4자리 이상 입력해주세요!!</div>}

                <input name="password" type="password" value={password} onChange={onPasswordHandler} placeholder="비밀번호"/>
                {passwordLengthError && <div style={{color : 'red'}}>비밀번호를 8자리 이상 입력해주세요!</div>}
                <input name="confirmPassword" type="password" value={confirmPassword} onChange={onConfirmPasswordHandler} placeholder="비밀번호 확인"/>
                {passwordError && <div style={{color : 'red'}}>비밀번호가 일치하지 않습니다!</div>}

                <input name="nick" type="text" value={nick} onChange={onNickHandler} placeholder='닉네임'/>
                <input name="email" type="email" value={email} onChange={onEmailHandler} placeholder='이메일'/>
                <input type="text" onChange={onPhoneHandler} value={phoneNumber} placeholder='전화번호'/>
                {phoneError && <div style={{color : 'red'}}>정확한 전화번호를 입력해주세요!!</div>}

                {/* 성별 radio버튼 */}
                {/* <input name="gender" type="radio" onChange={onGenderHandler}/><h5>{this.useState.none}</h5> */}
                {/* <input name="gender" type="radio" onChange={onGenderHandler}/><h5>{this.useState.female}</h5> */}
                {/* <input name="gender" type="radio" onChange={onGenderHandler}/><h5>{this.useState.male}</h5> */}

                {/* 지역 */}
                {/* <Form.Select>
        <option>지역</option>
        <option value={area} onChange={onAreaHandler}>One</option>
        <option value="aa" onChange={onAreaHandler}>Two</option>
        <option value="cc" onChange={onAreaHandler}>Three</option>
        </Form.Select> */}

                <button type="submit" className="signup_btn">회원 가입</button>
            </form>
        </div>
    )
}

export default Signup;