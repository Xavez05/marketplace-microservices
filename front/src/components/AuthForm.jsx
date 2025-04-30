import { useState } from "react";

export default function AuthForm({ onSubmit, isRegister = false }) {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
        ...(isRegister && {
            firstName: "",
            lastName: "",
            dateOfBirth: "",
        }),
    });

    const [errors, setErrors] = useState("");

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
        setErrors(""); // limpiar errores al escribir
    };

    const validate = () => {
        const { email, password, firstName, lastName, dateOfBirth } = formData;

        if (!email || !password || (isRegister && (!firstName || !lastName || !dateOfBirth))) {
            return "Todos los campos son obligatorios.";
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            return "El correo electrónico no es válido.";
        }

        if (isRegister) {
            const birthDate = new Date(dateOfBirth);
            const today = new Date();
            const age = today.getFullYear() - birthDate.getFullYear();
            const m = today.getMonth() - birthDate.getMonth();
            if (
                age < 18 ||
                (age === 18 && m < 0) ||
                (age === 18 && m === 0 && today.getDate() < birthDate.getDate())
            ) {
                return "Debes tener al menos 18 años.";
            }
        }

        return "";
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const error = validate();
        if (error) {
            setErrors(error);
            return;
        }
        onSubmit(formData);
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="max-w-md mx-auto mt-10 p-6 bg-white rounded-xl shadow-md space-y-4"
        >
            <h2 className="text-2xl font-bold text-center">
                {isRegister ? "Registro" : "Login"}
            </h2>

            {errors && <p className="text-red-600 text-sm text-center">{errors}</p>}

            {isRegister && (
                <>
                    <input
                        type="text"
                        name="firstName"
                        placeholder="Nombres"
                        value={formData.firstName}
                        onChange={handleChange}
                        className="input"
                    />
                    <input
                        type="text"
                        name="lastName"
                        placeholder="Apellidos"
                        value={formData.lastName}
                        onChange={handleChange}
                        className="input"
                    />
                    <input
                        type="date"
                        name="dateOfBirth"
                        value={formData.dateOfBirth}
                        onChange={handleChange}
                        className="input"
                    />
                </>
            )}

            <input
                type="email"
                name="email"
                placeholder="Correo electrónico"
                value={formData.email}
                onChange={handleChange}
                className="input"
            />
            <input
                type="password"
                name="password"
                placeholder="Contraseña"
                value={formData.password}
                onChange={handleChange}
                className="input"
            />

            <button type="submit" className="w-full bg-blue-600 text-white py-2 rounded">
                {isRegister ? "Registrarse" : "Ingresar"}
            </button>
        </form>
    );
}
