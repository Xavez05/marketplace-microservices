import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { createOrder } from "../services/orderService";
import { fetchProfile } from "../services/profileService";
import { clearCart } from "../services/cartService";

export default function ConfirmOrder() {
    const [address, setAddress] = useState("");
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        fetchProfile()
            .then((profile) => {
                setAddress(profile.address || "");
                setLoading(false);
            })
            .catch(() => {
                alert("Error al cargar el perfil");
                setLoading(false);
            });
    }, []);

    const handleConfirm = async () => {
        try {
            await createOrder(address);
            await clearCart(); // opcional
            alert("Pedido realizado con éxito");
            navigate("/app/orders");
        } catch (err) {
            alert("Error al confirmar pedido");
        }
    };

    if (loading) return <p>Cargando...</p>;

    return (
        <div>
            <h2 className="text-2xl font-bold text-blue-800 mb-4">Confirmar Pedido</h2>
            <div className="bg-white p-6 rounded shadow space-y-4">
                <label className="block text-sm font-medium text-gray-700">
                    Dirección de Envío:
                </label>
                <textarea
                    rows={3}
                    className="w-full border rounded p-2"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                />
                <button
                    onClick={handleConfirm}
                    className="bg-blue-600 text-white px-4 py-2 rounded"
                >
                    Confirmar Pedido
                </button>
            </div>
        </div>
    );
}
