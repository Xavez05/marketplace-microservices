import {Link, Outlet, useNavigate} from "react-router-dom";

export default function DashboardLayout() {
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/", { replace: true });
    };

    return (
        <div className="flex h-screen">
            {/* Sidebar */}
            <aside className="w-64 bg-blue-800 text-white flex flex-col p-4 space-y-4">
                <h1 className="text-xl font-bold mb-6">🏪 Carrito App</h1>
                <Link to="/app/products" className="hover:bg-blue-700 p-2 rounded">Catálogo</Link>
                <Link to="/app/cart" className="hover:bg-blue-700 p-2 rounded">Carrito</Link>
                <Link to="/app/orders" className="hover:bg-blue-700 p-2 rounded">Órdenes</Link>
                <Link to="/app/profile" className="hover:bg-blue-700 p-2 rounded">Perfil</Link>

                <button
                    onClick={handleLogout}
                    className="mt-auto text-sm hover:underline text-left text-gray-200"
                >
                    Cerrar sesión
                </button>
            </aside>

            {/* Main Content */}
            <main className="flex-1 bg-gray-100 p-8 overflow-auto">
                <Outlet />
            </main>
        </div>
    );
}
