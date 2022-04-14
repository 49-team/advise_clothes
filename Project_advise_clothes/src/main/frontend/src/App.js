import { Nav, Navbar, Container, NavDropdown, Card } from 'react-bootstrap';
import { Link, Route, Switch } from 'react-router-dom';
import './App.css';
import Login from "./Login/Login";
import Signup from './Login/Signup';
import Community from './Community';
import Weather from './Weather';
import Recommend from './Recommend';
import Success from './Login/Success';
import NotFound from './NotFound';
import Header from './Header';
import HeaderLogin from './HeaderLogin';
import Mypage from './Login/Mypage';
import PrivateRoute from './Login/PrivateRoute';
import PublicRoute from './Login/PublicRoute';
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

                    <PublicRoute path="/login" component={Login}/>

                    <PublicRoute path="/signUp" component={Signup}/>

                    <PublicRoute path="/success" component={Success}/>

                    <Route path="/community" component={Community}/>

                    <PrivateRoute path="/mypage" component={Mypage}/>

                    <Route path="*" component={NotFound}/>

                </Switch>

                {/* <PrivateRoute path="/success" component={Success}/> */}

            </div>

            <div className='footer' fixed='bottom'>
                <h6>ⓒAdvise-Clothes</h6>
            </div>
        </div>
    );
}

export default App;
