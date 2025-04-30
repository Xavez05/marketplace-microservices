import { useNavigate, Link } from "react-router-dom";
import AuthForm from "../components/AuthForm";
import { loginUser } from "../services/authService";

export default function Login() {
    const navigate = useNavigate();

    const handleLogin = async (formData) => {
        try {
            const res = await loginUser(formData);
            localStorage.setItem("token", res.token);
            alert("Login exitoso");
            navigate("/app");
        } catch (err) {
            alert(err.message);
        }
    };

    return (
        <div>
            <AuthForm onSubmit={handleLogin} />
            <p className="text-center mt-4 text-sm text-gray-600">
                ¿No tienes una cuenta?{" "}
                <Link to="/register" className="text-blue-600 hover:underline">
                    Regístrate aquí
                </Link>
            </p>
        </div>
    );
}
