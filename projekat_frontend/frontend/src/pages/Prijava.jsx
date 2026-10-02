import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authApi } from "../api";

function Prijava() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [greska, setGreska] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setGreska("");

    try {
      const response = await authApi.prijava(email, password);
      const token = response.data.token || response.data;
      localStorage.setItem('token', token);
       localStorage.setItem('korisnik', JSON.stringify(response.data));
      navigate("/pocetna");
    } catch (err) {
      console.error(err);
      setGreska("Neuspesna prijava, pokusajte ponovo");
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "50px auto", padding: "20px", border: "1px solid #ccc", borderRadius: "8px" }}>
      <h2>Prijava</h2>
      {greska && <p style={{ color: "red" }}>{greska}</p>}
      
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
        <div>
          <label>Email:</label>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
          />
        </div>

        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={{ width: "100%", padding: "8px", boxSizing: "border-box" }}
          />
        </div>

        <button type="submit" style={{ padding: "10px", background: "#007BFF", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}>
          Prijavi se
        </button>
      </form>
    </div>
  );
}

export default Prijava;