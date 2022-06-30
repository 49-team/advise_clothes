import React from "react";
import { Nav, Navbar, Container } from "react-bootstrap";
import { Link } from "react-router-dom";

function Header() {
    return(
        <Navbar className="d-flex" bg="light" expand="lg" fixed='top'>
            <Container>
                <Navbar.Brand as={Link} to="/">Advise-Clothes</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav" className='collapse navbar-collapse justify-content-end'>
                    <Nav className="ml-auto">
                        <Nav.Link as={Link} to="/" className='px-3'>Home</Nav.Link>
                        <Nav.Link as={Link} to="/login" className='px-3' >Login</Nav.Link>
                        <Nav.Link as={Link} to="/signUp" className='px-3'>Sign Up</Nav.Link>
                        <Nav.Link as={Link} to="/community">Community</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default Header;