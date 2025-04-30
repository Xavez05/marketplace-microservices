import { useEffect, useState } from "react";
import { fetchProducts } from "../services/productService";

export default function Products() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts()
            .then(setProducts)
            .catch(() => alert("Error al cargar productos"));
    }, []);

    return (
        <div>
            <h2 className="text-2xl font-bold mb-6">Catálogo de Productos</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
                {products.map((product) => (
                    <div key={product.id} className="bg-white p-4 rounded shadow">
                        <img
                            src={product.imageUrl || "https://via.placeholder.com/150"}
                            alt={product.name}
                            className="w-full h-48 object-cover rounded mb-4"
                        />
                        <h3 className="text-lg font-semibold">{product.name}</h3>
                        <p className="text-gray-600">{product.description}</p>
                        <p className="mt-2 font-bold text-blue-700">Q{product.price}</p>
                        <button
                            className="mt-4 w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
                            onClick={() => alert("Agregar al carrito (falta lógica)")}
                        >
                            Agregar al carrito
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}
