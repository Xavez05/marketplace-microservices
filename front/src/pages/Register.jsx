import AuthForm from "../components/AuthForm";
import { registerUser } from "../services/authService";
import {Link, useNavigate} from "react-router-dom";

export default function Register() {
    const navigate = useNavigate();

    const handleRegister = async (formData) => {
        try {
            await registerUser(formData);
            alert("Usuario registrado correctamente");
            navigate("/");
        } catch (err) {
            alert(err.message);
        }
    };

    return (
        <div>
            <AuthForm onSubmit={handleRegister} isRegister />
            <p className="text-center mt-4 text-sm text-gray-600">
                ¿Ya tienes una cuenta?{" "}
                <Link to="/" className="text-blue-600 hover:underline">
                    Inicia sesión
                </Link>
            </p>
        </div>
    );
}
