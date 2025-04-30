import api from "./api";

export async function fetchCart() {
    const res = await api.get("/cart");
    return res.data;
}

export async function addToCart(item) {
    const res = await api.post("/cart", item);
    return res.data;
}

export async function updateCartItem(cartItemId, item) {
    const res = await api.put(`/cart/${cartItemId}`, item);
    return res.data;
}

export async function deleteCartItem(cartItemId) {
    const res = await api.delete(`/cart/${cartItemId}`);
    return res.data;
}

export async function clearCart() {
    const res = await api.delete("/cart/clear");
    return res.data;
}
