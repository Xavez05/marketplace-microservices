import api from "./api";

export async function createOrder(shippingAddress) {
    const res = await api.post("/orders", { shippingAddress });
    return res.data;
}

export async function fetchUserOrders() {
    const res = await api.get("/orders");
    return res.data;
}

export async function fetchOrderById(orderId) {
    const res = await api.get(`/orders/${orderId}`);
    return res.data;
}
