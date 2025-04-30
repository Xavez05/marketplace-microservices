import { useEffect, useState } from "react";
import {
    fetchCart,
    deleteCartItem,
    clearCart,
} from "../services/cartService";

export default function Cart() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        fetchCart()
            .then(setItems)
            .catch(() => alert("Error al cargar el carrito"));
    }, []);

    const handleDelete = async (id) => {
        await deleteCartItem(id);
        setItems(items.filter((item) => item.id !== id));
    };

    const handleClear = async () => {
        await clearCart();
        setItems([]);
    };

    return (
        <div>
            <h2 className="text-2xl font-bold text-blue-800 mb-6">Carrito de Compras</h2>

            {items.length === 0 ? (
                <p className="text-gray-500">Tu carrito está vacío.</p>
            ) : (
                <>
                    <div className="space-y-4">
                        {items.map((item) => (
                            <div
                                key={item.id}
                                className="flex justify-between items-center bg-white p-4 rounded shadow"
                            >
                                <div>
                                    <p className="font-semibold">{item.productName}</p>
                                    <p className="text-sm text-gray-500">
                                        Cantidad: {item.quantity}
                                    </p>
                                </div>
                                <button
                                    onClick={() => handleDelete(item.id)}
                                    className="text-red-600 hover:underline"
                                >
                                    Eliminar
                                </button>
                            </div>
                        ))}
                    </div>

                    <div className="mt-6 flex justify-between">
                        <button
                            onClick={handleClear}
                            className="text-sm text-red-600 hover:underline"
                        >
                            Vaciar carrito
                        </button>
                        <button
                            onClick={() => alert("Implementar confirmación")}
                            className="bg-blue-600 text-white px-4 py-2 rounded"
                        >
                            Confirmar pedido
                        </button>
                    </div>
                </>
            )}
        </div>
    );
}
