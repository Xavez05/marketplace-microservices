import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Products from "./pages/Products";
import Cart from "./pages/Cart";
import Profile from "./pages/Profile";
import Orders from "./pages/Orders";
import ConfirmOrder from "./pages/ConfirmOrder";
import Home from "./pages/Home";
import DashboardLayout from "./components/DashboardLayout";
import ProtectedRoute from "./components/ProtectedRoute";
import OrderDetail from "./pages/OrderDetail";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                {/* Rutas públicas */}
                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />

                {/* Rutas protegidas */}
                <Route
                    path="/app"
                    element={
                        <ProtectedRoute>
                            <DashboardLayout />
                        </ProtectedRoute>
                    }
                >
                    <Route index element={<Home />} />
                    <Route path="products" element={<Products />} />
                    <Route path="cart" element={<Cart />} />
                    <Route path="orders" element={<Orders />} />
                    <Route path="profile" element={<Profile />} />
                    <Route path="confirm" element={<ConfirmOrder />} />
                    <Route path="orders/:orderId" element={<OrderDetail />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}


