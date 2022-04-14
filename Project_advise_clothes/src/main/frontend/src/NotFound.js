import React from "react";

function NotFound() {
    return(
        <div
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                fontSize: 65,
                position: "absolute",
                width: "100%",
                height: "100%",
            }}
        >
            잘못된 페이지입니다.
        </div>
    );
};

export default NotFound;