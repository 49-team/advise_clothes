import React from "react";
import { Nav, Navbar, Container } from "react-bootstrap";
import { Link } from "react-router-dom";

function HeaderLogin() {
    return(
        <Navbar className="d-flex" bg="light" expand="lg" fixed='top'>
            <Container>
                <Navbar.Brand as={Link} to="/">Advise-Clothes</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav" className='collapse navbar-collapse justify-content-end'>
                    <Nav className="ml-auto">
                        <Nav.Link as={Link} to="/" className='px-3'>Home</Nav.Link>
                        <Nav.Link as={Link} to="/mypage" className='px-3' >로그인데이터</Nav.Link>
                        <Nav.Link as={Link} to="/community">Community</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default HeaderLogin;