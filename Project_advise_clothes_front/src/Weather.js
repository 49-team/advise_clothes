import axios from "axios";
import React, { useEffect, useState } from "react";
import './Weather.scss';

function Weather() {

    const [weather, setWeather] = useState(null);
    const [icon, setIcon] = useState(null);

    const [loading, setLoading] = useState(null);
    const [error, setError] = useState(null);


    useEffect(() => {
        const fetch = async() => {
            try {
                setWeather(null);
                setIcon(null);
                setError(null);
                setLoading(true);

                const response = await axios.get(
                    'https://api.openweathermap.org/data/2.5/weather?q=seoul&lang=kr&appid=4b48e343728fa30415f6df25157c0e0e'
                );
                setWeather(response.data);
                setIcon(response.data.weather[0].icon)
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
        <div className='con1'>날씨

            <div className="weather">
                <img src = {`http://openweathermap.org/img/wn/${icon}@2x.png`} alt="weather_Icon" className="weimg"/>
                <h4>{weather.weather[0].main}</h4>

                {/* Math.floor 소수점 버리기, 켈빈(K) - 273.15 = 섭씨(℃) */}
                <h3>{Math.floor(weather.main.temp-273.15)}℃</h3>

                <p>{weather.name}</p>


            </div>
        </div>
    )
}

export default Weather;