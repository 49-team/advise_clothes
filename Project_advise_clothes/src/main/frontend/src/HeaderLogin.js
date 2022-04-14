import React, { useEffect, useState } from "react";
import { Nav, Navbar, Container, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Cookies, useCookies } from "react-cookie";
import axios from 'axios';



function HeaderLogin() {
    let btn_out = { marginLeft: "15px", borderRadius: "9px", background: "black", color: "white"};
    const [cookies, setCookies, removeCookie] = useCookies(['info', 'auth']);
    const [resSession, setResSession] = useState([]);

    // const fetch = async() => {
    //     try {
    //         const res = await axios.get(
    //             `http://localhost:8080/api/session/${cookies.auth}`
    //         );

    //         if (res.status === 200) {
    //             // setResSession(res);
    //             console.log("API 조회 성공")
    //             console.log(res.data)
    //             return res.data;
    //         } else if (res.status === 204) {
    //             console.log("204")
    //             // window.location.replace("/");
    //         // } else {
    //         //     removeCookie('info');
    //         //     removeCookie('auth');
    //         }

    //         return { "user" : {
    //             "nickname" : null
    //         }};
    //         // console.log(cookies.auth);
    //         // console.log(res);
    //     } catch (e) {
    //         console.log(e)
    //     }
    // }

    // useEffect(() => {
    //     async function fetch() {
    //         const res = await axios.get(
    //             `http://localhost:8080/api/session/${cookies.auth}`
    //         );
    //         console.log(res);
    //         setResSession(res.data);
    //     }
    //     fetch();
    // }, [])


    return(
        <Navbar className="d-flex" bg="light" expand="lg" fixed='top'>
            <Container>
                <Navbar.Brand as={Link} to="/">Advise-Clothes</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav" className='collapse navbar-collapse justify-content-end'>
                    <Nav className="ml-auto">
                        <Nav.Link as={Link} to="/" className='px-3'>Home</Nav.Link>
                        {/* <Nav.Link as={Link} to="/mypage" className='px-3' >안녕!{resSession.user.nickname}</Nav.Link> */}
                        {/* <Nav.Link as={Link} to="/mypage" className='px-3' >안녕!{fetch().user.nickname}</Nav.Link> */}
                        <Nav.Link as={Link} to="/mypage" className='px-3' >안녕!{cookies.info.nickname}</Nav.Link>
                        <Nav.Link as={Link} to="/community">Community</Nav.Link>
                        <button style={btn_out} onClick={()=> {
                            removeCookie('info');
                            return window.location.replace("/");
                        }}>로그아웃</button>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default HeaderLogin;