import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import { proizvodApi } from "../api";


function PojedinacanProizvod() {
  const { idProizvod } = useParams(); 
  const [proizvod, setProizvod] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  const ulogovan = !!localStorage.getItem("token");

  useEffect(() => {
    proizvodApi.getById(idProizvod)
     .then((res) => {
      setProizvod(res.data);
      setLoading(false);
  })    
      .catch((err) => {
        console.error("Greska:", err);
        setError("Greska pri ucitavanju proizvoda");
        setLoading(false);
      });
  }, [idProizvod]); 

  const dodajUKorpu = (proizvod) => {
    if (!ulogovan) {
      alert("Morate biti prijavljeni da biste dodali proizvod u korpu");
      navigate("/prijava"); 
      return;
    }
   
     let korpa = JSON.parse(localStorage.getItem("korpa")) || [];
    const postoji = korpa.find((p) => p.idProizvod === proizvod.idProizvod);

     if (postoji) {
    postoji.kolicina += 1;
     } else {
      korpa.push({ ...proizvod, kolicina: 1 });
    }

  localStorage.setItem("korpa", JSON.stringify(korpa));
  alert("Dodato u korpu");
};

  if (loading) return <p style={{ padding: "20px" }}>Učitavanje...</p>;
  if (error) return <p style={{ padding: "20px", color: "red" }}>{error}</p>;
  if (!proizvod) return <p style={{ padding: "20px" }}>Proizvod nije pronadjen</p>;

  const cenaBezPdv = proizvod.cena || 0;
  const procenatPdva = proizvod.poreskaStopa?.procenat || 20.0;
  const cenaSaPdv = cenaBezPdv * (1 + procenatPdva / 100.0);

    return (
    <div style={{ padding: "40px", fontFamily: "sans-serif", color: "#fff", maxWidth: "900px", margin: "0 auto" }}>
      <Link to="/proizvodi" style={{ textDecoration: "none", color: "#61dafb", display: "inline-block", marginBottom: "25px", fontSize: "16px" }}>
        &larr; Nazad na sve proizvode
      </Link>
      
      <div style={{ display: "flex", gap: "40px", alignItems: "flex-start", background: "#1e1e1e", padding: "30px", borderRadius: "12px", border: "1px solid #333" }}>
        
        {proizvod.slika ? (
          <img 
            src={proizvod.slika} 
            alt={proizvod.naziv} 
            style={{ width: "280px", height: "280px", objectFit: "cover", borderRadius: "8px", boxShadow: "0 4px 15px rgba(0,0,0,0.5)" }} 
          />
        ) : (
          <div style={{ width: "280px", height: "280px", background: "#333", borderRadius: "8px", display: "flex", alignItems: "center", justifyContent: "center", color: "#888" }}>
            Nema slike
          </div>
        )}

        <div style={{ display: "flex", flexDirection: "column", flex: "1", gap: "15px" }}>
          <h1 style={{ margin: "0", fontSize: "32px", color: "#fff" }}>{proizvod.naziv}</h1>
          <p style={{ fontSize: "22px", color: "#4caf50", fontWeight: "bold", margin: "0" }}>
            {cenaSaPdv.toFixed(2)} RSD
          </p>
          
          <div style={{ marginTop: "10px" }}>
            <h4 style={{ margin: "0 0 5px 0", color: "#aaa" }}>Opis:</h4>
            <p style={{ margin: "0", lineHeight: "1.6", color: "#ddd", fontSize: "15px" }}>
              {proizvod.opis || "Nema opisa za ovaj proizvod"}
            </p>
          </div>
          
          <div style={{ marginTop: "20px" }}>
            <button 
              style={{ 
                padding: "12px 25px", 
                cursor: "pointer", 
                backgroundColor: ulogovan ? "#007bff" : "#555",
                color: "#fff", 
                border: "none", 
                borderRadius: "6px", 
                fontSize: "16px",
                fontWeight: "bold",
                transition: "background 0.2s"
              }}
              onClick={() => dodajUKorpu(proizvod)} 
            >
             {ulogovan ? "Dodaj u korpu" : "Prijavite se da biste kupovali"}
            </button>
          </div>
        </div>

      </div>
    </div>
  );
}

export default PojedinacanProizvod;