import jwt_decode from "jwt-decode";

const token = localStorage.getItem("token");
const decoded = jwt_decode(token);
const userId = decoded.sub || decoded.userId;
