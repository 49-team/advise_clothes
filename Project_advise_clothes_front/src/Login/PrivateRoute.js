import React from "react";
import { Route, Redirect } from "react-router-dom";
import isLogin from "./isLogin";

const PrivateRoute = ({ component: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      render={(props) => {
        !isLogin() &&
          alert("잘못된 경로입니다.");
        return isLogin() ? <Component {...props} /> : <Redirect to="/" />;
      }}
    />
  );
};

export default PrivateRoute;