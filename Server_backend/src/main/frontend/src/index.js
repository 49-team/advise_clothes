import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";

//redux 세팅
import {Provider} from 'react-redux';
import { createStore } from 'redux';

// let store = createStore(()=>{
//   return (<div>어쩌구</div>)
// })

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
    {/* Provider로 감싸는 컴포넌트는 다 공유가능 */}
    {/* store라는 컴포넌트 예시들음, 이렇게 넣으면 모든 컴포넌트에서 공유 */}
    {/* <Provider store={store}> */}
      <App />
    {/* </Provider> */}
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
