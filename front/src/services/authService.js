import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

export async function loginUser(data) {
    try {
        const res = await axios.post(`${API_URL}/login`, data, {
            headers: { "Content-Type": "application/json" },
        });
        return res.data;
    } catch (err) {
        throw new Error("Credenciales inválidas");
    }
}

export async function registerUser(data) {
    try {
        const res = await axios.post(`${API_URL}/register`, data, {
            headers: { "Content-Type": "application/json" },
        });
        return res.data;
    } catch (err) {
        throw new Error("Error al registrar usuario");
    }
}
