import React from "react";
import { Nav, Navbar, Container, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Cookies, useCookies } from "react-cookie";

function HeaderLogin() {
    let btn_out = { marginLeft: "15px", borderRadius: "9px", background: "black", color: "white"};
    const [cookies, setCookies, removeCookie] = useCookies(['info']);

    return(
        <Navbar className="d-flex" bg="light" expand="lg" fixed='top'>
            <Container>
                <Navbar.Brand as={Link} to="/">Advise-Clothes</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav" className='collapse navbar-collapse justify-content-end'>
                    <Nav className="ml-auto">
                        <Nav.Link as={Link} to="/" className='px-3'>Home</Nav.Link>
                        <Nav.Link as={Link} to="/mypage" className='px-3' >{cookies.info.nickname}</Nav.Link>
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