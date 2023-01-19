import React from "react";
import './Recommend.css';
import './Weather'

function Recommend(props) {

    return(
        <div className='con2'>옷추천
            <div>Test : {props.weather}</div>
        </div>

    )
}

export default Recommend;