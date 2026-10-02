import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { proizvodApi } from "../api";

function Pocetna() {
  const [proizvodi, setProizvodi] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    proizvodApi.getAll()
        .then((res) => {
        setProizvodi(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("GRESKA:", err);
        setError("Greska pri ucitavanju proizvoda");
        setLoading(false);
      });
  }, []);

    const najnovijiProizvodi = [...proizvodi]
  .sort((a, b) => b.idProizvod - a.idProizvod)
  .slice(0, 3);


 return (

    <div style ={{ padding: "40px", fontFamily: "sans-serif", color: "#fff", maxWidth: "1200px", margin: "0 auto" }}>
      <div style={{ textAlign: "center", marginBottom: "50px" }}>
        <h1 style={{ fontSize: "40px", marginBottom: "15px", color: "#fff" }}>Dobrodošli u Le perfumerie</h1>
      </div>

      <h2 style= {{ fontSize: "28px", marginBottom: "20px", borderBottom: "2px solid #333", paddingBottom: "10px" }}>
        Najnoviji proizvodi
      </h2>

      {najnovijiProizvodi.length === 0 ? (
        <p style={{ color: "#aaa" }}>Trenutno nema proizvoda u bazi</p>
      ) : (
        <div style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gap: "25px" }}>
          {najnovijiProizvodi.map((proizvod) => { 
            const cenaBezPdv = proizvod.cena || 0;
            const procenatPdva = proizvod.poreskaStopa?.procenat || 20.0;
            const cenaSaPdv = (cenaBezPdv * (1 + procenatPdva / 100.0));

            return (
            <div 
              key={proizvod.idProizvod} 
              style={{ 
                background: "#1e1e1e", 
                border: "1px solid #333", 
                padding: "20px", 
                borderRadius: "12px", 
                display: "flex", 
                flexDirection: "column", 
                justifyContent: "space-between",
                boxShadow: "0 4px 10px rgba(0,0,0,0.3)",
                transition: "transform 0.2s"
              }}
            >
              <div>
                {proizvod.slika ? (
                  <img 
                    src={proizvod.slika} 
                     alt={proizvod.naziv} 
                    style={{ width: "100%", height: "200px", objectFit: "cover", borderRadius: "8px", marginBottom: "15px" }} 
                  />
                ) : (
                  <div style={{ width: "100%", height: "200px", background: "#333", borderRadius: "8px", display: "flex", alignItems: "center", justifyContent: "center", color: "#888", marginBottom: "15px" }}>
                    Nema slike
                  </div>
                )}

                <h3 style={{ margin: "0 0 10px 0", fontSize: "20px", color: "#fff" }}>{proizvod.naziv}</h3>
                <p style={{ fontSize: "18px", color: "#4caf50", fontWeight: "bold", margin: "0 0 15px 0" }}>
                  {cenaSaPdv} Rsd

                </p>
              </div>

              <Link to={`/proizvodi/${proizvod.idProizvod}`} style={{ textDecoration: "none" }}>
                <button style={{ 
                  width: "100%", 
                  padding: "10px", 
                  cursor: "pointer", 
                  backgroundColor: "#007bff", 
                  color: "#fff", 
                  border: "none", 
                  borderRadius: "6px", 
                  fontSize: "15px",
                  fontWeight: "bold"
                }}>
                  Detaljno
                </button>
              </Link>

            </div>
          );
          })}
        </div>
      )}
    </div>
  );
}

export default Pocetna;