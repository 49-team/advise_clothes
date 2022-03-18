import { Nav, Navbar, Container, NavDropdown, Card } from 'react-bootstrap';
import { Link, Route, Switch } from 'react-router-dom';
import './App.css';
import Login from './Login';
import Signup from './Signup';
import Community from './Community';
import Weather from './Weather';
import Recommend from './Recommend';
import Success from './Success';
import NotFound from './NotFound';
import Header from './Header';
import HeaderLogin from './HeaderLogin';
import Mypage from './Mypage';
import PrivateRoute from './PrivateRoute';
import React, { useState } from "react";
import { useCookies } from "react-cookie";


function App() {
    const [cookies, setCookies, removeCookie] = useCookies(['info']);

    return (
        <div className="App">
            <header>
                <Switch>
                    {cookies.info? <HeaderLogin/> : <Header/>}

                    {/* HeaderLogin 로그인 했을 때 로그인 정보랑 마이페이지 연결되게 */}
                </Switch>
            </header>

            <div className='content d-flex'>

                <Switch>
                    <Route path="/" exact>
                        <Weather/>
                        <Recommend/>
                    </Route>

                    <Route path="/login" component={Login}/>

                    <Route path="/signUp" component={Signup}/>

                    <Route path="/success" component={Success}/>

                    <Route path="/community" component={Community}/>

                    <Route path="/mypage" component={Mypage}/>

                    <Route path="*" component={NotFound}/>

                </Switch>

            </div>

            <div className='footer' fixed='bottom'>
                <h6>으아아악</h6>
            </div>
        </div>
    );
}

export default App;
