import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api", // Apunta al API Gateway
    headers: {
        "Content-Type": "application/json",
    },
});

// Si tienes JWT, lo puedes inyectar automáticamente con un interceptor:
api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default api;
