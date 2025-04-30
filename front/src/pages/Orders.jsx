import { useEffect, useState } from "react";
import { fetchUserOrders } from "../services/orderService";
import { Link } from "react-router-dom";

export default function Orders() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchUserOrders()
            .then(setOrders)
            .catch(() => alert("Error al cargar las órdenes"));
    }, []);

    return (
        <div>
            <h2 className="text-2xl font-bold text-blue-800 mb-6">Mis Órdenes</h2>

            {orders.length === 0 ? (
                <p className="text-gray-500">Aún no has realizado ninguna orden.</p>
            ) : (
                <div className="space-y-4">
                    {orders.map((order) => (
                        <div key={order.id} className="bg-white p-4 rounded shadow">
                            <div className="flex justify-between items-center">
                                <div>
                                    <p className="font-semibold">Orden #{order.id}</p>
                                    <p className="text-sm text-gray-500">Dirección: {order.shippingAddress}</p>
                                    <p className="text-sm text-gray-500">Estado: {order.status}</p>
                                </div>
                                <Link
                                    to={`/app/orders/${order.id}`}
                                    className="text-blue-600 hover:underline text-sm"
                                >
                                    Ver detalle
                                </Link>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
