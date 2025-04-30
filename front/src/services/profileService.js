import api from "./api";
import { jwtDecode } from "jwt-decode";


function getUserIdFromToken() {
    const token = localStorage.getItem("token");
    if (!token) throw new Error("Token no encontrado");


    const decoded = jwtDecode(token);
    console.log(decoded);
    return decoded.user_id || decoded.sub;
}

export async function fetchProfile() {
    const userId = getUserIdFromToken();
    const res = await api.get(`/users/profile/${userId}`);
    return res.data;
}


export async function updateProfile(profileData) {
    const userId = getUserIdFromToken();
    const res = await api.put(`/users/profile/${userId}`, profileData);
    return res.data;
}
