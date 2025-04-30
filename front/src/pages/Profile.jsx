
import { useEffect, useState } from "react";
import { fetchProfile, updateProfile } from "../services/profileService";

export default function Profile() {
    const [profile, setProfile] = useState(null);
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        fetchProfile()
            .then(setProfile)
            .catch(() => alert("Error al cargar el perfil"));
    }, []);

    const handleChange = (e) => {
        setProfile({ ...profile, [e.target.name]: e.target.value });
    };

    const handleSave = async () => {
        setSaving(true);
        try {
            const { address, phoneNumber } = profile;
            await updateProfile({ address, phoneNumber });
            alert("Perfil actualizado con éxito");
        } catch {
            alert("Error al actualizar el perfil");
        } finally {
            setSaving(false);
        }
    };

    if (!profile) return <p>Cargando perfil...</p>;

    return (
        <div>
            <h2 className="text-2xl font-bold text-blue-800 mb-4">Mi Perfil</h2>
            <div className="bg-white p-6 rounded shadow space-y-4 max-w-xl">

                <div>
                    <label className="block text-sm font-medium text-gray-700">Nombres</label>
                    <input
                        type="text"
                        value={profile.firstName}
                        disabled
                        className="input bg-gray-100"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700">Apellidos</label>
                    <input
                        type="text"
                        value={profile.lastName}
                        disabled
                        className="input bg-gray-100"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700">Correo</label>
                    <input
                        type="email"
                        value={profile.email}
                        disabled
                        className="input bg-gray-100"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700">Fecha de nacimiento</label>
                    <input
                        type="date"
                        value={profile.dateOfBirth}
                        disabled
                        className="input bg-gray-100"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700">Dirección</label>
                    <input
                        type="text"
                        name="address"
                        value={profile.address}
                        onChange={handleChange}
                        className="input"
                    />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-700">Teléfono</label>
                    <input
                        type="text"
                        name="phoneNumber"
                        value={profile.phoneNumber}
                        onChange={handleChange}
                        className="input"
                    />
                </div>

                <button
                    onClick={handleSave}
                    disabled={saving}
                    className="bg-blue-600 text-white px-4 py-2 rounded disabled:opacity-50"
                >
                    {saving ? "Guardando..." : "Guardar cambios"}
                </button>
            </div>
        </div>
    );
}
