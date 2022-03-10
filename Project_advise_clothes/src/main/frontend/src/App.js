import { Nav, Navbar, Container, NavDropdown, Card } from 'react-bootstrap';
import { Link, Route, Switch } from 'react-router-dom';
import './App.css';
import Login from './Login';
import Signup from './Signup';
import Community from './Community';
import Weather from './Weather';
import Recommend from './Recommend';
import Success from './Success';

function App() {
    return (
        <div className="App">
            <header>
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
            </header>

            <div className='content d-flex'>

                <Switch>
                    <Route path="/" exact>
                        <Weather/>
                        <Recommend/>
                    </Route>

                    <Route path="/login">
                        <Login/>
                    </Route>

                    <Route path="/signUp">
                        <Signup/>
                    </Route>

                    <Route path="/success">
                        <Success/>
                    </Route>

                    <Route path="/community">
                        <Community/>
                    </Route>

                    <Route path="*">
                        <NotFound/>
                    </Route>
                </Switch>

            </div>

            <div className='footer' fixed='bottom'>
                <h6>으아아악</h6>
            </div>
        </div>
    );
}

export default App;
