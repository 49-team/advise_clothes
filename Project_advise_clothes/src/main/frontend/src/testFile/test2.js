import React, {useState, useEffect} from "react";
import {Link} from "react-router-dom";

const Home = () => {

  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch('/api/db')
      .then(response => response.text())
      .then(message => {
        setMessage(message)
      });
  }, [])

  return (
      <div>
          <h1>주애애애애애애앵</h1>
          <Link to="/about">{message}</Link>
      </div>
  )
}

export default Home;