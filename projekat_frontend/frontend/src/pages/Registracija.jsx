import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { authApi } from "../api";

function Registracija() {

  const [email, setEmail] = useState("");
  const [greska, setGreska] = useState("");
  const [uspeh, setUspeh] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setGreska("");
    setUspeh("");

    try {
      await authApi.registracija({
        email
       
      });

      setUspeh("Proverite email kako biste verifikovali svoju registraciju ");
      setTimeout(() => {
        navigate("/");
      }, 10000);
    } catch (err) {
      console.error(err);
      setGreska(err.response?.data?.error || "Neuspesna registracija, pokusajte ponovo");
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "50px auto", padding: "20px", border: "1px solid #ccc", borderRadius: "8px" }}>
      <h2>Registracija</h2>
      {greska && <p style={{ color: "red" }}>{greska}</p>}
      {uspeh && <p style={{ color: "green" }}>{uspeh}</p>}

      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "12px" }}>

        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
          />
        </div>


        <button type="submit" style={{ padding: "10px", background: "#28a745", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}>
          Registruj se
        </button>
      </form>
      
      <p style={{ marginTop: "15px", textAlign: "center" }}>
        Imate postojeci nalog? <Link to="/prijava">Prijavi se</Link>
      </p>
    </div>
  );
}
export default Registracija;