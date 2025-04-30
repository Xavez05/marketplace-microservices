import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { fetchOrderById } from "../services/orderService";

export default function OrderDetail() {
    const { orderId } = useParams();
    const [order, setOrder] = useState(null);

    useEffect(() => {
        fetchOrderById(orderId)
            .then(setOrder)
            .catch(() => alert("No se pudo cargar la orden"));
    }, [orderId]);

    if (!order) return <p>Cargando orden...</p>;

    return (
        <div>
            <h2 className="text-2xl font-bold text-blue-800 mb-4">
                Detalle de Orden #{order.id}
            </h2>
            <div className="bg-white p-6 rounded shadow space-y-4">
                <p><strong>Dirección:</strong> {order.shippingAddress}</p>
                <p><strong>Estado:</strong> {order.status}</p>

                <h3 className="text-lg font-semibold mt-4">Productos:</h3>
                {order.items?.length === 0 ? (
                    <p>No hay productos en esta orden.</p>
                ) : (
                    <ul className="list-disc list-inside space-y-1">
                        {order.items.map((item) => (
                            <li key={item.id}>
                                {item.productName} — Cantidad: {item.quantity}
                            </li>
                        ))}
                    </ul>
                )}
            </div>

            <Link to="/app/orders" className="text-blue-600 text-sm mt-4 inline-block hover:underline">
                ← Volver a mis órdenes
            </Link>
        </div>
    );
}
