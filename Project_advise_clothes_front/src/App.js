import { Nav, Navbar, Container, NavDropdown, Card } from 'react-bootstrap';
import { Link, Route, Switch } from 'react-router-dom';
import './App.css';
import Login from "./Login/Login";
import Signup from './Login/Signup';
import Community from './Community';
import Weather from './Weather';
// import Recommend from './Recommend';
import Success from './Login/Success';
import NotFound from './NotFound';
import Header from './Header';
import HeaderLogin from './HeaderLogin';
import Mypage from './Login/Mypage';
import PrivateRoute from './Login/PrivateRoute';
import PublicRoute from './Login/PublicRoute';
import React, {useContext, useEffect, useState} from "react";
import { useCookies } from "react-cookie";
import isLogin from './Login/isLogin';
import axios from "axios";
import './Weather.scss';
import './Recommend.css';

import { createContext } from 'react';

export const WeatherContext = createContext();

function App() {
    const [cookies, setCookies, removeCookie] = useCookies(['info', 'auth']);

    const [weather, setWeather] = useState(null);
    const [icon, setIcon] = useState(null);

    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(null);

    // const weathers = useContext(WeatherContext);

    // filter()함수가 안 먹혀서(?) part마다 state 생성...
    // 뭔가 비효율적이다! 코드를 다시 한 번 생각해보자!!
    const [top, setTop] = useState([]);
    const [bottom, setBottom] = useState([]);
    const [outer, setOuter] = useState([]);
    const [shoes, setShoes] = useState([]);
    const [clothes, setClothes] = useState([]);

    useEffect(() => {
        const fetch = async() => {
            try {
                setWeather(null);
                setIcon(null);
                setError(null);
                setLoading(true);

                setTop(null);
                setBottom(null);
                setOuter(null);
                setShoes(null);

                const response = await axios.get(
                    'https://api.openweathermap.org/data/2.5/weather?q=seoul&lang=kr&appid=4b48e343728fa30415f6df25157c0e0e'
                );
                setWeather(response.data);
                setIcon(response.data.weather[0].icon)

                let temp = (Math.floor(response.data.main.temp-273.15))
                let weather_nal = response.data.weather[0].main;

                const result = await axios.get(
	                    `${process.env.REACT_APP_BACKEND_URL}/api/advise?temperature=${temp}&weather=${weather_nal}`
                );
                
                setClothes(result.data);
                setTop(result.data.filter(item => item.part === "TOP"));
                setBottom(result.data.filter(item => item.part === "BOTTOM"));
                setOuter(result.data.filter(item => item.part === "OUTER"));
                setShoes(result.data.filter(item => item.part === "SHOES"));
            } catch(e) {
                setError(e);
            }
            setLoading(false);
        };
        fetch();
    }, []);

    if (loading) return <div>로딩즁</div>
    if (error) return <div>에러!</div>
    if (!weather) return null;
    if (!icon) return null;

    return (
        <div className="App">
            <header>
                <Switch>
                    {/* {cookies.info? <HeaderLogin/> : <Header/>} cookie 활용을 위해 제거 */}
                    {isLogin()? <HeaderLogin/> : <Header/>}

                    {/* HeaderLogin 로그인 했을 때 로그인 정보랑 마이페이지 연결되게 */}
                </Switch>
            </header>

            <div className='content d-flex'>

                <Switch>
                    <Route path="/" exact>
                        {/*<Weather/>*/}
                        {/*<WeatherContext.Provider value={weather}>*/}
                        <div className='con1'>날씨

                            <div className="weather">
                                <img src = {`http://openweathermap.org/img/wn/${icon}@2x.png`} alt="weather_Icon" className="weimg"/>
                                <h4>{weather.weather[0].main}</h4>

                                {/* Math.floor 소수점 버리기, 켈빈(K) - 273.15 = 섭씨(℃) */}
                                <h3>{Math.floor(weather.main.temp-273.15)}℃</h3>

                                <p>{weather.name}</p>
                            </div>
                        </div>

                        {/*<Recommend/>*/}
                        <div className='con2'>옷추천
                            <p/>
                            {/*part마다(TOP,BOTTOM,OUTER 등) name나누기*/}
                            {/*TOP*/}
                            상의 : {clothes.filter(x => x.part === "TOP").map((item, index, arr) => (
                                <React.Fragment key={item.name}>
                                    <span style={{ display: 'inline-block' }}>{item.name}</span>
                                    {index !== arr.length - 1 && " / "}
                                </React.Fragment>
                            ))}
                            {/*BOTTOM*/}
                            <p/>
                            하의 : {bottom.map((item, index) => (
                                <React.Fragment key={item.name}>
                                    <span style={{ display: 'inline-block' }}>{item.name}</span>
                                    {index !== bottom.length - 1 && " / "}
                                </React.Fragment>
                            ))}
                            {/*OUTER*/}
                            <p/>
                            외투 : {outer.map((item, index) => (
                                <React.Fragment key={item.name}>
                                    <span style={{ display: 'inline-block' }}>{item.name}</span>
                                    {index !== outer.length - 1 && " / "}
                                </React.Fragment>
                            ))}
                            {/*/!*SHOES*!/*/}
                            {/*<p/>*/}
                            {/*신발 : {shoes.map((item, index) => (*/}
                            {/*    <React.Fragment key={item.name}>*/}
                            {/*        <span style={{ display: 'inline-block' }}>{item.name}</span>*/}
                            {/*        {index !== shoes.length - 1 && " / "}*/}
                            {/*    </React.Fragment>*/}
                            {/*))}*/}
                        </div>

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

// function Recommend() {
//
//     return(
//
//
//     )
// }
export default App;